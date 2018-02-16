package com.mart.solar.api.spell;

import com.mart.solar.Solar;
import com.mart.solar.common.items.ItemBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.registries.IForgeRegistryEntry;

public abstract class Spell extends IForgeRegistryEntry.Impl<Spell> implements INBTSerializable<NBTTagCompound>{

    private final String name;
    private static final String NBT_SPELL_HANDLE_KEY = "spell";

    /**
     * The lifespan of the Spell in ticks.
     */
    protected int lifeSpan = 0;

    public Spell(String name) {
        this.name = name;

        setRegistryName(Solar.MODID, getSpellRegistryName());
    }

    public abstract void activateSpell(EntityPlayer player, ItemStack stack);

    public abstract String getSpellRegistryName();

    public static String getSpellHandleFromNBT(NBTTagCompound tag){
        return tag.getString(NBT_SPELL_HANDLE_KEY);
    }

    public void setSpellToNullOnNBT(NBTTagCompound tag){
        tag.setString(NBT_SPELL_HANDLE_KEY, "");
    }


    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setString(NBT_SPELL_HANDLE_KEY, getSpellRegistryName());
        return nbt;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {

    }

    public abstract Spell getNewInstance();

    public String getName() {
        return name;
    }

    public int getLifeSpan() {
        return lifeSpan;
    }
}
