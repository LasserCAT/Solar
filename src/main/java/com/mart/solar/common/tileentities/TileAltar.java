package com.mart.solar.common.tileentities;

import com.mart.solar.api.ritual.Ritual;
import com.mart.solar.api.ritual.RitualManager;
import com.mart.solar.common.recipes.AltarRecipe;
import com.mart.solar.common.recipes.AltarRecipeManager;
import com.mart.solar.common.registry.ModBlocks;
import com.mart.solar.common.util.TileAltarItemHandler;
import com.mart.solar.common.util.TileRuneInfuserItemHandler;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;
import java.util.*;

public class TileAltar extends TileBase implements ITickable, ICapabilityProvider {

    //todo: change back to 0
    private float solarEnergy = 30000;
    private float lunarEnergy = 30000;

    private boolean blocked = false;

    private AltarRecipe currentRecipe;
    private int energyCost = 0;
    private int currentEnergyProgress = 0;
    private boolean recipeInProgress = false;

    private TileAltarItemHandler itemStackHandler;

    public TileAltar() {
        itemStackHandler = new TileAltarItemHandler(this);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);

        if(this.currentRecipe != null){
            compound.setBoolean("recipeInProgress", true);
            compound.setInteger("recipeEnergyCost", this.currentRecipe.getEnergyCost());
            compound.setString("currentRecipe", this.currentRecipe.getRegistryName().toString());
        }
        else{
            compound.setBoolean("recipeInProgress", false);
            compound.setInteger("recipeEnergyCost", 0);
            compound.setString("currentRecipe", "");
        }

        compound.setInteger("recipeEnergyProgress", this.currentEnergyProgress);
        compound.setFloat("solarEnergy", this.solarEnergy);
        compound.setFloat("lunarEnergy", this.lunarEnergy);


        compound.setTag("itemStackHandler", this.itemStackHandler.serializeNBT());

        return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.recipeInProgress = compound.getBoolean("recipeInProgress");
        this.solarEnergy = compound.getFloat("solarEnergy");
        this.lunarEnergy = compound.getFloat("lunarEnergy");
        this.energyCost = compound.getInteger("recipeEnergyCost");
        this.currentEnergyProgress = compound.getInteger("recipeEnergyProgress");
        this.currentRecipe = AltarRecipeManager.getRecipeByRegistryName(compound.getString("currentRecipe"));

