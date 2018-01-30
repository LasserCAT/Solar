package com.mart.solar.rituals;

import com.mart.solar.api.enums.CircleTypes;
import net.minecraft.entity.player.EntityPlayer;

public class RitualStorm extends Ritual {

    public RitualStorm() {
        super("Ritual of the Rising Storm", 0, 1000);

        types.add(CircleTypes.MOON);
        windRunes = 2;
        waterRunes = 2;

        runes.put(0, 2);
        runes.put(2, 4);
        runes.put(4, 2);
        runes.put(6, 4);
    }

    @Override
    public void activateRitual(EntityPlayer player, float solar, float lunar) {
        if (!player.getEntityWorld().isRemote) {
            player.getEntityWorld().getWorldInfo().setThundering(true);
            player.getEntityWorld().getWorldInfo().setRaining(true);
        }
    }
}