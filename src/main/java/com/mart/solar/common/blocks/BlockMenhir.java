package com.mart.solar.common.blocks;

import com.mart.solar.Solar;
import com.mart.solar.api.enums.CircleTypes;
import com.mart.solar.common.blocks.BlockBase;
import com.mart.solar.common.tileentities.TileMenhir;
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

public class BlockMenhir extends BlockBase implements ITileEntityProvider {

    private CircleTypes type = null;

    public BlockMenhir(String name) {
        super(Material.ROCK, name);
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

    public CircleTypes getType() {
        return type;
    }

    public void setType(CircleTypes type) {
        this.type = type;
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        TileMenhir tileEntity = (TileMenhir) world.getTileEntity(pos);
        ItemStack heldItem = player.getHeldItem(hand);

        if (world.isRemote || tileEntity == null) {
            return true;
        }

        if (player.isSneaking()){
            player.swingArm(hand);
            tileEntity.extractItem(player);
            return true;
        }

        if (!heldItem.isEmpty()) {
            tileEntity.addRune(heldItem, player, hand);
            return true;
        }

        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileMenhir();
    }
}
