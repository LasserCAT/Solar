package com.mart.solar.common.spells;

import com.mart.solar.api.interfaces.IPlaceableSpell;
import com.mart.solar.api.spell.Spell;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SpellLunarEmbrace extends Spell implements IPlaceableSpell {

    private int spellRadius;

    public static final String MOB_TAG = "lunar_embrace";

    private final static int MIN_SPAWN_DELAY = 200;
    private final static int MAX_SPAWN_DELAY = 300;
    private final static int MAX_MOBS = 7;

    private int currentDelay = 0;
    private int ticksSinceLastSpawn = 0;

    public SpellLunarEmbrace() {
        super("Lunar Embrace");
        this.lifeSpan = 12000;
        this.spellRadius = 8;

        this.randomizeDelay();
    }

    @Override
    public void activateSpell(EntityPlayer player, ItemStack stack) {
        this.placeSpell(player, stack);
    }

    @Override
    public String getSpellRegistryName() {
        return "spell_lunar_embrace";
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
    public Spell getNewInstance() {
        return new SpellLunarEmbrace();
    }

    @Override
    public void tick(World world, BlockPos pos, int tickValue) {
        if (world.isRemote) {
            return;
        }

        if (this.ticksSinceLastSpawn >= this.currentDelay) {
            this.ticksSinceLastSpawn = 0;
            this.randomizeDelay();

            List<EntityMob> entitiesInRange = world.getEntitiesWithinAABB(EntityMob.class,
                    new AxisAlignedBB(pos.getX() - 15, pos.getY() - 5, pos.getZ() - 15,
                            pos.getX() + 15, pos.getY() + 5, pos.getZ() + 15));

            int mobCount = entitiesInRange.size();

            if (mobCount >= MAX_MOBS) {
                return;
            }

            int amountSpawns = MAX_MOBS - mobCount;

            Random rand = new Random();

            for (int i = 0; i < amountSpawns; i++) {
                int randX = rand.nextInt(17) - 8;
                int randZ = rand.nextInt(17) - 8;

                EntityLiving entity = getRandomMob(world);
                entity.getEntityData().setBoolean(MOB_TAG, true);
                entity.setLocationAndAngles(pos.getX() + randX, pos.getY(), pos.getZ() + randZ, 0, 0);
                world.spawnEntity(entity);
            }
        }

        ticksSinceLastSpawn++;
    }

    private EntityLiving getRandomMob(World world) {
        Random rand = new Random();
        int mob = rand.nextInt(4);

        switch (mob) {
            case 0:
                EntityLiving entityZombie = new EntityZombie(world);
                entityZombie.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(Items.LEATHER_HELMET));
                return entityZombie;
            case 1:
                EntityLiving entitySkeleton = new EntitySkeleton(world);
                entitySkeleton.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(Items.LEATHER_HELMET));
                return entitySkeleton;
            case 2:
                EntitySpider spider = new EntitySpider(world);

                List<EntityAIBase> tasksToRemove = new ArrayList<>();
                for(EntityAITasks.EntityAITaskEntry a : spider.targetTasks.taskEntries){
                    EntityAIBase ai = a.action;

                    if(ai instanceof EntityAINearestAttackableTarget){
                        tasksToRemove.add(ai);
                    }
                }
                tasksToRemove.forEach(spider.targetTasks::removeTask);

                spider.targetTasks.addTask(5, new AISpiderTargetLunar<>(spider, EntityPlayer.class));
                spider.targetTasks.onUpdateTasks();

                return spider;
            case 3:
                return new EntityCreeper(world);
        }

        return new EntityZombie(world);
    }

    private void randomizeDelay() {
        Random rand = new Random();
        this.currentDelay = rand.nextInt(MAX_SPAWN_DELAY - MIN_SPAWN_DELAY) + MIN_SPAWN_DELAY;
    }

    static class AISpiderTargetLunar<T extends EntityLivingBase> extends EntityAINearestAttackableTarget<T> {
        public AISpiderTargetLunar(EntitySpider spider, Class<T> classTarget)
        {
            super(spider, classTarget, true);
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        public boolean shouldExecute()
        {
            return super.shouldExecute();
        }
    }
}
