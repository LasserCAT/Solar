package com.mart.solar.common.blocks;

import com.mart.solar.Solar;
import net.minecraft.block.material.Material;

public class BlockSilverOre extends BlockBase {

    public BlockSilverOre(String name) {
        super(Material.ROCK, name);
        setCreativeTab(Solar.solarTab);
    }


}
