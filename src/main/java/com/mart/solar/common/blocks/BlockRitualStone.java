package com.mart.solar.common.blocks;

import com.mart.solar.Solar;
import com.mart.solar.api.interfaces.IAltarManipulator;
import com.mart.solar.common.recipes.AltarRecipe;
import com.mart.solar.common.recipes.AltarRecipeManager;
import com.mart.solar.common.registry.ModItems;
import com.mart.solar.common.tileentities.TileRitualStone;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockRitualStone extends BlockBase implements ITileEntityProvider {

    public BlockRitualStone() {
        super(Material.ROCK, "ritualstone");
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
        if (world.isRemote) {
            return true;
        }

        System.out.println(1);

        TileRitualStone tileEntity = (TileRitualStone) world.getTileEntity(pos);
        ItemStack playerItem = player.inventory.getCurrentItem();

        System.out.println(2);

        if (hand == EnumHand.OFF_HAND || tileEntity == null) {
            System.out.println(3);
            return true;
        }

        if (player.isSneaking()) {
            System.out.println(4);
            return true;
        }

        if (playerItem.isEmpty()) {
            System.out.println(5);
            return true;
        }


        if (playerItem.getItem() instanceof IAltarManipulator) {
            System.out.println(6);
            if (playerItem.getItem() == ModItems.ritualAmulet) {
                System.out.println(7);
                tileEntity.poop();
                return true;
            }
        }

        System.out.println(8);
        return true;
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileRitualStone();
    }
}