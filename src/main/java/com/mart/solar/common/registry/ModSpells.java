package com.mart.solar.common.registry;

import com.mart.solar.api.registry.SpellRegister;
import com.mart.solar.common.spells.SpellHighTide;
import com.mart.solar.common.spells.SpellSummerHeat;

public class ModSpells {

    public static void init() {
        SpellRegister.registerSpell(new SpellSummerHeat());
        SpellRegister.registerSpell(new SpellHighTide());
    }
}
