package com.mart.solar.common.registry;

import com.mart.solar.api.ritual.Ritual;
import com.mart.solar.common.rituals.*;

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
        addRitual(new RitualRisingMoon());
        addRitual(new RitualRain());
        addRitual(new RitualClearSkies());
        addRitual(new RitualSummerHeat());
        addRitual(new RitualHighTide());
        addRitual(new RitualSolarProtection());
    }

    public static Ritual[] getRituals()
    {
        if(RITUALS == null) init();
        return RITUALS.toArray(new Ritual[RITUALS.size()]);
    }

}
