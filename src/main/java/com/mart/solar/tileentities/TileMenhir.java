package com.mart.solar.tileentities;

import com.mart.solar.api.interfaces.IRune;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumHand;

public class TileMenhir extends TileBase {

    private ItemStack rune = null;

    public void addRune(ItemStack heldItem, EntityPlayer player, EnumHand hand) {
        if (rune == null) {
            if (heldItem.getItem() instanceof IRune) {
                ItemStack heldItem2 = heldItem.copy();

                heldItem2.setCount(1);
                setRune(heldItem2);

                heldItem.setCount(heldItem.getCount() - 1);
                player.setHeldItem(hand, heldItem);
            }
        }
        notifyUpdate();
    }

    public void extractItem(EntityPlayer player, EnumHand hand) {
        if (rune != null) {
            player.inventory.addItemStackToInventory(rune);
            setRune(null);
        }
        notifyUpdate();
    }

    public ItemStack getRune() {
        return rune;
    }

    public void setRune(ItemStack rune) {
        this.rune = rune;
    }

    //Data
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);

        if (rune != null) {
            NBTTagList tagList = new NBTTagList();
            NBTTagCompound itemCompound = new NBTTagCompound();
            rune.writeToNBT(itemCompound);
            tagList.appendTag(itemCompound);
            compound.setTag("rune", tagList);
            compound.setBoolean("runeAvailable", true);
        } else {
            compound.setBoolean("runeAvailable", false);
        }

        return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);

        if (compound.getBoolean("runeAvailable")) {
            NBTTagList tagList = (NBTTagList) compound.getTag("rune");
            NBTTagCompound tagCompound = tagList.getCompoundTagAt(0);
            rune = new ItemStack(tagCompound);
        } else {
            rune = null;
        }

    }


}
