package com.mart.solar.common.blocks;

import com.mart.solar.Solar;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockSilverOre extends BlockBase {

    public BlockSilverOre(String name) {
        super(Material.ROCK, name);

        setHardness(3.0F);
        setResistance(5.0F);
        setSoundType(SoundType.STONE);

        setCreativeTab(Solar.solarTab);
    }


}
