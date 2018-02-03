package com.mart.solar.common.tileentities;

import com.mart.solar.api.enums.RitualLevel;
import com.mart.solar.api.enums.CircleTypes;
import com.mart.solar.api.registry.RitualRegister;
import com.mart.solar.api.util.RitualContainer;
import com.mart.solar.common.blocks.menhirs.BlockMenhir;
import com.mart.solar.common.circles.Circle;
import com.mart.solar.common.items.ItemRitualStaff;
import com.mart.solar.common.recipes.AltarRecipe;
import com.mart.solar.common.registry.ModBlocks;
import com.mart.solar.common.rituals.Ritual;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

import javax.vecmath.Vector2d;
import java.util.*;

public class TileAltar extends TileBase implements ITickable {

    private int[][] runeArray = new int[9][9];

    private int[][] tierOneCoords = new int[][]{
            {0, -4},
            {3, -3},
            {4, 0},
            {3, 3},
            {0, 4},
            {-3, 3},
            {-4, 0},
            {-3, -3}
    };

    private Map<Integer, Vector2d> menhirPlaces = new HashMap<>();

    private float solarEnergy = 0;
    private float lunarEnergy = 0;

    public boolean blocked = false;

    private ItemStack heldItem = ItemStack.EMPTY;
    private AltarRecipe currentRecipe;
    private int currentEnergyProgress;

