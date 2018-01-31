package com.mart.solar.common.registry;

import com.mart.solar.api.enums.RuneTypes;
import com.mart.solar.api.registry.InfuserRecipeRegister;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class ModRecipes {

    public static void init() {
        //Infuser Recipes
        InfuserRecipeRegister.registerRecipe(new InfuserRecipeRegister.InfuserRecipe(Items.BLAZE_ROD, RuneTypes.FIRE));
        InfuserRecipeRegister.registerRecipe(new InfuserRecipeRegister.InfuserRecipe(Items.POTIONITEM, RuneTypes.WATER));
        InfuserRecipeRegister.registerRecipe(new InfuserRecipeRegister.InfuserRecipe(ItemBlock.getByNameOrId("stone"), RuneTypes.EARTH));
        InfuserRecipeRegister.registerRecipe(new InfuserRecipeRegister.InfuserRecipe(Items.FEATHER, RuneTypes.WIND));
        InfuserRecipeRegister.registerRecipe(new InfuserRecipeRegister.InfuserRecipe(Items.DIAMOND, RuneTypes.TIME));
        InfuserRecipeRegister.registerRecipe(new InfuserRecipeRegister.InfuserRecipe(Items.BONE, RuneTypes.LIFE));
        InfuserRecipeRegister.registerRecipe(new InfuserRecipeRegister.InfuserRecipe(Items.GOLD_INGOT, RuneTypes.SUN));

        for (ItemStack i : OreDictionary.getOres("ingotSilver")) {
            InfuserRecipeRegister.registerRecipe(new InfuserRecipeRegister.InfuserRecipe(i.getItem(), RuneTypes.MOON));
        }

    }
}
