package com.mart.solar.common.registry;

import com.mart.solar.Solar;
import com.mart.solar.common.tileentities.TileBrokenTotem;
import com.mart.solar.common.tileentities.TileMenhir;
import com.mart.solar.common.tileentities.TileRuneInfuser;
import com.mart.solar.common.tileentities.TileAltar;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModTiles {

    static TileBrokenTotem brokenTotem = new TileBrokenTotem();
    static TileAltar totem = new TileAltar();
    static TileRuneInfuser runeInfuser = new TileRuneInfuser();
    static TileMenhir menhir = new TileMenhir();

    public static void init() {
        GameRegistry.registerTileEntity(brokenTotem.getClass(), Solar.MODID + "tileTotem");
        GameRegistry.registerTileEntity(totem.getClass(), Solar.MODID + "totem");
        GameRegistry.registerTileEntity(runeInfuser.getClass(), Solar.MODID + "runeInfuser");
        GameRegistry.registerTileEntity(menhir.getClass(), Solar.MODID + "menhir");
    }
}
