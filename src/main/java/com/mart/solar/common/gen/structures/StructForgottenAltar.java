package com.mart.solar.gen.structures;

import com.mart.solar.registry.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class StructForgottenAltar implements IWorldGenerator {

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        switch (world.provider.getDimension()) {
            case 1:
                break;
            case 0:
                generateOverWorld(world, random, chunkX * 16, chunkZ * 16);
                break;
            case -1:
                break;
        }
    }

    private void generateOverWorld(World world, Random random, int x, int z) {
        int randomX = x + random.nextInt(16);
        int randomZ = z + random.nextInt(16);
        int randomY = 90;
        BlockPos blockpos = new BlockPos(randomX, randomY, randomZ);

        Biome biome = world.getBiomeForCoordsBody(blockpos);

        if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.PLAINS) || BiomeDictionary.hasType(biome, BiomeDictionary.Type.SANDY) || BiomeDictionary.hasType(biome, BiomeDictionary.Type.SAVANNA)) {
            int chance = random.nextInt(200) + 1;
            if (chance == 1) {

                for (int y = 255; y > 0; y--) {
                    Block block = world.getBlockState(new BlockPos(randomX, y, randomZ)).getBlock();
                    if (block != Blocks.AIR) {
                        world.setBlockState(new BlockPos(randomX, y + 1, randomZ), ModBlocks.brokenTotem.getDefaultState());
                        return;
                    }
                }

            }
        }


    }
}
