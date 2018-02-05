package com.mart.solar.common.rituals;

import com.mart.solar.api.enums.CircleTypes;
import com.mart.solar.api.ritual.OldRitual;
import net.minecraft.entity.player.EntityPlayer;

public class RitualClearSkies extends OldRitual {

    public RitualClearSkies() {
        super("Rite of the Clear Skies", 1000, 0);

        types.add(CircleTypes.SUN);
        windRunes = 4;

        runes.put(0, 4);
        runes.put(2, 4);
        runes.put(4, 4);
        runes.put(6, 4);
    }

    @Override
    public void activateRitual(EntityPlayer player, float solar, float lunar) {
        if (!player.getEntityWorld().isRemote) {
            player.getEntityWorld().getWorldInfo().setRaining(false);
            player.getEntityWorld().getWorldInfo().setThundering(false);
        }
    }
}
