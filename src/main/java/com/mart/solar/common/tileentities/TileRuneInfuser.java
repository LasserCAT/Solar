package com.mart.solar.common.tileentities;

import com.mart.solar.api.enums.RuneType;
import com.mart.solar.api.infusing.InfuserReagent;
import com.mart.solar.api.infusing.InfuserReagentManager;
import com.mart.solar.common.registry.ModBlocks;
import com.mart.solar.common.registry.ModItems;
import com.mart.solar.common.util.itemhandler.TileRuneInfuserItemHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TileRuneInfuser extends TileBase implements ITickable, ICapabilityProvider {

    private static final int INFUSE_DURATION = 140;
    private static List<InfuserReagent> infuserReagents;

    private TileRuneInfuserItemHandler itemStackHandler;

    private int currentDuration = 0;
    private int reagentUses = 0;
    private String reagentName = "";
    private boolean infusing = false;

    private TileAltar linkedAltar = null;

    public TileRuneInfuser(){
        itemStackHandler = new TileRuneInfuserItemHandler(this);
    }

    @Override
    public void onLoad() {
        getNearbyAltar();

        if(infuserReagents == null){ setupInfuserReagents();}

        infuse();
    }

    @Override
    public void update() {
        if (infusing) {
            if(linkedAltar == null){
                stopInfusing();
                return;
            }

            if(world.isRemote){
                if(this.world.getWorldTime() % 5 == 0){
                    spawnEnergyParticle(this.linkedAltar.getPos(), this.pos);
                }
            }

            spawnInfuseParticle();
            if (currentDuration <= INFUSE_DURATION) {
                currentDuration++;
            } else {
                if(!this.getWorld().isRemote){
                    endInfusing();
                }
            }
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);

        compound.setTag("itemStackHandler", this.itemStackHandler.serializeNBT());

        compound.setString("reagentName", this.reagentName);
        compound.setInteger("currentDuration", this.currentDuration);
        compound.setInteger("reagentUses", this.reagentUses);
        compound.setBoolean("infusing", this.infusing);

        return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);

        this.itemStackHandler.deserializeNBT(compound.getCompoundTag("itemStackHandler"));

        this.reagentName = compound.getString("reagentName");
        this.currentDuration = compound.getInteger("currentDuration");
        this.reagentUses = compound.getInteger("reagentUses");
        this.infusing = compound.getBoolean("infusing");
    }

    private void setupInfuserReagents(){
        infuserReagents = new ArrayList<>();
        Random random = new Random(this.world.getSeed());
        List<InfuserReagent> allReagents = InfuserReagentManager.getReagents();

        for(RuneType type : RuneType.values()){
            List<InfuserReagent> typeReagents = new ArrayList<>();

            allReagents.forEach(re -> {
                if(re.getRuneType() == type){
                    typeReagents.add(re);
                }
            });

            if (typeReagents.size() == 0) {
                continue;
            }
            else if(typeReagents.size() == 1){
                infuserReagents.add(typeReagents.get(0));
                continue;
            }

            int randomReagent = random.nextInt(typeReagents.size());
            infuserReagents.add(typeReagents.get(randomReagent));
        }
    }


    public void extractItem(EntityPlayer player) {
        this.itemStackHandler.extractItemPlayer(0, 1, false, player);
    }

    public void insertItem(EntityPlayer player, EnumHand hand) {
        if(world.isRemote){
            return;
        }

        ItemStack returnStack;
        ItemStack heldItem = player.getHeldItem(hand);

        ItemStack insertStack = heldItem.copy();
        insertStack.setCount(1);

        if(heldItem.getItem() == ModItems.RUNES){
            if(heldItem.getItemDamage() != 0){
                return;
            }

            returnStack = this.itemStackHandler.insertItem(0, insertStack, false);
        }
        else{
            returnStack = this.itemStackHandler.insertItem(1, insertStack, false);
        }

        if(returnStack.isEmpty()){
            if(heldItem.getCount() <= 1){
                player.setHeldItem(hand, ItemStack.EMPTY);
            }
            else{
                heldItem.setCount(heldItem.getCount()-1);
            }
        }

        if(!this.world.isRemote){
            notifyUpdate();
        }
    }

    public void infuse(){
        if(!getNearbyAltar()){
            return;
        }

        if(!this.itemStackHandler.getStackInSlot(0).isEmpty() && !this.itemStackHandler.getStackInSlot(1).isEmpty()){
            if(reagentName.equalsIgnoreCase("")){
                for(InfuserReagent reagent : infuserReagents){
                    if(reagent.getReagent() == this.itemStackHandler.getStackInSlot(1).getItem()){
                        this.infusing = true;
                        this.reagentName = reagent.getRegistryName().toString();
                        this.reagentUses = reagent.getReagentUses();
                        break;
                    }
                }
            }
            else{
                this.infusing = true;
            }
        }
    }

    /*
    Force stop the infusing because something happened, infusion did not succeed
     */
    private void stopInfusing(){
        infusing = false;
        currentDuration = 0;
    }

    /*
    End the infusing in the appropriate way. This means the infusion has succeeded
     */
    private void endInfusing() {
        if (this.world.isRemote) {
            return;
        }

        //set the rune to its infused state
        InfuserReagent reagent = InfuserReagentManager.getInuserReagent(this.reagentName);
        ItemStack rune = new ItemStack(ModItems.RUNES, 1, reagent.getRuneType().ordinal());
        this.itemStackHandler.setStackInSlot(0, rune);

        //remove or down reagent
        this.reagentUses--;
        if(reagentUses <= 0){
            this.reagentName = "";
            this.itemStackHandler.setStackInSlot(1, ItemStack.EMPTY);
        }

        infusing = false;
        currentDuration = 0;

        if(!this.world.isRemote){
            notifyUpdate();
        }
    }

    public boolean getNearbyAltar(){
        int y = this.getPos().getY();
        for(int x = this.getPos().getX() - 5; x < this.getPos().getX() + 5; x++){
            for(int z = this.getPos().getZ() - 5; z < this.getPos().getZ() + 5; z++){

                if(this.world.getBlockState(new BlockPos(x, y, z)).getBlock() == ModBlocks.ALTAR){
                    this.linkedAltar = (TileAltar) this.world.getTileEntity(new BlockPos(x, y, z));
                    return true;
                }

            }
        }

        return false;
    }

    //Infusing particle spawn
    @SideOnly(Side.CLIENT)
    private void spawnInfuseParticle() {
        int[] array = {Item.getIdFromItem(this.itemStackHandler.getStackInSlot(1).getItem())};
        Vec3d[] vec = getPositionAndVelocity(getPos());
        getWorld().spawnParticle(EnumParticleTypes.ITEM_CRACK, vec[0].x, vec[0].y, vec[0].z, vec[1].x, 0, vec[1].z, array);
    }

    private Vec3d[] getPositionAndVelocity(BlockPos center) {
        Random random = new Random();
        double velocityFactor = 0.1;
        double radius = 0.2 + random.nextDouble() * 0.2;
        double angle = random.nextDouble() * Math.PI * 2;
        double posX = 0.5 + Math.cos(angle) * radius;
        double posZ = 0.5 + Math.sin(angle) * radius;

        Vec3d p = new Vec3d(posX + center.getX(), center.getY() + 2, posZ + center.getZ());
        Vec3d v = new Vec3d((0.5 - posX) * velocityFactor, 0, (0.5 - posZ) * velocityFactor);
        return new Vec3d[]{p, v};
    }


    //Capability
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

    public ItemStack getReagent() {
        return this.itemStackHandler.getStackInSlot(1);
    }

    public ItemStack getRune() {
        return this.itemStackHandler.getStackInSlot(0);
    }

    public void clearReagent() {
        this.itemStackHandler.setStackInSlot(1, ItemStack.EMPTY);
        if(!this.world.isRemote){
            notifyUpdate();
        }
    }

    public boolean isInfusing() {
        return infusing;
    }

    public boolean isLinkedToAltar(){
        return this.linkedAltar != null;
    }

    public TileRuneInfuserItemHandler getItemStackHandler() {
        return itemStackHandler;
    }
}