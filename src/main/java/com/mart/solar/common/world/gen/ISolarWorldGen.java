package com.mart.solar.common.world.gen;

import net.minecraft.world.World;

import java.util.Random;

public interface ISolarWorldGen {

    public void generateOverworld(World world, Random rand, int chunkX, int chunkZ);
    public void generateNether(World world, Random rand, int chunkX, int chunkZ);
    public void generateEnd(World world, Random rand, int chunkX, int chunkZ);

}
