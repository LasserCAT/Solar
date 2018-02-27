package com.mart.solar.common.tileentities;

import com.mart.solar.common.registry.ModBlocks;
import com.mart.solar.common.registry.ModItems;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.oredict.OreDictionary;

public class TileBrokenTotem extends TileBase {

    private int silverIngots = 0;
    private int goldIngots = 0;
    private int woodenBlocks = 0;

    public int getWoodenBlocks() {
        return woodenBlocks;
    }

    public void setWoodenBlocks(int woodenBlocks) {
        this.woodenBlocks = woodenBlocks;
    }

    public int getSilverIngots() {
        return silverIngots;
    }

    public void setSilverIngots(int silverIngots) {
        this.silverIngots = silverIngots;
    }

    public int getGoldIngots() {
        return goldIngots;
    }

    public void setGoldIngots(int goldIngots) {
        this.goldIngots = goldIngots;
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setInteger("silverIngots", silverIngots);
        compound.setInteger("goldIngots", goldIngots);
        compound.setInteger("woodenBlocks", woodenBlocks);
        return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.silverIngots = compound.getInteger("silverIngots");
        this.goldIngots = compound.getInteger("goldIngots");
        this.woodenBlocks = compound.getInteger("woodenBlocks");
    }

    public void useItem(ItemStack heldItem, EntityPlayer player, EnumHand hand, BlockPos pos) {
        if (heldItem.getItem().equals(Items.GOLD_INGOT)) {
            if (goldIngots < 4) {
                setGoldIngots(getGoldIngots() + 1);
                heldItem.setCount(heldItem.getCount() - 1);
                player.setHeldItem(hand, heldItem);
            }
        }

        for (ItemStack i : OreDictionary.getOres("ingotSilver")) {
            if (heldItem.getItem().equals(i.getItem())) {
                if (silverIngots < 4) {
                    setSilverIngots(getSilverIngots() + 1);
                    heldItem.setCount(heldItem.getCount() - 1);
                    player.setHeldItem(hand, heldItem);
                }
            }
        }

        if (heldItem.getItem().equals(ItemBlock.getItemById(5))) {
            if (woodenBlocks < 8) {
                setWoodenBlocks(getWoodenBlocks() + 1);
                heldItem.setCount(heldItem.getCount() - 1);
                player.setHeldItem(hand, heldItem);
            }
        }

        notifyUpdate();

        checkTransform(pos, player);
    }

    public void checkTransform(BlockPos pos, EntityPlayer player) {
        if (getGoldIngots() == 4 && getSilverIngots() == 4 && getWoodenBlocks() == 8) {
            player.getEntityWorld().setBlockState(pos, ModBlocks.blockAltar.getDefaultState());
            if(!this.getWorld().isRemote){
                EntityItem bookItem = new EntityItem(this.getWorld(), pos.getX(), pos.getY()+1, pos.getZ(), new ItemStack(ModItems.JOURNAL) );
                this.getWorld().spawnEntity(bookItem);
            }
        }
    }
}
