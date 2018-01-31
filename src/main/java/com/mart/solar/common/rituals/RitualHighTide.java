package com.mart.solar.common.rituals;

import com.mart.solar.api.enums.CircleTypes;
import com.mart.solar.common.spells.SpellHighTide;
import net.minecraft.entity.player.EntityPlayer;

public class RitualHighTide extends Ritual {

    public RitualHighTide() {
        super("Rite of the High Tide", 0, 10000);

        types.add(CircleTypes.MOON);
        waterRunes = 4;

        hasSpell = true;

        spell = new SpellHighTide();

        runes.put(0, 2);
        runes.put(2, 2);
        runes.put(4, 2);
        runes.put(6, 2);
    }

    @Override
    public void activateRitual(EntityPlayer player, float solar, float lunar) {

    }
}
