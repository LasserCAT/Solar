package com.mart.solar.common.handler;

import com.mart.solar.Solar;
import com.mart.solar.api.ritual.Ritual;
import com.mart.solar.api.ritual.RitualManager;
import com.mart.solar.common.recipes.AltarRecipe;
import com.mart.solar.common.recipes.AltarRecipeManager;
import com.mart.solar.common.registry.ModAltarRecipes;
import com.mart.solar.common.registry.ModRituals;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;

@Mod.EventBusSubscriber
public class RegistrationHandler {

    @SubscribeEvent
    public static void addRegistry(RegistryEvent.NewRegistry event) {
        AltarRecipeManager.setRegistry(
                new RegistryBuilder<AltarRecipe>()
                        .setName(new ResourceLocation(Solar.MODID, "altar_recipes"))
                        .setType(AltarRecipe.class)
                        .disableSaving()
                        .allowModification()
                        .create());

        RitualManager.setRegistry(
                new RegistryBuilder<Ritual>()
                .setName(new ResourceLocation(Solar.MODID, "rituals"))
                .setType(Ritual.class)
                .disableSaving()
                .allowModification()
                .create()
        );
    }

    @SubscribeEvent
    public static void registerAltarRecipes(RegistryEvent.Register<AltarRecipe> event) {
        IForgeRegistry<AltarRecipe> registry = event.getRegistry();
        registry.registerAll(ModAltarRecipes.getAltarRecipes());
    }

    @SubscribeEvent
    public static void registerRituals(RegistryEvent.Register<Ritual> event){
        IForgeRegistry<Ritual> registry = event.getRegistry();
        registry.registerAll(ModRituals.getRituals());
    }
}
