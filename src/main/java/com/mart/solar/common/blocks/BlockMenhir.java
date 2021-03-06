package com.mart.solar.common.blocks;

import com.mart.solar.Solar;
import com.mart.solar.common.blocks.base.BlockBase;
import com.mart.solar.common.tileentities.TileMenhir;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockMenhir extends BlockBase {

    private final AxisAlignedBB menhirAabb = new AxisAlignedBB(0.2D, 0.0D, 0.2D, 0.8D, 0.45D, 0.8D);

    public BlockMenhir(String name) {
        super(Material.ROCK, name);
        setCreativeTab(Solar.solarTab);

        setHardness(3.0F);
        setResistance(5.0F);
        setSoundType(SoundType.STONE);

        setHarvestLevel("pickaxe", 0);
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
    public void breakBlock(World world, BlockPos pos, IBlockState state) {
        TileMenhir tileEntity = (TileMenhir) world.getTileEntity(pos);

        if (world.isRemote || tileEntity == null) {
            return;
        }

        if (tileEntity.getItemStackHandler().getStackInSlot(0).isEmpty()) {
            return;
        }

        double d0 = (double) (world.rand.nextFloat() * 0.5F) + 0.25D;
        double d1 = (double) (world.rand.nextFloat() * 0.5F) + 0.25D;
        double d2 = (double) (world.rand.nextFloat() * 0.5F) + 0.25D;
        EntityItem dropItem = new EntityItem(world, (double) pos.getX() + d0, (double) pos.getY() + d1, (double) pos.getZ() + d2, tileEntity.getItemStackHandler().getStackInSlot(0));
        dropItem.setDefaultPickupDelay();
        world.spawnEntity(dropItem);
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileMenhir();
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return menhirAabb;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    @Deprecated
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
        return BlockFaceShape.UNDEFINED;
    }
}
