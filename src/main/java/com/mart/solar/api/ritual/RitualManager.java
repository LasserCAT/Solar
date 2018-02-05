package com.mart.solar.api.ritual;

import net.minecraftforge.registries.IForgeRegistry;

import java.util.List;

public class RitualManager {

    private static IForgeRegistry<Ritual> REGISTRY;

    public static void setRegistry(IForgeRegistry<Ritual> registry) {
        REGISTRY = registry;
    }

    public static List<Ritual> getRituals() {
        return REGISTRY.getValues();
    }

}
