package com.mart.solar.common.items;

import com.mart.solar.Solar;
import com.mart.solar.api.interfaces.ITotemManipulator;
import com.mart.solar.api.registry.SpellRegister;
import com.mart.solar.common.spells.Spell;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemRitualStaff extends ItemBase implements ITotemManipulator {

    public ItemRitualStaff(String name) {
        super(name);
        setCreativeTab(Solar.solarTab);
    }


    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        if (!world.isRemote) {
            ItemStack stack = player.getHeldItem(hand);
            String spell = getCurrentSpell(stack);

            for (Spell s : SpellRegister.getSpells()) {
                if (spell.equals(s.getName())) {
                    s.activateSpell(player);
                }
            }
        }
        return super.onItemRightClick(world, player, hand);
    }

    public void setCurrentSpell(ItemStack stack, String key) {
        if (!stack.hasTagCompound()) {
            stack.setTagCompound(new NBTTagCompound());
        }

        NBTTagCompound tag = stack.getTagCompound();

        tag.setString("spell", key);
    }

    public String getCurrentSpell(ItemStack stack) {
        if (!stack.hasTagCompound()) {
            stack.setTagCompound(new NBTTagCompound());
        }

        NBTTagCompound tag = stack.getTagCompound();
        return tag.getString("spell");
    }

}
