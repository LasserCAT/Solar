package com.mart.solar.common.blocks;

import com.mart.solar.Solar;
import com.mart.solar.common.blocks.base.BlockEnum;
import com.mart.solar.common.blocks.enums.EnumSunBurnt;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockSunburnt extends BlockEnum<EnumSunBurnt> {

    public BlockSunburnt() {
        super(Material.ROCK, EnumSunBurnt.class);

        setUnlocalizedName(Solar.MODID + ".");
        setCreativeTab(Solar.solarTab);
        setHardness(2.0F);
        setResistance(5.0F);
        setSoundType(SoundType.STONE);
        setHarvestLevel("pickaxe", 1);
    }
}
