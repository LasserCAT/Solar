package com.mart.solar.common.blocks;

import com.mart.solar.Solar;
import com.mart.solar.api.interfaces.IAltarManipulator;
import com.mart.solar.common.blocks.base.BlockBase;
import com.mart.solar.common.recipes.AltarRecipe;
import com.mart.solar.common.recipes.AltarRecipeManager;
import com.mart.solar.common.registry.ModItems;
import com.mart.solar.common.tileentities.TileAltar;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockAltar extends BlockBase {

    public BlockAltar(String name) {
        super(Material.WOOD, name);
        setCreativeTab(Solar.solarTab);

        this.setHardness(2.0F);
        this.setSoundType(SoundType.WOOD);
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
            TileAltar tileEntity = (TileAltar) world.getTileEntity(pos);
            ItemStack playerItem = player.inventory.getCurrentItem();

            if (hand == EnumHand.OFF_HAND || tileEntity == null) {
                return true;
            }

            if (player.isSneaking()) {
                tileEntity.retrieveItem(player);
                return true;
            }

            if(playerItem.isEmpty()){
                return true;
            }


            if (playerItem.getItem() instanceof IAltarManipulator) {
                if (playerItem.getItem() == ModItems.ritualAmulet) {
                    tileEntity.useRitualAmulet(tileEntity.getPos(), tileEntity.getWorld(), player);
                    return true;
                }
            }

            AltarRecipe altarRecipe = AltarRecipeManager.findMatchingInput(playerItem.getItem());
            if(altarRecipe != null){
                tileEntity.startAltarRecipe(altarRecipe, player, playerItem, hand);
            }

            player.sendMessage(new TextComponentString("" + tileEntity.getSolarEnergy() + "/" + tileEntity.getLunarEnergy()));
        }

        return true;
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileAltar();
    }
}
