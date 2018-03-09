package com.mart.solar.common.blocks.base;

import com.mart.solar.Solar;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;

public class BlockBase extends Block {

    protected String registryName;

    public BlockBase(Material material, String registryName) {
        super(material);

        this.registryName = registryName;

        setUnlocalizedName(Solar.MODID + "." + registryName);
        setRegistryName(registryName);
    }

    public void registerItemModel(ItemBlock itemBlock) {
        Solar.proxy.registerItemRenderer(itemBlock, 0);
    }


}