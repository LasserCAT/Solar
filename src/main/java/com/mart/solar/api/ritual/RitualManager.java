package com.mart.solar.api.ritual;

import com.mart.solar.Solar;
import net.minecraft.util.ResourceLocation;
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

    public static Ritual getByRegName(String registryName){
        return REGISTRY.getValue(new ResourceLocation(Solar.MODID, registryName));
    }

}
