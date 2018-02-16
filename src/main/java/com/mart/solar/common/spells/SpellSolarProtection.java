package com.mart.solar.common.spells;

import com.mart.solar.api.interfaces.IPlaceAbleSpell;
import com.mart.solar.api.spell.Spell;
import com.mart.solar.common.entity.SpellContainer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class SpellSolarProtection extends Spell implements IPlaceAbleSpell {

    private int spellRadius = 8;

    public SpellSolarProtection() {
        super("Solar Protection");
        this.lifeSpan = 200;
        this.spellRadius = 8;
    }

    @Override
    public void saveDataToNBT(NBTTagCompound tag) {
        tag.setInteger("spellRadius", this.spellRadius);
    }

    @Override
    public void getDataFromNBT(NBTTagCompound tag) {
        this.spellRadius = tag.getInteger("spellRadius");
    }

    @Override
    public void activateSpell(EntityPlayer player, ItemStack itemStack) {
        if (player.getEntityWorld().isRemote) {
            return;
        }
        player.getEntityWorld().spawnEntity(new SpellContainer(player.getEntityWorld(), player, this));
    }

    @Override
    public String getSpellRegistryName() {
        return "spellsolarprotection";
    }


    @Override
    public void tick(World world, BlockPos pos, int tickValue) {
        if(tickValue % 20 != 0){
            return;
        }

        long worldTime = world.getWorldTime();
        long dayTime = worldTime % 24000;

        if(dayTime < 12566 || dayTime > 23450){
            return;
        }


        List<EntityMob> entitiesInRange = world.getEntitiesWithinAABB(EntityMob.class,
                new AxisAlignedBB(pos.getX() - this.spellRadius, 0, pos.getZ() - this.spellRadius,
                        pos.getX() + this.spellRadius, 255, pos.getZ() + this.spellRadius));

        for(EntityMob e : entitiesInRange){
            if(e.isImmuneToFire()){
                return;
            }

            if(e.isEntityUndead()){
                e.setFire(5);
                continue;
            }

            if(e instanceof EntitySpider){
                EntitySpider spider = (EntitySpider) e;

                if(spider.targetTasks.taskEntries.size() <= 1){
                    continue;
                }

                List<EntityAIBase> tasksToRemove = new ArrayList<>();

                for(EntityAITasks.EntityAITaskEntry a : spider.targetTasks.taskEntries){
                    EntityAIBase ai = a.action;

                    if(ai instanceof EntityAINearestAttackableTarget){
                        tasksToRemove.add(ai);
                    }
                }

                tasksToRemove.forEach(spider.targetTasks::removeTask);

            }
        }
    }

    @Override
    public Spell getNewInstance() {
        return new SpellSolarProtection();
    }
}
