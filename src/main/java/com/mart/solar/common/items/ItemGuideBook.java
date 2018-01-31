package com.mart.solar.common.items;

import com.mart.solar.Solar;
import com.mart.solar.common.network.GuiHandler;
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
        else{
            playerIn.openGui(Solar.instance, GuiHandler.GUIDE_BOOK_ID_GUI, worldIn, 0, 0, 0);
        }

        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
