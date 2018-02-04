package com.mart.solar.common.world.gen.structures;

import com.mart.solar.common.registry.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.IWorldGenerator;

import javax.vecmath.Vector2d;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StructForgottenAltar implements IWorldGenerator {

    private List<Vector2d> ruinLocations = new ArrayList<>();

    public StructForgottenAltar(){
        this.ruinLocations.add(new Vector2d(5, 0));
        this.ruinLocations.add(new Vector2d(4, 2));
        this.ruinLocations.add(new Vector2d(2, 4));
        this.ruinLocations.add(new Vector2d(0, 5));
        this.ruinLocations.add(new Vector2d(-2, 4));
        this.ruinLocations.add(new Vector2d(-4, 3));
        this.ruinLocations.add(new Vector2d(-5, 0));
        this.ruinLocations.add(new Vector2d(-4, -3));
        this.ruinLocations.add(new Vector2d(-3, -4));
        this.ruinLocations.add(new Vector2d(0, -5));
        this.ruinLocations.add(new Vector2d(3, -4));
        this.ruinLocations.add(new Vector2d(4, -2));
    }

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
                        spawnAltarRuins(world, randomX, y+1, randomZ);
                        return;
                    }
                }

            }
        }
    }

    private void spawnAltarRuins(World world, int x, int y, int z){
        Random random = new Random();
        for(Vector2d vec : this.ruinLocations){
            int yCoord = y;
            BlockPos checkPos = new BlockPos(x + vec.getX(), yCoord-1, z + vec.getY());

            while(world.getBlockState(checkPos).getBlock() == Blocks.AIR ||
                    world.getBlockState(checkPos).getBlock() == Blocks.WATER ||
                    world.getBlockState(checkPos).getBlock() == Blocks.FLOWING_WATER){
                yCoord--;
            }

            int amountOfBlocks = random.nextInt(6);
            for(int i = 0; i < amountOfBlocks; i++){
                if(random.nextInt(2) == 0){
                    world.setBlockState(new BlockPos(x + vec.getX(), yCoord + i, z + vec.getY()), Blocks.COBBLESTONE.getDefaultState());
                }
                else{
                    world.setBlockState(new BlockPos(x + vec.getX(), yCoord + i, z + vec.getY()), Blocks.MOSSY_COBBLESTONE.getDefaultState());
                }
            }
        }
    }
}
