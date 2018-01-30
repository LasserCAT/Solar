package com.mart.solar.proxy;

import com.mart.solar.Solar;
import com.mart.solar.client.render.RenderInfuser;
import com.mart.solar.tileentities.TileRuneInfuser;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {

    @Override
    public void registerItemRenderer(Item item, int meta) {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }

    @Override
    public void preInit() {
        ClientRegistry.bindTileEntitySpecialRenderer(TileRuneInfuser.class, new RenderInfuser());
    }


}
