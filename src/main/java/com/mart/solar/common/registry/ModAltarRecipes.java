package com.mart.solar.common.registry;

import com.mart.solar.common.recipes.AltarRecipe;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class ModAltarRecipes {

    public static List<AltarRecipe> ALTAR_RECIPES;

    public static void addAltarRecipe(AltarRecipe recipe){
        ALTAR_RECIPES.add(recipe);
    }

    private static void init(){
        ALTAR_RECIPES  = new ArrayList<>();

        addAltarRecipe(new AltarRecipe(ModItems.dullAmulet, ModItems.ritualAmulet, 1000, "ritualAmuletRecipe"));
        addAltarRecipe(new AltarRecipe(Item.getItemFromBlock(Blocks.PLANKS), ModItems.RUNES, 100, "runeRecipe"));
    }

    public static AltarRecipe[] getAltarRecipes()
    {
        if(ALTAR_RECIPES == null) init();
        return ALTAR_RECIPES.toArray(new AltarRecipe[ALTAR_RECIPES.size()]);
    }

}
