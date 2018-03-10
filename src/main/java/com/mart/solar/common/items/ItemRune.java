package com.mart.solar.common.items;

import com.mart.solar.Solar;
import com.mart.solar.api.interfaces.IRune;
import com.mart.solar.common.entity.item.RuneEntity;
import com.mart.solar.common.items.base.ItemEnum;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class ItemRune<T extends Enum<T>> extends ItemEnum implements IRune {

    public ItemRune(Class<T> enumClass) {
        super(enumClass);
        setUnlocalizedName(Solar.MODID + "." + "rune");
    }

    @Override
    public boolean hasCustomEntity(ItemStack stack) {
        return true;
    }

    @Nullable
    @Override
    public Entity createEntity(World world, Entity location, ItemStack itemstack) {
        RuneEntity entity = new RuneEntity(world, location.posX, location.posY, location.posZ, itemstack);
        entity.setPickupDelay(40);
        entity.motionY = location.motionY;
        entity.motionX = location.motionX;
        entity.motionZ = location.motionZ;
        return entity;
    }


}
