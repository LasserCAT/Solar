package com.mart.solar.api.registry;

import com.mart.solar.Solar;
import com.mart.solar.common.rituals.Ritual;

import java.util.ArrayList;
import java.util.List;

public class RitualRegister {

    private static List<Ritual> rituals = new ArrayList<>();

    public static void registerRitual(Ritual ritual) {
        if (!rituals.contains(ritual))
            rituals.add(ritual);
        else
            Solar.logger.info("Failed to add ritual:" + ritual.getRitualName());
    }

    public static List<Ritual> getRituals() {
        return rituals;
    }
}
