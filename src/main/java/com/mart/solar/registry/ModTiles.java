package com.mart.solar.registry;

import com.mart.solar.Solar;
import com.mart.solar.tileentities.TileBrokenTotem;
import com.mart.solar.tileentities.TileMenhir;
import com.mart.solar.tileentities.TileRuneInfuser;
import com.mart.solar.tileentities.TileTotem;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModTiles {

    static TileBrokenTotem brokenTotem = new TileBrokenTotem();
    static TileTotem totem = new TileTotem();
    static TileRuneInfuser runeInfuser = new TileRuneInfuser();
    static TileMenhir menhir = new TileMenhir();

    public static void init() {
        GameRegistry.registerTileEntity(brokenTotem.getClass(), Solar.MODID + "tileTotem");
        GameRegistry.registerTileEntity(totem.getClass(), Solar.MODID + "totem");
        GameRegistry.registerTileEntity(runeInfuser.getClass(), Solar.MODID + "runeInfuser");
        GameRegistry.registerTileEntity(menhir.getClass(), Solar.MODID + "menhir");
    }
}
