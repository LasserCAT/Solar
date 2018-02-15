package com.mart.solar.common.entity;

import com.mart.solar.Solar;
import com.mart.solar.api.interfaces.IPlaceAbleSpell;
import com.mart.solar.api.spell.Spell;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import java.util.logging.Level;

public class SpellContainer extends Entity{

    private final IPlaceAbleSpell iPlaceAbleSpell;

    private int lifeTime;

    public SpellContainer(World worldIn, EntityPlayer player, IPlaceAbleSpell iPlaceAbleSpell) {
        super(worldIn);

        this.setWorld(worldIn);
        this.setPosition(player.getPosition().getX(), player.getPosition().getY(), player.getPosition().getZ());

        this.iPlaceAbleSpell = iPlaceAbleSpell;
    }

    @Override
    protected void entityInit() {
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound compound) {

    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound compound) {

    }

    @Override
    public void onEntityUpdate() {
        if(!(this.iPlaceAbleSpell instanceof Spell)){
            Solar.logger.log(Level.WARNING, "[SpellContainer.java] Removing iPlaceAbleSpell entity. iPlaceAbleSpell was not an instance of a Spell.class");
            getEntityWorld().removeEntity(this);
            return;
        }

        Spell spell = (Spell) this.iPlaceAbleSpell;

        if(spell.getLifeSpan() <= this.lifeTime){
            getEntityWorld().removeEntity(this);
        }

        this.iPlaceAbleSpell.tick(this.getEntityWorld(), this.getPosition(), this.lifeTime);

        this.lifeTime++;
    }
}
