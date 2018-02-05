package com.mart.solar.common.spells;

import net.minecraft.entity.player.EntityPlayer;

public abstract class Spell {

    private final String name;

    public Spell(String name) {
        this.name = name;
    }

    public abstract void activateSpell(EntityPlayer player);

    public String getName() {
        return name;
    }

}
