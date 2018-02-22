package com.mart.solar.common.entity;

import com.mart.solar.api.interfaces.IPlaceableSpell;
import com.mart.solar.api.spell.Spell;
import com.mart.solar.api.spell.SpellManager;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntitySpellContainer extends Entity{

    private Spell spell;

    private int lifeTime = 0;

    public EntitySpellContainer(World worldIn) {
        super(worldIn);
    }

    public EntitySpellContainer(World worldIn, BlockPos blockPos, Spell iPlaceAbleSpell) {
        super(worldIn);

        this.setWorld(worldIn);

        this.setPosition(blockPos.getX() + 0.5, blockPos.getY() + 1, blockPos.getZ() + 0.5);

        this.spell = iPlaceAbleSpell;

        writeEntityToNBT(getEntityData());
    }

    @Override
    protected void entityInit() {
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound compound) {
        this.lifeTime = compound.getInteger("lifeTime");

        this.spell = SpellManager.getSpellByName(Spell.getSpellHandleFromNBT(compound)).getNewInstance();
        this.spell.getDataFromNBT(compound);
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound compound) {
        compound.setInteger("lifeTime", this.lifeTime);

        spell.saveSpellHandleToNBT(compound);
        spell.saveDataToNBT(compound);
    }

    @Override
    public void onEntityUpdate() {
        if(this.getEntityWorld().isRemote){
            return;
        }

        if(!(spell instanceof IPlaceableSpell)){
            System.out.println("[EntitySpellContainer.java] Removing spell entity. spell was not an instance of a Spell.class");
            getEntityWorld().removeEntity(this);
        }

        if(spell.getLifeSpan() <= this.lifeTime){
            getEntityWorld().removeEntity(this);
        }

        IPlaceableSpell placeAbleSpell = (IPlaceableSpell) this.spell;
        placeAbleSpell.tick(this.getEntityWorld(), this.getPosition(), this.lifeTime);

        this.lifeTime++;
    }

    public boolean isDay() {
        long worldTime = world.getWorldTime();
        long dayTime = worldTime % 24000;

        return dayTime < 12566 || dayTime > 23450;

    }

}
