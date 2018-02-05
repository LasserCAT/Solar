package com.mart.solar.common.registry;

import com.mart.solar.api.enums.RuneType;
import com.mart.solar.api.registry.InfuserRecipeRegister;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class ModRecipes {

    public static void init() {
        //Infuser Recipes
        InfuserRecipeRegister.registerRecipe(new InfuserRecipeRegister.InfuserRecipe(Items.BLAZE_ROD, RuneType.FIRE));
        InfuserRecipeRegister.registerRecipe(new InfuserRecipeRegister.InfuserRecipe(Items.POTIONITEM, RuneType.WATER));
        InfuserRecipeRegister.registerRecipe(new InfuserRecipeRegister.InfuserRecipe(ItemBlock.getByNameOrId("stone"), RuneType.EARTH));
        InfuserRecipeRegister.registerRecipe(new InfuserRecipeRegister.InfuserRecipe(Items.FEATHER, RuneType.WIND));
        InfuserRecipeRegister.registerRecipe(new InfuserRecipeRegister.InfuserRecipe(Items.DIAMOND, RuneType.TIME));
        InfuserRecipeRegister.registerRecipe(new InfuserRecipeRegister.InfuserRecipe(Items.BONE, RuneType.LIFE));
        InfuserRecipeRegister.registerRecipe(new InfuserRecipeRegister.InfuserRecipe(Items.GOLD_INGOT, RuneType.SUN));

        for (ItemStack i : OreDictionary.getOres("ingotSilver")) {
            InfuserRecipeRegister.registerRecipe(new InfuserRecipeRegister.InfuserRecipe(i.getItem(), RuneType.MOON));
        }

        //Altar Recipes

    }
}
