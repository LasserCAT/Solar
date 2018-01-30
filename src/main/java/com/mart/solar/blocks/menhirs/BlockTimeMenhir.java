package com.mart.solar.blocks.menhirs;

import com.mart.solar.api.enums.CircleTypes;

public class BlockTimeMenhir extends BlockMenhir {
    public BlockTimeMenhir(String name) {
        super(name);
        type = CircleTypes.TIME;
    }
}