        this.itemStackHandler.deserializeNBT(compound.getCompoundTag("itemStackHandler"));
    }

    @Override
    public void update() {

        if(this.getWorld().getWorldTime() % 20 == 0){
            this.blocked = this.skyBlocked();
        }

        if(!blocked){
            if (this.getWorld().getWorldTime() % 24000 > 0 && this.getWorld().getWorldTime() % 24000 < 13000) {
                addSolarEnergy();
                extractLunarEnergy(2);
            }
            if (this.getWorld().getWorldTime() % 24000 >= 13000 && this.getWorld().getWorldTime() % 24000 <= 24000) {
                addLunarEnergy();
                extractSolarEnergy(2);
            }
        }

        runAltarRecipe();
    }

    private void runAltarRecipe(){
        if(!this.itemStackHandler.getStackInSlot(0).isEmpty() && this.currentRecipe != null){
            if(this.currentEnergyProgress < this.currentRecipe.getEnergyCost()){
                if(this.solarEnergy > 0 && this.lunarEnergy > 0){
                    this.currentEnergyProgress += 2;
                    this.solarEnergy--;
                    this.lunarEnergy--;
                }
                else if(this.solarEnergy <= 0 && this.lunarEnergy > 0){
                    this.currentEnergyProgress++;
                    this.lunarEnergy--;
                }
                else if(this.lunarEnergy <= 0 && this.solarEnergy > 0){
                    this.currentEnergyProgress++;
                    this.solarEnergy--;
                }
            }
            else{
                this.itemStackHandler.setStackInSlot(0, new ItemStack(this.currentRecipe.getOutput(), 1));
                this.currentRecipe = null;
                this.currentEnergyProgress = 0;
                this.recipeInProgress = false;
                if(!this.world.isRemote){
                    notifyUpdate();
                }
            }
        }
    }

    public void useRitualAmulet(BlockPos pos, World world, EntityPlayer player) {
        int radius = 5;
        int startingY = pos.getY();

        for (int x = pos.getX() - radius; x <= pos.getX() + radius; x++) {
            for (int z = pos.getZ() - radius; z <= pos.getZ() + radius; z++) {
                Block block = world.getBlockState(new BlockPos(x, startingY, z)).getBlock();

                if (block.equals(Blocks.STONE)) {
                    craftMenhirs(radius, pos, world);
                    return;
                }
            }
        }

        //Check Ritual
        Optional<Ritual> ritual = RitualManager.getRituals().stream().filter(r -> r.isSetup(this)).findFirst();
        if(ritual.isPresent()){
            if(this.solarEnergy >= ritual.get().getRitualSolarCost() && this.lunarEnergy >= ritual.get().getRitualLunarCost() ){
                ritual.get().performRitual(this, player);
                ritual.get().clearRunes(this);
                this.lunarEnergy -= ritual.get().getRitualLunarCost();
                this.solarEnergy -= ritual.get().getRitualSolarCost();
            }
        }
    }

    private void craftMenhirs(int radius, BlockPos pos, World world) {
        List<BlockPos> stonePositions = new ArrayList<>();
        int startingY = pos.getY();

        for (int x = pos.getX() - radius; x <= pos.getX() + radius; x++) {
            for (int z = pos.getZ() - radius; z <= pos.getZ() + radius; z++) {
                Block block = world.getBlockState(new BlockPos(x, startingY, z)).getBlock();

                if (block.equals(Blocks.STONE)) {
                    stonePositions.add(new BlockPos(x, startingY, z));
                }
            }
        }

        for (BlockPos b : stonePositions) {
            BlockPos blockPlusOnePos = new BlockPos(b.getX(), b.getY() + 1, b.getZ());
            BlockPos blockPlusTwoPos = new BlockPos(b.getX(), b.getY() + 2, b.getZ());
            if(world.getBlockState(blockPlusOnePos).getBlock() == Blocks.STONE && world.getBlockState(blockPlusTwoPos).getBlock() == Blocks.STONE){
                world.setBlockState(b, ModBlocks.menhir.getDefaultState());
                world.setBlockState(blockPlusOnePos, Blocks.AIR.getDefaultState());
                world.setBlockState(blockPlusTwoPos, Blocks.AIR.getDefaultState());
            }
            else{
                world.setBlockState(b, Blocks.AIR.getDefaultState());
                world.setBlockState(blockPlusOnePos, Blocks.AIR.getDefaultState());
                world.setBlockState(blockPlusTwoPos, Blocks.AIR.getDefaultState());
                world.playEvent(2001, b, Block.getStateId(Blocks.STONE.getDefaultState()));
            }
        }
    }

    private boolean skyBlocked(){
        BlockPos position = this.getPos();
        for(int y = position.getY() + 1; y < 256; y++){
            BlockPos checkPosition = new BlockPos(position.getX(), y, position.getZ());
            IBlockState checkBlock =this.getWorld().getBlockState(checkPosition);

            if(checkBlock.getBlock() != Blocks.AIR){
                return true;
            }
        }

        return false;
    }

    public void retrieveItem(EntityPlayer player){
        ItemStack extractedItem = this.itemStackHandler.extractItem(0, 1, false);

        player.addItemStackToInventory(extractedItem);
        if(!this.world.isRemote){
            notifyUpdate();
        }
    }

    public void insertItem(EntityPlayer player, EnumHand hand) {
        if (world.isRemote) {
            return;
        }

        ItemStack heldStack = player.getHeldItem(hand);
        ItemStack insertStack = heldStack.copy();
        insertStack.setCount(1);

        AltarRecipe altarRecipe = isAltarRecipe(heldStack);

        if(altarRecipe == null){
            return;
        }

        ItemStack returnStack = this.itemStackHandler.insertItem(0, insertStack, false);

        if(returnStack == insertStack){
            return;
        }

        heldStack.setCount(heldStack.getCount() - 1);
    }

    public void startAltarRecipe(AltarRecipe altarRecipe) {
        this.currentRecipe = altarRecipe;
        this.energyCost = altarRecipe.getEnergyCost();
        this.recipeInProgress = true;
        this.currentEnergyProgress = 0;

        if(!this.world.isRemote){
            notifyUpdate();
        }
    }

    public AltarRecipe isAltarRecipe(ItemStack stack){
        AltarRecipe altarRecipe = AltarRecipeManager.findMatchingInput(stack.getItem());
        if (altarRecipe == null) {
            return null;
        }
        return altarRecipe;
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return true;
        }
        return super.hasCapability(capability, facing);
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return (T) this.itemStackHandler;
        }
        return super.getCapability(capability, facing);
    }

    //Getter Setters Adders Extractors
    public ItemStack getHeldItem() {
        return this.itemStackHandler.getStackInSlot(0);
    }

    public int getCurrentEnergyProgress() {
        return currentEnergyProgress;
    }

    public boolean isRecipeInProgress() {
        return recipeInProgress;
    }

    public int getEnergyCost() {
        return energyCost;
    }

    public void addSolarEnergy() {
        solarEnergy++;
    }

    public void addLunarEnergy() {
        lunarEnergy++;
    }

    public void extractSolarEnergy(float amount) {
        if (solarEnergy > 0)
            solarEnergy -= amount;
    }

    public void extractLunarEnergy(float amount) {
        if (lunarEnergy > 0)
            lunarEnergy -= amount;
    }

    public float getSolarEnergy() {
        return solarEnergy;
    }

    public float getLunarEnergy() {
        return lunarEnergy;
    }
}