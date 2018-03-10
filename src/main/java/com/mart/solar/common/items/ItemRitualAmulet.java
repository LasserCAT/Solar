package com.mart.solar.common.items;

import com.mart.solar.Solar;
import com.mart.solar.api.interfaces.IAltarManipulator;
import com.mart.solar.api.spell.Spell;
import com.mart.solar.api.spell.SpellManager;
import com.mart.solar.common.items.base.ItemBase;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ItemRitualAmulet extends ItemBase implements IAltarManipulator {

    public ItemRitualAmulet(String registryName) {
        super(registryName);
        setCreativeTab(Solar.solarTab);
        setMaxStackSize(1);
    }


    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        if (!world.isRemote) {
            ItemStack stack = player.getHeldItem(hand);

            Spell spell = SpellManager.getSpellByName(Spell.getSpellHandleFromNBT(getCompound(stack)));

            if(spell == null){
                return super.onItemRightClick(world, player, hand);
            }

            Spell copySpell = spell.getNewInstance();
            copySpell.getDataFromNBT(getCompound(stack));
            copySpell.activateSpell(player, stack);

        }
        return super.onItemRightClick(world, player, hand);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        Spell spell = SpellManager.getSpellByName(Spell.getSpellHandleFromNBT(getCompound(stack)));

        if(spell == null){
            return;
        }

        String spellName = spell.getName();
        tooltip.add("Spell: " + spellName);

        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    public static void setEnergy(ItemStack stack, int energy) {
        NBTTagCompound tag = getCompound(stack);
        tag.setInteger("energy", energy);
    }

    public static int getEnergy(ItemStack stack) {
        NBTTagCompound tag = getCompound(stack);
        return tag.getInteger("energy");
    }

}
