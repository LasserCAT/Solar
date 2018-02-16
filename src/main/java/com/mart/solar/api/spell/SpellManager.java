package com.mart.solar.api.spell;

import com.mart.solar.Solar;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.List;

public class SpellManager {

    private static IForgeRegistry<Spell> REGISTRY;

    public static void setRegistry(IForgeRegistry<Spell> registry) {
        REGISTRY = registry;
    }

    public static List<Spell> getSpells() {
        return REGISTRY.getValues();
    }

    public static Spell getSpellByName(String registrationName){
        return REGISTRY.getValue(new ResourceLocation(Solar.MODID, registrationName));
    }


}
