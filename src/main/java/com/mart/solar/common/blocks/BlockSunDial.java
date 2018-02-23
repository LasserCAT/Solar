package com.mart.solar.common.blocks;

import com.mart.solar.Solar;
import com.mart.solar.common.blocks.base.BlockBase;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class BlockSunDial extends BlockBase {

    public BlockSunDial(String registryName) {
        super(Material.WOOD, registryName);

        setCreativeTab(Solar.solarTab);

        this.setHardness(4.0F);
        this.setSoundType(SoundType.WOOD);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if(worldIn.isRemote){
            return true;
        }

        long ticks = worldIn.getWorldTime();
        long time = ticks % 24000;
        long hour;

        if(time > 0 && time < 18000){
            hour = 6;
        }
        else{
            hour = 0;
            time -= 18000;
        }

        double extraHour = Math.floor(time / 1000);

        hour += extraHour;

        long minuteTicks = time % 1000;

        double minutes = Math.floor(minuteTicks / 16.6);

        if(minutes < 10){
            playerIn.sendMessage(new TextComponentString(hour + ":0" + (int)minutes));
        }
        else{
            playerIn.sendMessage(new TextComponentString(hour + ":" + (int)minutes));
        }

        return true;
    }
}
