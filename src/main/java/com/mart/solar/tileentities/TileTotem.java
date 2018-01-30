package com.mart.solar.tileentities;

import com.mart.solar.api.enums.RitualLevel;
import com.mart.solar.api.enums.CircleTypes;
import com.mart.solar.api.registry.RitualRegister;
import com.mart.solar.api.util.RitualContainer;
import com.mart.solar.blocks.menhirs.BlockMenhir;
import com.mart.solar.circles.Circle;
import com.mart.solar.items.ItemRitualStaff;
import com.mart.solar.registry.ModBlocks;
import com.mart.solar.rituals.Ritual;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.vecmath.Vector2d;
import java.util.*;

public class TileTotem extends TileBase implements ITickable {

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

    int second = 0;

    public TileTotem() {
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
        return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.solarEnergy = compound.getFloat("solarEnergy");
        this.lunarEnergy = compound.getFloat("lunarEnergy");
    }

    @Override
    public void update() {
        if (this.getWorld().getWorldTime() % 24000 > 0 && this.getWorld().getWorldTime() % 24000 < 13000) {
            addSolarEnergy();
            extractLunarEnergy(1);
        }
        if (this.getWorld().getWorldTime() % 24000 >= 13000 && this.getWorld().getWorldTime() % 24000 <= 24000) {
            addLunarEnergy();
            extractSolarEnergy(1);
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

}