package com.mart.solar.common.tileentities;

import com.mart.solar.api.interfaces.IRune;
import com.mart.solar.common.util.itemhandler.TileMenhirItemHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;

import javax.annotation.Nullable;

public class TileMenhir extends TileBase implements ITickable {

    private TileMenhirItemHandler itemStackHandler;

    public TileMenhir(){
        this.itemStackHandler = new TileMenhirItemHandler(this);
    }

    @Override
    public void update() {

    }

    public void addRune(ItemStack heldItem, EntityPlayer player, EnumHand hand) {

        ItemStack returnStack = this.itemStackHandler.insertItem(0, heldItem, false);

        if(returnStack == heldItem){
            return;
        }
        else{
            if(returnStack.isEmpty()){
                if(heldItem.getCount() <= 1){
                    player.setHeldItem(hand, ItemStack.EMPTY);
                }
                else{
                    heldItem.setCount(heldItem.getCount()-1);
                }
            }
        }

        notifyUpdate();
    }

    public void extractItem(EntityPlayer player) {
        player.addItemStackToInventory(this.itemStackHandler.extractPlayer());
        notifyUpdate();
    }

    public void emptyRuneSlot(){
        this.itemStackHandler.setStackInSlot(0, ItemStack.EMPTY);
        notifyUpdate();
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);

        compound.setTag("itemStackHandler", this.itemStackHandler.serializeNBT());

        return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);

        this.itemStackHandler.deserializeNBT(compound.getCompoundTag("itemStackHandler"));
    }

    //Capability
    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return true;
        }
        return super.hasCapability(capability, facing);
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return (T) this.itemStackHandler;
        }
        return super.getCapability(capability, facing);
    }

    public TileMenhirItemHandler getItemStackHandler() {
        return itemStackHandler;
    }
}
