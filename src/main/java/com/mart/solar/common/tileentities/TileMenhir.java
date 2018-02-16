package com.mart.solar.common.tileentities;

import com.mart.solar.api.interfaces.IRune;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ITickable;

public class TileMenhir extends TileBase implements ITickable {

    private ItemStack rune = ItemStack.EMPTY;

    @Override
    public void update() {

    }

    public void addRune(ItemStack heldItem, EntityPlayer player, EnumHand hand) {
        if (rune.isEmpty()) {
            if (heldItem.getItem() instanceof IRune) {
                ItemStack heldItem2 = heldItem.copy();

                heldItem2.setCount(1);
                this.rune = heldItem2;

                heldItem.setCount(heldItem.getCount() - 1);
                player.setHeldItem(hand, heldItem);
            }
        }
        notifyUpdate();
    }

    public void extractItem(EntityPlayer player) {
        if (!rune.isEmpty()) {
            player.inventory.addItemStackToInventory(rune);
            this.rune = ItemStack.EMPTY;
        }
        notifyUpdate();
    }

    public void emptyRuneSlot(){
        this.rune = ItemStack.EMPTY;
        notifyUpdate();
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);

        NBTTagList tagList = new NBTTagList();
        NBTTagCompound itemCompound = new NBTTagCompound();
        this.rune.writeToNBT(itemCompound);
        tagList.appendTag(itemCompound);
        compound.setTag("rune", tagList);

        return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);

        NBTTagList tagList = (NBTTagList) compound.getTag("rune");
        NBTTagCompound tagCompound = tagList.getCompoundTagAt(0);
        this.rune = new ItemStack(tagCompound);

    }

    public ItemStack getRune() {
        return rune;
    }
}
