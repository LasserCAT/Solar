package com.mart.solar.common.util;

import com.mart.solar.common.registry.ModItems;
import com.mart.solar.common.tileentities.TileRuneInfuser;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;

public class TileRuneInfuserItemHandler extends ItemStackHandler {

    private final TileRuneInfuser entity;

    public TileRuneInfuserItemHandler(TileRuneInfuser entity){
        super(2);
        this.entity = entity;
    }

    @Nonnull
    @Override
    public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
        if(slot == 0){
            if(stack.getItem() != ModItems.RUNES){
                return stack;
            }

            ItemStack slotStack = getStackInSlot(slot);

            if(!slotStack.isEmpty()){
                return stack;
            }

            return super.insertItem(slot, stack, simulate);
        }

        if(slot == 1){
            ItemStack slotStack = getStackInSlot(slot);

            if(!slotStack.isEmpty()){
                return stack;
            }

            return super.insertItem(slot, stack, simulate);
        }

        return super.insertItem(slot, stack, simulate);
    }

    @Override
    protected void onContentsChanged(int slot) {
        entity.notifyUpdate();
    }
}