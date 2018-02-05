package com.mart.solar.common.registry;

import com.mart.solar.api.registry.RitualRegistar;
import com.mart.solar.api.ritual.Ritual;
import com.mart.solar.common.recipes.AltarRecipe;
import com.mart.solar.common.rituals.*;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class ModRituals {

    public static List<Ritual> RITUALS;

    public static void addRitual(Ritual ritual){
        RITUALS.add(ritual);
    }

    private static void init(){
        RITUALS = new ArrayList<>();

        addRitual(new RitualRisingSun());
    }

    public static void oldInit(){
        RitualRegistar.registerRitual(new RitualRisingMoon());
        RitualRegistar.registerRitual(new RitualSummerHeat());
        RitualRegistar.registerRitual(new RitualHighTide());
        RitualRegistar.registerRitual(new RitualClearSkies());
        RitualRegistar.registerRitual(new RitualRain());
        RitualRegistar.registerRitual(new RitualStorm());
    }

    public static Ritual[] getRituals()
    {
        if(RITUALS == null) init();
        return RITUALS.toArray(new Ritual[RITUALS.size()]);
    }

}
