package com.mart.solar.common.world.gen;

import com.mart.solar.common.registry.ModBlocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;

import java.util.Random;

public class WorldGenFlowerMoon implements ISolarWorldGen {

    @Override
    public void generateOverworld(World world, Random rand, int chunkX, int chunkZ) {
        int randomChance = rand.nextInt(16);
        if(randomChance != 1){
            return;
        }

        int chunkBlockLocX = (chunkX * 16), chunkBlockLocZ = (chunkZ * 16);
        int randomY = 100;

        BlockPos blockpos = new BlockPos(chunkBlockLocX, randomY, chunkBlockLocZ);
        Biome biome = world.getBiomeForCoordsBody(blockpos);

        if (!BiomeDictionary.hasType(biome, BiomeDictionary.Type.PLAINS) && !BiomeDictionary.hasType(biome, BiomeDictionary.Type.SANDY) && !BiomeDictionary.hasType(biome, BiomeDictionary.Type.SAVANNA)) {
            return;
        }

        int flowers = rand.nextInt(8) + 1;

        for(int i = 0; i < flowers; i++){
            int randomX = chunkBlockLocX + rand.nextInt(16);
            int randomZ = chunkBlockLocZ + rand.nextInt(16);
            BlockPos flowerPos = WorldGenUtil.getBlockAboveGround(new BlockPos(randomX, randomY, randomZ), world);

            if(ModBlocks.FLOWER_MOON.canPlaceBlockAt(world, flowerPos)){
                world.setBlockState(flowerPos, ModBlocks.FLOWER_MOON.getDefaultState());
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
