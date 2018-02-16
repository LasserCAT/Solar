package com.mart.solar.api.spell;

import com.mart.solar.Solar;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.registries.IForgeRegistryEntry;

public abstract class SpellType extends IForgeRegistryEntry.Impl<SpellType> {

    private static final String NBT_SPELL_HANDLE_KEY = "spell";

    /**
     * The lifespan of the Spell in ticks.
     */
    protected int lifeSpan = 0;

    public SpellType(String registryName) {

        setRegistryName(Solar.MODID, registryName);
    }

    //public abstract void activateSpell(EntityPlayer player, ItemStack stack);

    //public abstract String getSpellRegistryName();

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

    public int getLifeSpan() {
        return lifeSpan;
    }
}
