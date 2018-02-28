package com.mart.solar.common.world.gen;

import com.mart.solar.common.registry.ModBlocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;

import java.util.Random;

public class WorldGenFlowerFiery implements ISolarWorldGen {

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

        if (!BiomeDictionary.hasType(biome, BiomeDictionary.Type.SANDY)) {
            return;
        }

        int flowers = rand.nextInt(3) + 1;
        int baseX = rand.nextInt(16) + 1;
        int baseZ = rand.nextInt(16) + 1;
        for(int i = 0; i < flowers; i++){
            int randomZ = rand.nextInt(6) -3;
            int randomX = rand.nextInt(6) -3;
            BlockPos flowerPos = WorldGenUtil.getBlockAboveGround(new BlockPos(chunkBlockLocX + baseX + randomX, randomY, chunkBlockLocZ + baseZ + randomZ), world);

            if(ModBlocks.flowerFiery.canPlaceBlockAt(world, flowerPos)){
                world.setBlockState(flowerPos, ModBlocks.flowerFiery.getDefaultState());
            }

        }
    }

    @Override
    public void generateNether(World world, Random rand, int chunkX, int chunkZ) {
        int randomChance = rand.nextInt(6);
        if(randomChance != 1){
            return;
        }

        int chunkBlockLocX = (chunkX * 16), chunkBlockLocZ = (chunkZ * 16);
        int randomY = 100;

        BlockPos blockpos = new BlockPos(chunkBlockLocX, randomY, chunkBlockLocZ);

        int flowers = rand.nextInt(4) + 1;
        int baseX = rand.nextInt(16) + 1;
        int baseZ = rand.nextInt(16) + 1;
        for(int i = 0; i < flowers; i++){
            int randomZ = rand.nextInt(6) -3;
            int randomX = rand.nextInt(6) -3;
            BlockPos flowerPos = WorldGenUtil.getFirstBlockInNether(new BlockPos(chunkBlockLocX + baseX + randomX, randomY, chunkBlockLocZ + baseZ + randomZ), world);
            if(ModBlocks.flowerFiery.canPlaceBlockAt(world, flowerPos)){
                world.setBlockState(flowerPos, ModBlocks.flowerFiery.getDefaultState());
            }

        }
    }

    @Override
    public void generateEnd(World world, Random rand, int chunkX, int chunkZ) {

    }
}
