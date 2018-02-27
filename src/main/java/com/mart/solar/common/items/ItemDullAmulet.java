package com.mart.solar.common.items;

import com.mart.solar.Solar;
import com.mart.solar.api.interfaces.IRune;

public class ItemDullAmulet extends ItemBase implements IRune{

    public ItemDullAmulet(String registryName) {
        super(registryName);
        setCreativeTab(Solar.solarTab);
        setMaxStackSize(1);
    }
}
