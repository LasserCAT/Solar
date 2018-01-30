package com.mart.solar.spells;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class SpellSummerHeat extends Spell {

    public SpellSummerHeat() {
        super("Summer Heat");
    }

    @Override
    public void activateSpell(EntityPlayer player) {
        if (!player.getEntityWorld().isRemote) {
            World world = player.getEntityWorld();
            List<BlockPos> blocks = new ArrayList<>();

            Vec3d vec3d = player.getPositionEyes(0.1F);
            Vec3d vec3d1 = player.getLook(0.1F);
            Vec3d vec3d2 = vec3d.addVector(vec3d1.x * 5, vec3d1.y * 5, vec3d1.z * 5);
            RayTraceResult result = player.getEntityWorld().rayTraceBlocks(vec3d, vec3d2, true, false, true);

            Block b = player.getEntityWorld().getBlockState(result.getBlockPos()).getBlock();

            if (b.equals(Blocks.WATER)) {
                blocks.add(result.getBlockPos());
            } else {
                return;
            }

            for (int i = 0; i < 41; i++) {

                if (i == blocks.size()) {
                    setWater(blocks, player.getEntityWorld());
                    return;
                }
                if (i == 40) {
                    //todo: cancel spell and retract some things
                    break;
                }

                BlockPos block = blocks.get(i);
                BlockPos blockpos1 = new BlockPos(block.getX(), block.getY(), block.getZ() + 1);
                BlockPos blockpos2 = new BlockPos(block.getX(), block.getY(), block.getZ() - 1);
                BlockPos blockpos3 = new BlockPos(block.getX() + 1, block.getY(), block.getZ());
                BlockPos blockpos4 = new BlockPos(block.getX() - 1, block.getY(), block.getZ());

                Block block1 = world.getBlockState(blockpos1).getBlock();
                Block block2 = world.getBlockState(blockpos2).getBlock();
                Block block3 = world.getBlockState(blockpos3).getBlock();
                Block block4 = world.getBlockState(blockpos4).getBlock();

                if (block1.equals(Blocks.WATER) && !blocks.contains(blockpos1))
                    blocks.add(blockpos1);
                if (block2.equals(Blocks.WATER) && !blocks.contains(blockpos2))
                    blocks.add(blockpos2);
                if (block3.equals(Blocks.WATER) && !blocks.contains(blockpos3))
                    blocks.add(blockpos3);
                if (block4.equals(Blocks.WATER) && !blocks.contains(blockpos4))
                    blocks.add(blockpos4);

                if (blocks.size() >= 41) {
                    //todo: cancel spell and retract some things
                    break;
                }
            }
            System.out.println(blocks.size());
        }
    }

    void setWater(List<BlockPos> list, World world) {
        for (BlockPos p : list) {
            world.setBlockToAir(p);
        }
    }
}
