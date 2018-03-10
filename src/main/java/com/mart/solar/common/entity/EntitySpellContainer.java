package com.mart.solar.common.entity;

import com.mart.solar.api.interfaces.IPlaceableSpell;
import com.mart.solar.api.spell.Spell;
import com.mart.solar.api.spell.SpellManager;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntitySpellContainer extends Entity {

    private Spell spell;

    private int lifeTime = 0;

    public EntitySpellContainer(World worldIn) {
        super(worldIn);
    }

    public EntitySpellContainer(World worldIn, BlockPos blockPos, Spell iPlaceAbleSpell) {
        super(worldIn);

        this.setWorld(worldIn);

        this.setPosition(blockPos.getX(), blockPos.getY() + 1, blockPos.getZ());

        this.spell = iPlaceAbleSpell;
        this.setSize(8, 8);

        writeEntityToNBT(getEntityData());
    }

    @Override
    protected void entityInit() {
    }

    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        return new AxisAlignedBB(this.getPosition().getX() - 8, this.getPosition().getY(), this.getPosition().getZ() - 8,
                this.getPosition().getX() + 8, this.getPosition().getY() + 1, this.getPosition().getZ() + 8);
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
        if (this.getEntityWorld().isRemote) {
            return;
        }

        if (!(spell instanceof IPlaceableSpell)) {
            System.out.println("[EntitySpellContainer.java] Removing spell item. spell was not an instance of a Spell.class");
            getEntityWorld().removeEntity(this);
        }

        if (spell.getLifeSpan() <= this.lifeTime) {
            getEntityWorld().removeEntity(this);
        }

        IPlaceableSpell placeAbleSpell = (IPlaceableSpell) this.spell;
        placeAbleSpell.tick(this.getEntityWorld(), this.getPosition(), this.lifeTime);

        this.lifeTime++;
    }

}
