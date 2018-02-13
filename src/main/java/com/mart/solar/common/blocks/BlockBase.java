package com.mart.solar.common.blocks;

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

        setUnlocalizedName(registryName);
        setRegistryName(registryName);
    }

    public void registerItemModel(ItemBlock itemBlock) {
        Solar.proxy.registerItemRenderer(itemBlock, 0);
    }

    @Override
    public BlockBase setCreativeTab(CreativeTabs tab) {
        super.setCreativeTab(tab);
        return this;
    }

}