package com.mart.solar.common.registry;

import com.mart.solar.Solar;
import com.mart.solar.common.tileentities.*;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModTiles {

    static TileBrokenTotem brokenTotem = new TileBrokenTotem();
    static TileAltar totem = new TileAltar();
    static TileRuneInfuser runeInfuser = new TileRuneInfuser();
    static TileMenhir menhir = new TileMenhir();
    static TileRitualStone ritualStone = new TileRitualStone();

    public static void init() {
        GameRegistry.registerTileEntity(brokenTotem.getClass(), Solar.MODID + ":tiletotem");
        GameRegistry.registerTileEntity(totem.getClass(), Solar.MODID + ":totem");
        GameRegistry.registerTileEntity(runeInfuser.getClass(), Solar.MODID + ":runeinfuser");
        GameRegistry.registerTileEntity(menhir.getClass(), Solar.MODID + ":menhir");
        GameRegistry.registerTileEntity(ritualStone.getClass(), Solar.MODID + ":ritualstone");
    }
}
