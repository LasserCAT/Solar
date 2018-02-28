package com.mart.solar.common.util;

import com.mart.solar.common.recipes.AltarRecipe;
import com.mart.solar.common.tileentities.TileAltar;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;

public class TileAltarItemHandler extends ItemStackHandler {

    private TileAltar tileAltar;

    public TileAltarItemHandler(TileAltar tileAltar){
        super(1);
        this.tileAltar = tileAltar;
    }

    @Nonnull
    @Override
    public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
        AltarRecipe altarRecipe = tileAltar.isAltarRecipe(stack);

        if(altarRecipe == null){
            return stack;
        }

        ItemStack item = this.getStackInSlot(slot);

        if(!item.isEmpty()){
            return stack;
        }

        tileAltar.startAltarRecipe(altarRecipe);

        return super.insertItem(slot, stack, simulate);
    }

    @Nonnull
    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        if(tileAltar.isRecipeInProgress()){
            return ItemStack.EMPTY;
        }

        if(!tileAltar.getWorld().isRemote){
            tileAltar.notifyUpdate();
        }

        return super.extractItem(slot, amount, simulate);
    }
}
