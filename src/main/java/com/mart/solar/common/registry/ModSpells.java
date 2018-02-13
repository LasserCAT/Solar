package com.mart.solar.common.registry;

import com.mart.solar.api.spell.Spell;
import com.mart.solar.common.spells.SpellHighTide;
import com.mart.solar.common.spells.SpellSolarProtection;
import com.mart.solar.common.spells.SpellSummerHeat;

import java.util.ArrayList;
import java.util.List;

public class ModSpells {

    public static List<Spell> SPELLS;

    public static SpellSummerHeat spellSummerHeat;
    public static SpellHighTide spellHighTide;
    public static SpellSolarProtection spellSolarProtection;


    private static void init(){
        SPELLS = new ArrayList<>();

        spellSummerHeat = addSpell(new SpellSummerHeat());
        spellHighTide = addSpell(new SpellHighTide());
        spellSolarProtection = addSpell(new SpellSolarProtection());
    }

    private static <T extends Spell> T addSpell(T spell){
        SPELLS.add(spell);
        return spell;
    }

    public static Spell[] getSpells()
    {
        if(SPELLS == null) init();
        return SPELLS.toArray(new Spell[SPELLS.size()]);
    }
}
