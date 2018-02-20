package com.mart.solar.common.blocks;

import com.mart.solar.Solar;
import com.mart.solar.common.blocks.base.BlockFlowerBase;

public class BlockFlowerMoon extends BlockFlowerBase {

    public BlockFlowerMoon(String registryName) {
        super(registryName);
        setCreativeTab(Solar.solarTab);
    }
}
