package com.mart.solar.common.world.gen;

import com.mart.solar.common.registry.ModBlocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class WorldGenSilverOre implements ISolarWorldGen {

    public void generateOverworld(World world, Random rand, int chunkX, int chunkZ) {
        for (int k = 0; k < 16; k++) {
            WorldGenerator silverGen = new WorldGenMinable(ModBlocks.silverOre.getDefaultState(), 9);

            for (int i = 0; i < 2; i++) {
                int firstBlockXCoord = chunkX + rand.nextInt(16);
                int firstBlockZCoord = chunkZ + rand.nextInt(16);
                int blockY = rand.nextInt(28) + 2;

                BlockPos blockPos = new BlockPos(firstBlockXCoord, blockY, firstBlockZCoord);

                silverGen.generate(world, rand, blockPos);
            }
        }
    }

    @Override
    public void generateNether(World world, Random rand, int chunkX, int chunkZ) {

    }

    @Override
    public void generateEnd(World world, Random rand, int chunkX, int chunkZ) {

    }

}
