package com.mart.solar.common.items;

import com.mart.solar.Solar;
import com.mart.solar.common.items.base.ItemBase;

public class ItemLunarFocus extends ItemBase {

    public ItemLunarFocus(String registryName) {
        super(registryName);
        setCreativeTab(Solar.solarTab);
    }
}
