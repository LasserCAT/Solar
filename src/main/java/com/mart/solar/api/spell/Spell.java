package com.mart.solar.api.spell;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.registries.IForgeRegistryEntry;

public abstract class Spell extends IForgeRegistryEntry.Impl<Spell> {

    private final String name;

    public Spell(String name) {
        this.name = name;

        setRegistryName(getSpellRegistryName());
    }

    public abstract void activateSpell(EntityPlayer player);

    public abstract String getSpellRegistryName();

    public String getName() {
        return name;
    }

}
