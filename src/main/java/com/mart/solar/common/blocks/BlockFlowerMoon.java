package com.mart.solar.common.blocks;

import com.mart.solar.Solar;
import com.mart.solar.common.blocks.base.BlockFlowerBase;
import com.mart.solar.common.util.CommonUtils;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class BlockFlowerMoon extends BlockFlowerBase {

    public BlockFlowerMoon(String registryName) {
        super(registryName);
        setCreativeTab(Solar.solarTab);
    }

    @Override
    public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune) {
        if(CommonUtils.isDay(worldIn)){
            return;
        }
        super.dropBlockAsItemWithChance(worldIn, pos, state, chance, fortune);
    }


}
