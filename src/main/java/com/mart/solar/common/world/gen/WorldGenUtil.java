package com.mart.solar.common.world.gen;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WorldGenUtil {

    public static BlockPos getBlockAboveGround(BlockPos pos, World world){
        for (int y = 255; y > 0; y--) {
            Block block = world.getBlockState(new BlockPos(pos.getX(), y, pos.getZ())).getBlock();
            if (block != Blocks.AIR &&
                    block != Blocks.WATER &&
                    block != Blocks.FLOWING_WATER &&
                    block != Blocks.LEAVES &&
                    block != Blocks.LEAVES2 &&
                    block != Blocks.LAVA &&
                    block != Blocks.TALLGRASS) {
                return new BlockPos(pos.getX(), y + 1, pos.getZ());
            }
        }

        return new BlockPos(pos.getX(), 1, pos.getZ());
    }

    public static BlockPos getFirstBlockInNether(BlockPos blockPos, World world) {
        for (int y = 32; y < 125; y++) {
            Block block = world.getBlockState(new BlockPos(blockPos.getX(), y, blockPos.getZ())).getBlock();
            if (block == Blocks.NETHERRACK){
                Block blockAbove = world.getBlockState(new BlockPos(blockPos.getX(), y+1, blockPos.getZ())).getBlock();
                if(blockAbove == Blocks.AIR){
                    return new BlockPos(blockPos.getX(), y+1, blockPos.getZ());
                }
            }
        }

        return new BlockPos(blockPos.getX(), 200, blockPos.getZ());
    }
}
