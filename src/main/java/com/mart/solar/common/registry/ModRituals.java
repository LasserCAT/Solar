package com.mart.solar.common.registry;

import com.mart.solar.api.ritual.Ritual;
import com.mart.solar.common.rituals.*;

import java.util.ArrayList;
import java.util.List;

public class ModRituals {

    public static List<Ritual> RITUALS;

    private static void init(){
        RITUALS = new ArrayList<>();

        RITUALS.add(new RitualRisingSun());
        RITUALS.add(new RitualRisingMoon());
        RITUALS.add(new RitualRain());
        RITUALS.add(new RitualClearSkies());
        RITUALS.add(new RitualSummerHeat());
        RITUALS.add(new RitualHighTide());
        RITUALS.add(new RitualSolarProtection());
        RITUALS.add(new RitualLunarEmbrace());
        RITUALS.add(new RitualHunt("Rite of the Hunt"));
    }

    public static Ritual[] getRituals()
    {
        if(RITUALS == null) init();
        return RITUALS.toArray(new Ritual[RITUALS.size()]);
    }

}