    public TileAltar() {
        menhirPlaces.put(1, new Vector2d(0, -4));
        menhirPlaces.put(2, new Vector2d(3, -3));
        menhirPlaces.put(3, new Vector2d(4, 0));
        menhirPlaces.put(4, new Vector2d(3, 3));
        menhirPlaces.put(5, new Vector2d(0, 4));
        menhirPlaces.put(6, new Vector2d(-3, 3));
        menhirPlaces.put(7, new Vector2d(-4, 0));
        menhirPlaces.put(8, new Vector2d(-3, -3));
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

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setFloat("solarEnergy", this.solarEnergy);
        compound.setFloat("lunarEnergy", this.lunarEnergy);

        NBTTagList tagList = new NBTTagList();
        NBTTagCompound itemCompound = new NBTTagCompound();
        this.heldItem.writeToNBT(itemCompound);
        tagList.appendTag(itemCompound);
        compound.setTag("heldItem", tagList);

        return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.solarEnergy = compound.getFloat("solarEnergy");
        this.lunarEnergy = compound.getFloat("lunarEnergy");

        NBTTagList tagList = (NBTTagList) compound.getTag("heldItem");
        NBTTagCompound tagCompound = tagList.getCompoundTagAt(0);
        this.heldItem = new ItemStack(tagCompound);
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
        if(this.getWorld().isRemote){
            return;
        }

        if(!this.heldItem.isEmpty() && this.currentRecipe != null){
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
                this.heldItem = new ItemStack(this.currentRecipe.getOutput(), 1);
                this.currentRecipe = null;
                this.currentEnergyProgress = 0;
                notifyUpdate();
            }
        }
    }

    public void checkForMenhirs(BlockPos pos, World world, EntityPlayer player) {
        int radius = 4;

        int startingX = pos.getX() - radius;
        int startingZ = pos.getZ() - radius;
        int startingY = pos.getY();

        for (int x = pos.getX() - radius; x <= pos.getX() + radius; x++) {
            for (int z = pos.getZ() - radius; z <= pos.getZ() + radius; z++) {
                Block block = world.getBlockState(new BlockPos(x, startingY, z)).getBlock();

                int xPos = x - startingX;
                int zPos = z - startingZ;

                if (block instanceof BlockMenhir) {
                    runeArray[zPos][xPos] = 1;
                } else if (block.equals(Blocks.STONE)) {
                    System.out.println("Block is stone");
                    getStoneBlocks(radius, pos, world, player);
                    return;
                } else {
                    runeArray[zPos][xPos] = 0;
                }
            }
        }
        for (int[] i : runeArray) {
            for (int j : i) {
                System.out.print(j + " ");
            }
            System.out.println(" ");
        }

        if (Arrays.deepEquals(Circle.tieronecircel, runeArray)) {
            checkCirleType(1, world, pos, player);
        }
    }

    void getStoneBlocks(int radius, BlockPos pos, World world, EntityPlayer player) {
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

        turnIntoMenhirs(stonePositions, world);
    }

    void turnIntoMenhirs(List<BlockPos> pos, World world) {
        for (BlockPos b : pos) {
            BlockPos blockPos = new BlockPos(b.getX(), b.getY() + 1, b.getZ());
            Block block = world.getBlockState(blockPos).getBlock();

            if (block.equals(Blocks.LOG)) {
                world.setBlockState(b, ModBlocks.lifeMenhir.getDefaultState());
                world.setBlockState(blockPos, Blocks.AIR.getDefaultState());
            }
            if (block.equals(Blocks.GOLD_ORE)) {
                world.setBlockState(b, ModBlocks.sunMenhir.getDefaultState());
                world.setBlockState(blockPos, Blocks.AIR.getDefaultState());
            }
            if (block.equals(ModBlocks.silverOre)) {
                world.setBlockState(b, ModBlocks.moonMenhir.getDefaultState());
                world.setBlockState(blockPos, Blocks.AIR.getDefaultState());
            }
            if (block.equals(Blocks.LAPIS_BLOCK)) {
                world.setBlockState(b, ModBlocks.timeMenhir.getDefaultState());
                world.setBlockState(blockPos, Blocks.AIR.getDefaultState());
            }
        }
    }


    public void checkCirleType(int level, World world, BlockPos pos, EntityPlayer player) {
        switch (level) {
            case 1:
                CircleTypes circleTypes = null;
                List<TileMenhir> menhirs = new ArrayList<>();

                BlockPos block1Pos = new BlockPos(pos.getX() + tierOneCoords[0][0], pos.getY(), pos.getZ() + tierOneCoords[0][1]);

                Block block = world.getBlockState(block1Pos).getBlock();
                if (block instanceof BlockMenhir) {
                    circleTypes = ((BlockMenhir) block).getType();

                    TileMenhir tile = (TileMenhir) world.getTileEntity(block1Pos);

                    menhirs.add(tile);
                }


                for (int i = 1; i < 8; i++) {
                    BlockPos block2Pos = new BlockPos(pos.getX() + tierOneCoords[i][0], pos.getY(), pos.getZ() + tierOneCoords[i][1]);
                    Block block2 = world.getBlockState(block2Pos).getBlock();

                    if (block2 instanceof BlockMenhir) {
                        CircleTypes type = ((BlockMenhir) block2).getType();
                        if (circleTypes != type) {
                            return;
                        }

                        TileMenhir tile = (TileMenhir) world.getTileEntity(block2Pos);
                        menhirs.add(tile);
                    }
                }

                RitualContainer rc = new RitualContainer(pos);
                rc.addTypes(circleTypes);
                rc.setRcLevel(RitualLevel.ONE);
                rc.setMenhirs(menhirs);
                rc.setWorld(world);
                rc.setPlayer(player);
                rc.setRitualStaff(player.getHeldItemMainhand());
                checkRitual(rc);

                break;
        }
    }

    public void checkRitual(RitualContainer rc) {

        for (TileMenhir b : rc.getMenhirs()) {
            if (b.getRune() != null) {
                if (b.getRune().getItemDamage() == 1) {
                    rc.addFireRunes();
                } else if (b.getRune().getItemDamage() == 2) {
                    rc.addWaterRunes();
                } else if (b.getRune().getItemDamage() == 3) {
                    rc.addEarthRunes();
                } else if (b.getRune().getItemDamage() == 4) {
                    rc.addWindRunes();
                } else if (b.getRune().getItemDamage() == 5) {
                    rc.addTimeRunes();
                } else if (b.getRune().getItemDamage() == 6) {
                    rc.addLifeRunes();
                } else if (b.getRune().getItemDamage() == 7) {
                    rc.addSunRunes();
                } else if (b.getRune().getItemDamage() == 8) {
                    rc.addMoonRunes();
                }
            }

        }

        for (Ritual r : RitualRegister.getRituals()) {
            if (r.getTypes().equals(rc.getTypes())
                    && r.getFireRunes() == rc.getFireRunes()
                    && r.getWaterRunes() == rc.getWaterRunes()
                    && r.getEarthRunes() == rc.getEarthRunes()
                    && r.getWindRunes() == rc.getWindRunes()
                    && r.getTimeRunes() == rc.getTimeRunes()
                    && r.getLifeRunes() == rc.getLifeRunes()
                    && r.getMoonRunes() == rc.getMoonRunes()
                    && r.getSunRunes() == rc.getSunRunes()) {
                checkRunes(r, rc);
            }
        }
    }

    void checkRunes(Ritual ritual, RitualContainer rc) {
        for (Map.Entry<Integer, Integer> entry : ritual.getRunes().entrySet()) {

            if (rc.getMenhirs().get(entry.getKey()).getRune() != null) {

                TileMenhir tile = rc.getMenhirs().get(entry.getKey());
                int itemDamage = entry.getValue();

                ItemStack i = tile.getRune();

                if (i.getItemDamage() == itemDamage) {
                } else {
                    return;
                }

            } else {
                System.out.println("Nope");
                return;
            }
        }

        if (ritual.isHasSpell()) {
            ItemRitualStaff r = (ItemRitualStaff) rc.getRitualStaff().getItem();
            r.setCurrentSpell(rc.getPlayer().getHeldItemMainhand(), ritual.getSpell().getName());

        } else {
            ritual.activateRitual(rc.getPlayer(), ritual.getRitualSolarCost(), ritual.getRitualLunarCost());
        }
    }

    public boolean skyBlocked(){
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

    public void retrieveItem(EntityPlayer player, EnumHand hand){
        if(!this.heldItem.isEmpty()){
            player.setHeldItem(hand, this.heldItem);
            this.heldItem = ItemStack.EMPTY;
        }

    }

    public void startAltarRecipe(AltarRecipe altarRecipe, EntityPlayer player, ItemStack stack, EnumHand hand) {
        if(altarRecipe.getEnergyCost() > (this.solarEnergy + lunarEnergy)){
            player.sendMessage(new TextComponentString("Not enough energy in altar"));
            return;
        }

        this.currentRecipe = altarRecipe;
        this.currentEnergyProgress = 0;
        this.heldItem = stack.copy();
        this.heldItem.setCount(1);

        player.setHeldItem(hand, ItemStack.EMPTY);

        notifyUpdate();
    }

    public ItemStack getHeldItem() {
        return heldItem;
    }
}