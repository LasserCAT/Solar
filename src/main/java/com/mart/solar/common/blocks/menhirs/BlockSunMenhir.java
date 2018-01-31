package com.mart.solar.common.blocks.menhirs;

import com.mart.solar.api.enums.CircleTypes;

public class BlockSunMenhir extends BlockMenhir {
    public BlockSunMenhir(String name) {
        super(name);
        type = CircleTypes.SUN;
    }
}
