package com.mart.solar.common.blocks.base;

import com.mart.solar.Solar;
import net.minecraft.block.BlockBush;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;

public class BlockFlowerBase extends BlockBush{

    protected String registryName;

    public BlockFlowerBase(String registryName) {
        super(Material.PLANTS);
        this.registryName = registryName;

        setUnlocalizedName(registryName);
        setRegistryName(registryName);
        setSoundType(SoundType.PLANT);
    }

    public void registerItemModel(ItemBlock itemBlock) {
        Solar.proxy.registerItemRenderer(itemBlock, 0);
    }


}
