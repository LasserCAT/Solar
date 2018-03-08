package com.mart.solar.common.registry;

import com.mart.solar.api.spell.Spell;
import com.mart.solar.common.spells.*;

import java.util.ArrayList;
import java.util.List;

public class ModSpells {

    public static List<Spell> SPELLS;


    private static void init(){
        SPELLS = new ArrayList<>();

        SPELLS.add(new SpellSummerHeat());
        SPELLS.add(new SpellHighTide());
        SPELLS.add(new SpellSolarProtection());
        SPELLS.add(new SpellLunarEmbrace());
        SPELLS.add(new SpellHunt());
    }

    public static Spell[] getSpells()
    {
        if(SPELLS == null) init();
        return SPELLS.toArray(new Spell[SPELLS.size()]);
    }
}
