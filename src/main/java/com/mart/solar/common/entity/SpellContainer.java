package com.mart.solar.common.entity;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class SpellContainer extends Entity{

    public SpellContainer(World worldIn) {
        super(worldIn);
    }

    @Override
    protected void entityInit() {
        System.out.println("Fuck me");
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound compound) {

    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound compound) {

    }
}
