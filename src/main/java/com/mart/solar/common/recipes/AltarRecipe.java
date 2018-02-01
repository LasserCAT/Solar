package com.mart.solar.common.recipes;

import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class AltarRecipe extends IForgeRegistryEntry.Impl<AltarRecipe>{

    private final Item input;
    private final Item output;

    private final int energyCost;


    public AltarRecipe(Item input, Item output, int energyCost, String recipeName) {
        this.input = input;
        this.output = output;
        this.energyCost = energyCost;

        this.setRegistryName(recipeName);
    }

    public Item getInput() {
        return input;
    }

    public Item getOutput() {
        return output;
    }

    public int getEnergyCost() {
        return energyCost;
    }
}