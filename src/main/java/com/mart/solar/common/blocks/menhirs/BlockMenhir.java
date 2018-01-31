package com.mart.solar.common.blocks.menhirs;

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
import net.minecraft.world.World;

public class BlockMenhir extends BlockBase implements ITileEntityProvider {

    CircleTypes type = null;

    public BlockMenhir(String name) {
        super(Material.ROCK, name);
        setCreativeTab(Solar.solarTab);
    }

    public CircleTypes getType() {
        return type;
    }

    public void setType(CircleTypes type) {
        this.type = type;
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            if (hand.equals(EnumHand.MAIN_HAND)) {
                TileMenhir tileEntity = (TileMenhir) world.getTileEntity(pos);
                ItemStack heldItem = player.getHeldItem(hand);

                if (tileEntity == null || player.isSneaking())
                    return false;

                if (heldItem.getItem() != Items.AIR) {
                    tileEntity.addRune(heldItem, player, hand);
                } else {
                    player.swingArm(hand);
                    tileEntity.extractItem(player, hand);
                }
            }
        }
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileMenhir();
    }
}
