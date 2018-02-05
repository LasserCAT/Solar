package com.mart.solar.api.spell;

import net.minecraftforge.registries.IForgeRegistry;

import java.util.List;

public class SpellManager {

    private static IForgeRegistry<Spell> REGISTRY;

    public static void setRegistry(IForgeRegistry<Spell> registry) {
        REGISTRY = registry;
    }

    public static List<Spell> getRituals() {
        return REGISTRY.getValues();
    }

}
