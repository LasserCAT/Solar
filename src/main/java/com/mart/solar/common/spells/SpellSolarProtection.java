package com.mart.solar.common.spells;

import com.mart.solar.api.interfaces.IPlaceableSpell;
import com.mart.solar.api.spell.Spell;
import com.mart.solar.common.tileentities.TileRitualStone;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class SpellSolarProtection extends Spell implements IPlaceableSpell {

    public SpellSolarProtection() {
        super("Solar Protection");
    }

    @Override
    public void tick(TileRitualStone stone) {

    }

    @Override
    public void activateSpell(EntityPlayer player, ItemStack itemStack) {

    }

    @Override
    public String getSpellRegistryName() {
        return "spellsolarprotection";
    }
}
