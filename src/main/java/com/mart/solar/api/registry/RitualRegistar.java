package com.mart.solar.api.registry;

import com.mart.solar.Solar;
import com.mart.solar.api.ritual.OldRitual;

import java.util.ArrayList;
import java.util.List;

public class RitualRegistar {

    private static List<OldRitual> rituals = new ArrayList<>();

    public static void registerRitual(OldRitual ritual) {
        if (!rituals.contains(ritual))
            rituals.add(ritual);
        else
            Solar.logger.info("Failed to add ritual:" + ritual.getRitualName());
    }

    public static List<OldRitual> getRituals() {
        return rituals;
    }
}
