package com.mart.solar.rituals;

import com.mart.solar.api.enums.CircleTypes;
import net.minecraft.entity.player.EntityPlayer;

public class RitualRain extends Ritual {

    public RitualRain() {
        super("Ritual of the Rising Storm(Rain)", 0, 1000);

        types.add(CircleTypes.MOON);
        windRunes = 4;

        runes.put(0, 4);
        runes.put(2, 4);
        runes.put(4, 4);
        runes.put(6, 4);
    }

    @Override
    public void activateRitual(EntityPlayer player, float solar, float lunar) {
        if (!player.getEntityWorld().isRemote) {
            player.getEntityWorld().getWorldInfo().setThundering(false);
            player.getEntityWorld().getWorldInfo().setRaining(true);
        }
    }
}
