package com.mart.solar.api.util;


import com.mart.solar.Solar;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.Locale;

public class ItemEnum<T extends Enum<T>> extends Item {

    private final T[] types;

    public ItemEnum(Class<T> enumClass) {
        setHasSubtypes(true);
        setCreativeTab(Solar.solarTab);
        this.types = enumClass.getEnumConstants();
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return super.getUnlocalizedName(stack) + "." + getItemType(stack).toString().toLowerCase(Locale.ENGLISH);
    }

    @SideOnly(Side.CLIENT)
    public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
        for (T type : types)
            subItems.add(new ItemStack(this, 1, type.ordinal()));
    }

    public T getItemType(ItemStack stack) {
        return types[MathHelper.clamp(stack.getItemDamage(), 0, types.length)];
    }

    public ItemStack getStack(T enumType) {
        return new ItemStack(this, 1, enumType.ordinal());
    }
}