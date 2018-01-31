package com.mart.solar.common.creativetabs;

import com.mart.solar.Solar;
import com.mart.solar.common.registry.ModItems;
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
