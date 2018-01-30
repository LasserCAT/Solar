package com.mart.solar.items;

import com.mart.solar.Solar;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemGuideBook extends ItemBase {

    public ItemGuideBook() {
        super("guidebook");
        setCreativeTab(Solar.solarTab);
        setMaxStackSize(1);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if(!worldIn.isRemote){
            System.out.println("Right Clicked");
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
