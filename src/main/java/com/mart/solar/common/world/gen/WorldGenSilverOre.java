package com.mart.solar.common.world.gen;

import com.mart.solar.common.registry.ModBlocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenSilverOre implements ISolarWorldGen {

    public void generateOverworld(World world, Random rand, int chunkX, int chunkZ) {
        for (int k = 0; k < 8; k++) {
            int blockCount = rand.nextInt(6) + 1;
            WorldGenerator silverGen = new WorldGenMinable(ModBlocks.SILVER_ORE.getDefaultState(), blockCount);

            int firstBlockXCoord = chunkX + rand.nextInt(16);
            int firstBlockZCoord = chunkZ + rand.nextInt(16);
            int blockY = rand.nextInt(28) + 2;

            BlockPos blockPos = new BlockPos(firstBlockXCoord, blockY, firstBlockZCoord);

            silverGen.generate(world, rand, blockPos);
        }
    }

    @Override
    public void generateNether(World world, Random rand, int chunkX, int chunkZ) {

    }

    @Override
    public void generateEnd(World world, Random rand, int chunkX, int chunkZ) {

    }

}
