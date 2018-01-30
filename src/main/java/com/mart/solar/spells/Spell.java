package com.mart.solar.spells;

import net.minecraft.entity.player.EntityPlayer;

public abstract class Spell {

    String name = "Spell not set";

    public Spell(String name) {
        setName(name);
    }

    public abstract void activateSpell(EntityPlayer player);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
