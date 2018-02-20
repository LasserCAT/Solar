package com.mart.solar.common.world.gen.structures;

import com.mart.solar.common.registry.ModBlocks;
import com.mart.solar.common.world.gen.ISolarWorldGen;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;

import javax.vecmath.Vector2d;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StructForgottenAltar implements ISolarWorldGen {

    private List<Vector2d> ruinLocations = new ArrayList<>();

    private Vector2d lastChunkGenerated = new Vector2d(0, 0);
    private int chunksNotSpawned = 0;

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
    public void generateOverworld(World world, Random rand, int x, int z) {
        int randomX = x + rand.nextInt(16);
        int randomZ = z + rand.nextInt(16);
        int randomY = 90;
        int chunkX = x / 16;
        int chunkZ = z / 16;

        BlockPos blockpos = new BlockPos(randomX, randomY, randomZ);
        Biome biome = world.getBiomeForCoordsBody(blockpos);

        if (!BiomeDictionary.hasType(biome, BiomeDictionary.Type.PLAINS) && !BiomeDictionary.hasType(biome, BiomeDictionary.Type.SANDY) && !BiomeDictionary.hasType(biome, BiomeDictionary.Type.SAVANNA)) {
            return;
        }

        if(!isNumberAwayOf(5, chunkX, (int) this.lastChunkGenerated.x) || !isNumberAwayOf(5, chunkZ, (int) this.lastChunkGenerated.y)){
            return;
        }

        int chance = rand.nextInt(200) + 1;
        if (chance != 1) {
            chunksNotSpawned++;

            if(chunksNotSpawned < 100){
                return;
            }

            chunksNotSpawned = 0;
        }

        this.lastChunkGenerated = new Vector2d(chunkX, chunkZ);

        for (int y = 255; y > 0; y--) {
            Block block = world.getBlockState(new BlockPos(randomX, y, randomZ)).getBlock();
            if (block != Blocks.AIR &&
                    block != Blocks.WATER &&
                    block != Blocks.FLOWING_WATER &&
                    block != Blocks.LEAVES &&
                    block != Blocks.LEAVES2 &&
                    block != Blocks.LAVA &&
                    block != Blocks.TALLGRASS) {
                world.setBlockState(new BlockPos(randomX, y + 1, randomZ), ModBlocks.brokenTotem.getDefaultState());
                spawnAltarRuins(world, randomX, y+1, randomZ);
                return;
            }
        }
    }

    @Override
    public void generateNether(World world, Random rand, int chunkX, int chunkZ) {

    }

    @Override
    public void generateEnd(World world, Random rand, int chunkX, int chunkZ) {

    }

    private void spawnAltarRuins(World world, int x, int y, int z){
        Random random = new Random();
        for(Vector2d vec : this.ruinLocations){
            int yCoord = y;
            BlockPos checkPos = new BlockPos(x + vec.getX(), yCoord-1, z + vec.getY());

            while(world.getBlockState(checkPos).getBlock() == Blocks.AIR ||
                    world.getBlockState(checkPos).getBlock() == Blocks.WATER ||
                    world.getBlockState(checkPos).getBlock() == Blocks.FLOWING_WATER ||
                    world.getBlockState(checkPos).getBlock() == Blocks.LEAVES ||
                    world.getBlockState(checkPos).getBlock() == Blocks.LEAVES2 ||
                    world.getBlockState(checkPos).getBlock() == Blocks.LAVA ||
                    world.getBlockState(checkPos).getBlock() == Blocks.TALLGRASS){
                yCoord--;

                if(yCoord <= 0){
                    yCoord = 1;
                    break;
                }
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

    private boolean isNumberAwayOf(int difference, int number1, int number2){
        if(number1 > number2){
            if(number1 - number2 > difference){
                return true;
            }
        }
        else{
            if(number2 - number1 > 5){
                return true;
            }
        }

        return false;
    }
}
