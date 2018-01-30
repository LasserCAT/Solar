package com.mart.solar.registry;

import com.mart.solar.api.registry.SpellRegister;
import com.mart.solar.spells.SpellHighTide;
import com.mart.solar.spells.SpellSummerHeat;

public class ModSpells {

    public static void init() {
        SpellRegister.registerSpell(new SpellSummerHeat());
        SpellRegister.registerSpell(new SpellHighTide());
    }
}
