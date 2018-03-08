package com.mart.solar.common.spells;

import com.mart.solar.api.interfaces.IPlaceableSpell;
import com.mart.solar.api.spell.Spell;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.BiomeDictionary;

import java.util.List;
import java.util.Random;

public class SpellHunt extends Spell implements IPlaceableSpell {

    private final static int MIN_SPAWN_DELAY = 100;
    private final static int MAX_SPAWN_DELAY = 200;
    private final static int MAX_MOBS = 10;

    private int currentDelay = 0;
    private int ticksSinceLastSpawn = 0;

    public SpellHunt() {
        super("Spell of the Hunt");

        this.lifeSpan = 14000;
    }

    @Override
    public void activateSpell(EntityPlayer player, ItemStack stack) {
        this.placeSpell(player, stack);
    }

    @Override
    public String getSpellRegistryName() {
        return "spell_hunt";
    }

    @Override
    public void saveDataToNBT(NBTTagCompound tag) {

    }

    @Override
    public void getDataFromNBT(NBTTagCompound tag) {

    }

    @Override
    public Spell getNewInstance() {
        return new SpellHunt();
    }

    @Override
    public void tick(World world, BlockPos pos, int tickValue) {
        if (world.isRemote) {
            return;
        }

        if (this.ticksSinceLastSpawn >= this.currentDelay) {
            this.ticksSinceLastSpawn = 0;
            this.randomizeDelay();

            List<EntityLiving> entitiesInRange = world.getEntitiesWithinAABB(EntityLiving.class,
                    new AxisAlignedBB(pos.getX() - 15, pos.getY() - 5, pos.getZ() - 15,
                            pos.getX() + 15, pos.getY() + 5, pos.getZ() + 15));

            int mobCount = entitiesInRange.size();

            if (mobCount >= MAX_MOBS) {
                return;
            }

            Random rand = new Random();

            int randX = rand.nextInt(17) - 8;
            int randZ = rand.nextInt(17) - 8;

            BlockPos spawnPos = new BlockPos(pos.getX() + randX, pos.getY(), pos.getZ() + randZ);

            EntityLiving entity = getRandomMob(world,spawnPos);
            entity.setLocationAndAngles(spawnPos.getX(), spawnPos.getY(), spawnPos.getZ(), 0, 0);
            world.spawnEntity(entity);
        }

        ticksSinceLastSpawn++;
    }

    private EntityLiving getRandomMob(World world, BlockPos pos) {
        Random rand = new Random();
        int mob;

        if(BiomeDictionary.hasType(world.getBiomeForCoordsBody(pos), BiomeDictionary.Type.JUNGLE)){
            mob = rand.nextInt(6);
        }
        else{
            mob = rand.nextInt(5);
        }

        switch (mob) {
            case 0:
                return new EntityCow(world);
            case 1:
                return new EntityChicken(world);
            case 2:
                return new EntityPig(world);
            case 3:
                return new EntityPig(world);
            case 4:
                return new EntityWolf(world);
            case 5:
                return new EntityOcelot(world);
        }

        return new EntityCow(world);
    }

    private void randomizeDelay() {
        Random rand = new Random();
        this.currentDelay = rand.nextInt(MAX_SPAWN_DELAY - MIN_SPAWN_DELAY) + MIN_SPAWN_DELAY;
    }

}
