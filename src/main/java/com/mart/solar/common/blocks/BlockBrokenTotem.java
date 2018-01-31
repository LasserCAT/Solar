package com.mart.solar.common.blocks;

import com.mart.solar.Solar;
import com.mart.solar.common.tileentities.TileBrokenTotem;
import com.mart.solar.common.world.data.InteractedWithAltarData;
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

import java.util.Random;

public class BlockBrokenTotem extends BlockBase implements ITileEntityProvider {

    private final String firstUseString = "It seems the altar has received a lot of corrosion damage from the weather " +
            "over thousands of years. The wood and metal bits have been compromised to such an extent that trying to " +
            "move the broken altar will make it crumble into nothing. Maybe I should repair it first with some wood, " +
            "gold and silver.";

    public BlockBrokenTotem(String name) {
        super(Material.WOOD, name);
        setCreativeTab(Solar.solarTab);

        this.setHardness(2.0F);
        this.setSoundType(SoundType.WOOD);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileBrokenTotem();
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {

            InteractedWithAltarData data = InteractedWithAltarData.get(world);
            if(!data.getUUIDList().contains(player.getUniqueID())){
                data.addUUID(player.getUniqueID());
                System.out.println(player.getUniqueID());
                player.sendMessage(new TextComponentString(this.firstUseString));
            }

            ItemStack heldItem = player.getHeldItem(hand);

            TileBrokenTotem tileEntity = (TileBrokenTotem) world.getTileEntity(pos);
            if (tileEntity == null || player.isSneaking())
                return false;

            if (heldItem.getItem() != Items.AIR) {
                tileEntity.useItem(heldItem, player, hand, pos);
            }

        }
        return true;
    }

    @Override
    public int quantityDropped(Random random) {
        return 0;
    }
}