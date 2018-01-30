package com.mart.solar.creativetabs;

import com.mart.solar.Solar;
import com.mart.solar.registry.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class SolarTab extends CreativeTabs {

    public SolarTab() {
        super(Solar.MODID);
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(ModItems.RUNES);
    }
}
