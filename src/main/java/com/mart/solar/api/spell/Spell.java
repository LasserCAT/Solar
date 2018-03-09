package com.mart.solar.api.spell;

import com.mart.solar.Solar;
import com.mart.solar.common.entity.EntitySpellContainer;
import com.mart.solar.common.items.ItemBase;
import com.mart.solar.common.items.ItemRitualAmulet;
import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.registries.IForgeRegistryEntry;

import java.awt.*;

public abstract class Spell extends IForgeRegistryEntry.Impl<Spell> {

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

    protected void placeSpell(EntityPlayer player, ItemStack stack){
        if (player.getEntityWorld().isRemote) {
            return;
        }

        Vec3d vec3d = player.getPositionEyes(0.1F);
        Vec3d vec3d1 = player.getLook(0.1F);
        Vec3d vec3d2 = vec3d.addVector(vec3d1.x * 5, vec3d1.y * 5, vec3d1.z * 5);
        RayTraceResult result = player.getEntityWorld().rayTraceBlocks(vec3d, vec3d2, true, false, true);

        assert result != null;

        BlockPos resultPos = result.getBlockPos();

        if (player.getEntityWorld().getBlockState(resultPos).getBlock() == Blocks.AIR) {
            return;
        }

        if(player.getEntityWorld().getBlockState(resultPos).getBlock() instanceof IGrowable){
            System.out.println("This is true");
            resultPos = resultPos.add(0, -1, 0);
        }
        else{
            System.out.println("Nah Boi");
        }

        player.getEntityWorld().spawnEntity(new EntitySpellContainer(player.getEntityWorld(), resultPos, this));

        setSpellToNullOnNBT(ItemBase.getCompound(stack));
        ItemRitualAmulet.setEnergy(stack, 0);
    }

    public abstract void activateSpell(EntityPlayer player, ItemStack stack);

    public abstract String getSpellRegistryName();

    public abstract void saveDataToNBT(NBTTagCompound tag);

    public abstract void getDataFromNBT(NBTTagCompound tag);

    public void saveSpellHandleToNBT(NBTTagCompound tag){
        tag.setString(NBT_SPELL_HANDLE_KEY, getSpellRegistryName());
    }

    public static String getSpellHandleFromNBT(NBTTagCompound tag){
        return tag.getString(NBT_SPELL_HANDLE_KEY);
    }

    public void setSpellToNullOnNBT(NBTTagCompound tag){
        tag.setString(NBT_SPELL_HANDLE_KEY, "");
    }

    public abstract Spell getNewInstance();

    public String getName() {
        return name;
    }

    public int getLifeSpan() {
        return lifeSpan;
    }
}
