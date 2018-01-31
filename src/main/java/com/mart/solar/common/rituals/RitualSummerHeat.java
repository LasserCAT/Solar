package com.mart.solar.common.rituals;

import com.mart.solar.api.enums.CircleTypes;
import com.mart.solar.common.spells.SpellSummerHeat;
import net.minecraft.entity.player.EntityPlayer;

public class RitualSummerHeat extends Ritual {

    public RitualSummerHeat() {
        super("Ritual of Summer Heat", 10000, 0);

        types.add(CircleTypes.SUN);
        fireRunes = 4;

        hasSpell = true;

        spell = new SpellSummerHeat();

        runes.put(0, 1);
        runes.put(2, 1);
        runes.put(4, 1);
        runes.put(6, 1);
    }

    @Override
    public void activateRitual(EntityPlayer player, float solar, float lunar) {
    }
}
