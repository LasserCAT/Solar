package com.mart.solar.api.registry;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.mart.solar.Solar;
import com.mart.solar.api.enums.RuneTypes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class InfuserRecipeRegister {

    private static BiMap<Item, InfuserRecipe> recipes = HashBiMap.create();

    public static void registerRecipe(InfuserRecipe infuserRecipe) {
        if (!recipes.containsValue(infuserRecipe) && infuserRecipe.getInput() != null)
            recipes.put(infuserRecipe.getInput(), infuserRecipe);
        else
            Solar.logger.info("Failed to add recipe with input:" + infuserRecipe.getInput().getUnlocalizedName());
    }

    public static BiMap<Item, InfuserRecipe> getRecipes() {
        return recipes;
    }

    //Recipe Class
    public static class InfuserRecipe {

        private final Item input;
        private final RuneTypes output;


        public InfuserRecipe(Item input, RuneTypes output) {
            this.input = input;
            this.output = output;
        }

        public Item getInput() {
            return input;
        }

        public RuneTypes getOutput() {
            return output;
        }
    }
}
