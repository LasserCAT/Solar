package com.mart.solar.api.registry;

import com.mart.solar.Solar;
import com.mart.solar.api.spell.Spell;

import java.util.ArrayList;
import java.util.List;

public class SpellRegister {

    private static List<Spell> spells = new ArrayList<>();

    public static void registerSpell(Spell spell) {
        if (!spells.contains(spell))
            spells.add(spell);
        else
            Solar.logger.info("Failed to add spell:" + spell.getName());
    }

    public static List<Spell> getSpells() {
        return spells;
    }

}
