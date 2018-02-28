package com.mart.solar.common.blocks;

import com.mart.solar.Solar;
import com.mart.solar.common.blocks.base.BlockFlowerBase;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class BlockFlowerFiery extends BlockFlowerBase {

    public BlockFlowerFiery(String registryName) {
        super(registryName);
        setCreativeTab(Solar.solarTab);
    }

    @Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
        IBlockState soil = worldIn.getBlockState(pos.down());
        return soil.getBlock() == Blocks.SAND || soil.getBlock() == Blocks.NETHERRACK || soil.getBlock() == Blocks.SOUL_SAND;
    }

    @Override
    public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state) {
        if (state.getBlock() == this) //Forge: This function is called during world gen and placement, before this block is set, so if we are not 'here' then assume it's the pre-check.
        {
            return canPlaceBlockAt(worldIn, pos);
        }
        return this.canSustainBush(worldIn.getBlockState(pos.down()));
    }

    @Override
    protected boolean canSustainBush(IBlockState state) {
        return state.getBlock() == Blocks.SAND || state.getBlock() == Blocks.NETHERRACK || state.getBlock() == Blocks.SOUL_SAND;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Items.BLAZE_POWDER;
    }

    @Override
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        int dropCount = 0;
        if(world.getBiome(pos) == Biomes.DESERT || world.getBiome(pos) == Biomes.DESERT){
            Random random = new Random();
            dropCount = random.nextInt(2);
        }
        else if(world.getBiome(pos) == Biomes.HELL){
            Random random = new Random();
            dropCount = random.nextInt(2) + 1;
        }

        if(dropCount > 0){
            drops.add(new ItemStack(Items.BLAZE_POWDER, dropCount));
        }
    }

    @Override
    public int quantityDropped(Random random) {
        return 0;
    }
}
