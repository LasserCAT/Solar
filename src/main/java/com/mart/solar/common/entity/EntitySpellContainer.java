package com.mart.solar.common.entity;

import com.mart.solar.api.interfaces.IPlaceAbleSpell;
import com.mart.solar.api.spell.Spell;
import com.mart.solar.api.spell.SpellManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import java.util.Optional;

public class EntitySpellContainer extends Entity{

    private Spell spell;

    private int lifeTime;

    public EntitySpellContainer(World worldIn) {
        super(worldIn);
    }

    public EntitySpellContainer(World worldIn, EntityPlayer player, Spell iPlaceAbleSpell) {
        super(worldIn);

        this.setWorld(worldIn);
        this.setPosition(player.getPosition().getX(), player.getPosition().getY(), player.getPosition().getZ());

        this.spell = iPlaceAbleSpell;
        System.out.println("Spell is set");
        if(spell instanceof IPlaceAbleSpell){
            System.out.println("Sick is an instance");
        }
        else{
            System.out.println("Somehow not an instance");
        }
    }

    @Override
    protected void entityInit() {
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound compound) {
        this.lifeTime = compound.getInteger("lifeTime");

        Optional<Spell> spell = SpellManager.getSpells().stream()
                .filter(s -> s.getSpellRegistryName().equals(Spell.getSpellHandleFromNBT(compound))).findFirst();

        if(spell.isPresent()){
            Spell copySpell = spell.get().getNewInstance();
            copySpell.getDataFromNBT(compound);

            this.spell = copySpell;
        }


    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound compound) {
        compound.setInteger("lifeTime", this.lifeTime);

        spell.saveSpellHandleToNBT(compound);
        spell.saveDataToNBT(compound);
    }

    @Override
    public void onEntityUpdate() {
        if(!(spell instanceof IPlaceAbleSpell)){
            System.out.println("[EntitySpellContainer.java] Removing spell entity. spell was not an instance of a Spell.class");
            getEntityWorld().removeEntity(this);
        }

        if(spell.getLifeSpan() <= this.lifeTime){
            getEntityWorld().removeEntity(this);
        }
        IPlaceAbleSpell placeAbleSpell = (IPlaceAbleSpell) this.spell;
        placeAbleSpell.tick(this.getEntityWorld(), this.getPosition(), this.lifeTime);

        this.lifeTime++;
    }
}
