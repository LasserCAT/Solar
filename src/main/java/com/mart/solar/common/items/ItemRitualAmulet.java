package com.mart.solar.common.items;

import com.mart.solar.Solar;
import com.mart.solar.api.interfaces.IAltarManipulator;
import com.mart.solar.api.spell.Spell;
import com.mart.solar.api.spell.SpellManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import java.util.Optional;

public class ItemRitualAmulet extends ItemBase implements IAltarManipulator {

    public ItemRitualAmulet() {
        super("ritualamulet");
        setCreativeTab(Solar.solarTab);
        setMaxStackSize(1);
    }


    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        if (!world.isRemote) {
            ItemStack stack = player.getHeldItem(hand);

            Optional<Spell> spell = SpellManager.getSpells().stream().filter(s -> s.getSpellRegistryName().equals(getCurrentSpell(stack))).findFirst();
            spell.ifPresent(spell1 -> spell1.activateSpell(player, player.getHeldItem(hand)));
        }
        return super.onItemRightClick(world, player, hand);
    }

    public static void setCurrentSpell(ItemStack stack, String key) {
        System.out.println("Settng the spell");
        NBTTagCompound tag = getCompound(stack);
        tag.setString("spell", key);
    }

    public static void setEnergy(ItemStack stack, int energy) {
        System.out.println("Setting the energy");
        NBTTagCompound tag = getCompound(stack);
        tag.setInteger("energy", energy);

        if(energy == 0){
            setCurrentSpell(stack, "");
        }
    }

    private String getCurrentSpell(ItemStack stack) {
        NBTTagCompound tag = getCompound(stack);
        return tag.getString("spell");
    }

    public static int getEnergy(ItemStack stack) {
        NBTTagCompound tag = getCompound(stack);
        return tag.getInteger("energy");
    }

    private static NBTTagCompound getCompound(ItemStack stack){
        System.out.println("Getting tag");
        if (!stack.hasTagCompound()) {
            stack.setTagCompound(new NBTTagCompound());
        }

        return stack.getTagCompound();
    }

}
