package com.mart.solar.blocks;

import com.mart.solar.Solar;
import com.mart.solar.api.interfaces.ITotemManipulator;
import com.mart.solar.items.ItemRitualStaff;
import com.mart.solar.tileentities.TileTotem;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockTotem extends BlockBase implements ITileEntityProvider {

    public BlockTotem(String name) {
        super(Material.WOOD, name);
        setCreativeTab(Solar.solarTab);

        this.setHardness(2.0F);
        this.setSoundType(SoundType.WOOD);
    }


    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {

            if (hand == EnumHand.OFF_HAND) {
                return true;
            }

            TileTotem tileEntity = (TileTotem) world.getTileEntity(pos);

            if (tileEntity == null || player.isSneaking())
                return false;

            ItemStack playerItem = player.inventory.getCurrentItem();

            if (playerItem.getItem() != Items.AIR) {
                if (playerItem.getItem() instanceof ITotemManipulator) {
                    if (playerItem.getItem() instanceof ItemRitualStaff) {
                        tileEntity.checkForMenhirs(tileEntity.getPos(), tileEntity.getWorld(), player);
                        return true;
                    }
                    playerItem.getItem().onItemRightClick(world, player, hand);
                    return true;
                }
            }

            player.sendMessage(new TextComponentString("" + tileEntity.getSolarEnergy() + "/" + tileEntity.getLunarEnergy()));


        }

        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileTotem();
    }
}
