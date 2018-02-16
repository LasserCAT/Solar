package com.mart.solar.common.entity;

import com.mart.solar.Solar;
import com.mart.solar.api.interfaces.IPlaceAbleSpell;
import com.mart.solar.api.spell.Spell;
import com.mart.solar.api.spell.SpellManager;
import com.mart.solar.common.items.ItemBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import java.util.Optional;
import java.util.logging.Level;

public class EntitySpellContainer extends Entity{

    private IPlaceAbleSpell iPlaceAbleSpell;

    private int lifeTime;

    public EntitySpellContainer(World worldIn) {
        super(worldIn);
    }

    public EntitySpellContainer(World worldIn, EntityPlayer player, IPlaceAbleSpell iPlaceAbleSpell) {
        super(worldIn);

        this.setWorld(worldIn);
        this.setPosition(player.getPosition().getX(), player.getPosition().getY(), player.getPosition().getZ());

        this.iPlaceAbleSpell = iPlaceAbleSpell;
    }

    @Override
    protected void entityInit() {
        if(!(iPlaceAbleSpell instanceof Spell)){
            System.out.println("[EntitySpellContainer.java] Removing iPlaceAbleSpell entity. iPlaceAbleSpell was not an instance of a Spell.class");
            getEntityWorld().removeEntity(this);
        }
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound compound) {
        Optional<Spell> spell = SpellManager.getSpells().stream()
                .filter(s -> s.getSpellRegistryName().equals(Spell.getSpellHandleFromNBT(compound))).findFirst();

        if(spell.isPresent()){
            Spell copySpell = spell.get().getNewInstance();
            copySpell.getDataFromNBT(compound);

            this.iPlaceAbleSpell = (IPlaceAbleSpell) copySpell;
        }


    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound compound) {
        Spell spell = (Spell) this.iPlaceAbleSpell;
        spell.saveSpellHandleToNBT(compound);
        spell.saveDataToNBT(compound);
    }

    @Override
    public void onEntityUpdate() {
        Spell spell = (Spell) this.iPlaceAbleSpell;

        if(spell.getLifeSpan() <= this.lifeTime){
            getEntityWorld().removeEntity(this);
        }

        this.iPlaceAbleSpell.tick(this.getEntityWorld(), this.getPosition(), this.lifeTime);

        this.lifeTime++;
    }
}
