package com.mart.solar.common.blocks;

import com.mart.solar.Solar;
import com.mart.solar.common.tileentities.TileRuneInfuser;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockRuneInfuser extends BlockBase implements ITileEntityProvider {

    public BlockRuneInfuser(String name) {
        super(Material.WOOD, name);
        setCreativeTab(Solar.solarTab);
    }

    @Override
    @Deprecated
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isNormalCube(IBlockState state, IBlockAccess world, BlockPos pos) {
        return false;
    }


    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            if (hand.equals(EnumHand.MAIN_HAND)) {
                TileRuneInfuser tileEntity = (TileRuneInfuser) world.getTileEntity(pos);
                ItemStack heldItem = player.getHeldItem(hand);

                if (tileEntity == null)
                    return true;

                if(heldItem.isEmpty() && player.isSneaking()){
                    player.swingArm(hand);
                    tileEntity.extractItem(player);
                    return true;
                }

                if (!heldItem.isEmpty()) {
                    tileEntity.onUse(heldItem, player, hand);
                }
            }
        }
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileRuneInfuser();
    }
}
