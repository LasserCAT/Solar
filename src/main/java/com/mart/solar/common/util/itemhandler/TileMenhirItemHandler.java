package com.mart.solar.common.util.itemhandler;

import com.mart.solar.common.registry.ModItems;
import com.mart.solar.common.tileentities.TileMenhir;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;

public class TileMenhirItemHandler extends ItemStackHandler {

    private TileMenhir tile;

    public TileMenhirItemHandler(TileMenhir tile){
        super(1);
        this.tile = tile;
    }

    @Nonnull
    @Override
    public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
        if(slot != 0){
            return stack;
        }

        if(!this.getStackInSlot(0).isEmpty()){
            return stack;
        }

        if(stack.getItem() != ModItems.RUNES){
            return stack;
        }

        if(this.getStackInSlot(0).getCount() >= 1){
            return stack;
        }

        ItemStack returnStack = super.insertItem(slot, stack, simulate);
        tile.notifyUpdate();
        return returnStack;
    }

    @Nonnull
    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        return ItemStack.EMPTY;
    }

    public ItemStack extractPlayer(){
        return super.extractItem(0, 1, false);
    }
}
