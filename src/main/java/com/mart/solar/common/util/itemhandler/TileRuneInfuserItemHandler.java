package com.mart.solar.common.util.itemhandler;

import com.mart.solar.common.registry.ModItems;
import com.mart.solar.common.tileentities.TileRuneInfuser;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;

public class TileRuneInfuserItemHandler extends ItemStackHandler {

    private final TileRuneInfuser tileRuneInfuser;

    public TileRuneInfuserItemHandler(TileRuneInfuser entity){
        super(2);
        this.tileRuneInfuser = entity;
    }

    @Nonnull
    @Override
    public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
        ItemStack slotStack = getStackInSlot(slot);

        if(!slotStack.isEmpty()){
            return stack;
        }

        if(slot == 0){
            if(stack.getItem() != ModItems.RUNES){
                return stack;
            }

            ItemStack returnItem = super.insertItem(slot, stack, simulate);
            tileRuneInfuser.infuse();
            return returnItem;
        }

        if(slot == 1){
            if(stack.getItem() == ModItems.RUNES){
                return stack;
            }

            ItemStack returnItem = super.insertItem(slot, stack, simulate);
            tileRuneInfuser.infuse();
            return returnItem;
        }

        return super.insertItem(slot, stack, simulate);
    }

    @Nonnull
    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        if(tileRuneInfuser.isInfusing()){
            return ItemStack.EMPTY;
        }

        if(slot == 1){
            return ItemStack.EMPTY;
        }

        if(slot == 0){
           ItemStack rune = this.getStackInSlot(0);
           if(rune.getItemDamage() == 0){
               return ItemStack.EMPTY;
           }
        }

        return super.extractItem(slot, amount, simulate);
    }

    public void extractItemPlayer(int slot, int amount, boolean simulate, EntityPlayer player) {
        if(tileRuneInfuser.isInfusing()){
            return;
        }

        if(slot == 1){
            return;
        }

        ItemStack extractedItem = super.extractItem(slot, amount, simulate);

        player.addItemStackToInventory(extractedItem);

        if(!tileRuneInfuser.getWorld().isRemote){
            tileRuneInfuser.notifyUpdate();
        }
    }

    @Override
    protected void onContentsChanged(int slot) {
        tileRuneInfuser.notifyUpdate();
    }
}