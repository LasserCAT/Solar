package com.mart.solar.common.world.gen;

import com.mart.solar.common.world.gen.structures.StructForgottenAltar;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class WorldGenHandler implements IWorldGenerator {

    private WorldGenSilverOre silverOreGen = new WorldGenSilverOre();
    private StructForgottenAltar structForgottenAltar = new StructForgottenAltar();
    private WorldGenFlowerMoon flowerMoon = new WorldGenFlowerMoon();
    private WorldGenFlowerFiery flowerFiery = new WorldGenFlowerFiery();

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        switch (world.provider.getDimension()) {
            case 1:
                break;
            case 0:
                silverOreGen.generateOverworld(world, random, chunkX * 16, chunkZ * 16);
                structForgottenAltar.generateOverworld(world, random, chunkX * 16, chunkZ * 16);
                flowerMoon.generateOverworld(world, random, chunkX, chunkZ);
                flowerFiery.generateOverworld(world, random, chunkX, chunkZ);
                break;
            case -1:
                flowerFiery.generateNether(world, random, chunkX, chunkZ);
                break;
        }
    }
}
