package com.mart.solar.common.registry;

import com.mart.solar.api.registry.RitualRegister;
import com.mart.solar.common.rituals.*;

public class ModRituals {

    public static void init() {
        RitualRegister.registerRitual(new RitualRisingSun());
        RitualRegister.registerRitual(new RitualRisingMoon());
        RitualRegister.registerRitual(new RitualSummerHeat());
        RitualRegister.registerRitual(new RitualHighTide());
        RitualRegister.registerRitual(new RitualClearSkies());
        RitualRegister.registerRitual(new RitualRain());
        RitualRegister.registerRitual(new RitualStorm());
    }
}
