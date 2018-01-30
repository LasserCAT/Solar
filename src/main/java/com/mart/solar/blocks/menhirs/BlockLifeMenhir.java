package com.mart.solar.blocks.menhirs;

import com.mart.solar.api.enums.CircleTypes;

public class BlockLifeMenhir extends BlockMenhir {
    public BlockLifeMenhir(String name) {
        super(name);
        type = CircleTypes.LIFE;
    }
}
