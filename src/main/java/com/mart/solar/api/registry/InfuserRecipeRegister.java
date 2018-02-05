package com.mart.solar.api.registry;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.mart.solar.Solar;
import com.mart.solar.api.enums.RuneType;
import net.minecraft.item.Item;

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
        private final RuneType output;


        public InfuserRecipe(Item input, RuneType output) {
            this.input = input;
            this.output = output;
        }

        public Item getInput() {
            return input;
        }

        public RuneType getOutput() {
            return output;
        }
    }
}
