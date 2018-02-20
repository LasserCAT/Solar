package com.mart.solar.common.recipes;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.List;

public class AltarRecipeManager {

    private static IForgeRegistry<AltarRecipe> REGISTRY;

    public static void setRegistry(IForgeRegistry<AltarRecipe> registry) {
        REGISTRY = registry;
    }

    public static List<AltarRecipe> getRecipes() {
        return REGISTRY.getValues();
    }

    public static  AltarRecipe findMatchingInput(Item item) {
        for (AltarRecipe altarRecipe : REGISTRY) {
            if (altarRecipe.getInput() == item) {
                return altarRecipe;
            }
        }

        return null;
    }

    public static AltarRecipe getRecipeByRegistryName(String registryName){
        return REGISTRY.getValue(new ResourceLocation(registryName));
    }
}
