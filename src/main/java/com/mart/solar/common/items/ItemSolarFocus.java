package com.mart.solar.common.items;

import com.mart.solar.Solar;
import com.mart.solar.common.items.base.ItemBase;

public class ItemSolarFocus extends ItemBase {

    public ItemSolarFocus(String registryName) {
        super(registryName);
        setCreativeTab(Solar.solarTab);
    }

}
