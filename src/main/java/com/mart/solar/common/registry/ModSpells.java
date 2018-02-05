package com.mart.solar.common.registry;

import com.mart.solar.api.spell.Spell;
import com.mart.solar.common.spells.SpellHighTide;
import com.mart.solar.common.spells.SpellSummerHeat;

import java.util.ArrayList;
import java.util.List;

public class ModSpells {

    public static List<Spell> SPELLS;

    public static void addSpell(Spell ritual){
        SPELLS.add(ritual);
    }

    private static void init(){
        SPELLS = new ArrayList<>();

        addSpell(new SpellSummerHeat());
        addSpell(new SpellHighTide());
    }

    public static Spell[] getSpells()
    {
        if(SPELLS == null) init();
        return SPELLS.toArray(new Spell[SPELLS.size()]);
    }
}
