package com.mart.solar.common.registry;

import com.mart.solar.common.items.enums.RuneType;
import com.mart.solar.api.infusing.InfuserReagent;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;

import java.util.ArrayList;
import java.util.List;

public class ModRecipes {

    public static List<InfuserReagent> REAGENTS;

    public static void addRitual(InfuserReagent ritual){
        REAGENTS.add(ritual);
    }

    private static void init(){
        REAGENTS = new ArrayList<>();

        addRitual(new InfuserReagent(Items.BLAZE_POWDER, RuneType.FIRE, 2, "blaze_fire"));
        addRitual(new InfuserReagent(Items.COAL, RuneType.FIRE, 1, "coal_fire"));
        addRitual(new InfuserReagent(Items.GUNPOWDER, RuneType.FIRE, 3, "gunpowder_fire"));

        addRitual(new InfuserReagent(Items.POTIONITEM, RuneType.WATER, 1, "waterbottle_water"));

        addRitual(new InfuserReagent(ItemBlock.getByNameOrId("stone"), RuneType.EARTH, 1, "stone_earth"));

        addRitual(new InfuserReagent(Items.FEATHER, RuneType.WIND, 1, "feather_wind"));

        addRitual(new InfuserReagent(Items.DIAMOND, RuneType.TIME, 2, "diamond_time"));

        addRitual(new InfuserReagent(Items.BONE, RuneType.LIFE, 1, "bone_life"));

        addRitual(new InfuserReagent(Items.GOLD_NUGGET, RuneType.SUN, 1, "goldnugget_sun"));

        addRitual(new InfuserReagent(ModItems.SILVER_NUGGET, RuneType.MOON, 1, "silvernugget_sun"));


        /*
        for (ItemStack i : OreDictionary.getOres("ingotSilver")) {
            InfuserRecipeRegister.registerRecipe(new InfuserRecipeRegister.InfuserRecipe(i.getItem(), RuneType.MOON));
        }
         */
    }

    public static InfuserReagent[] getReagents()
    {
        if(REAGENTS == null) init();
        return REAGENTS.toArray(new InfuserReagent[REAGENTS.size()]);
    }






}
