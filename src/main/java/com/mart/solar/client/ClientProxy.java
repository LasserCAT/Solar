package com.mart.solar.client;

import com.mart.solar.client.render.RenderAltar;
import com.mart.solar.client.render.RenderInfuser;
import com.mart.solar.client.render.RenderMenhir;
import com.mart.solar.common.tileentities.TileAltar;
import com.mart.solar.common.tileentities.TileMenhir;
import com.mart.solar.common.tileentities.TileRuneInfuser;
import com.mart.solar.common.CommonProxy;
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
        ClientRegistry.bindTileEntitySpecialRenderer(TileAltar.class, new RenderAltar());
        ClientRegistry.bindTileEntitySpecialRenderer(TileMenhir.class, new RenderMenhir());
    }


}
