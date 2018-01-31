package com.mart.solar.api.util;

import com.mart.solar.api.enums.CircleTypes;
import com.mart.solar.api.enums.RitualLevel;
import com.mart.solar.common.tileentities.TileMenhir;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class RitualContainer {

    private List<CircleTypes> types = new ArrayList<>();
    private List<TileMenhir> menhirs = new ArrayList<>();

    private ItemStack ritualStaff;

    private RitualLevel rcLevel;

    private BlockPos altarPos;

    private World world;

    private EntityPlayer player;

    private int fireRunes = 0;
    private int waterRunes = 0;
    private int earthRunes = 0;
    private int windRunes = 0;
    private int timeRunes = 0;
    private int lifeRunes = 0;
    private int sunRunes = 0;
    private int moonRunes = 0;

    public EntityPlayer getPlayer() {
        return player;
    }

    public void setPlayer(EntityPlayer player) {
        this.player = player;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public RitualContainer(BlockPos altarPos) {
        setAltarPos(altarPos);
    }

    public List<CircleTypes> getTypes() {
        return types;
    }

    public void setTypes(List<CircleTypes> types) {
        this.types = types;
    }

    public List<TileMenhir> getMenhirs() {
        return menhirs;
    }

    public void setMenhirs(List<TileMenhir> menhirs) {
        this.menhirs = menhirs;
    }


    public int getFireRunes() {
        return fireRunes;
    }

    public void addFireRunes() {
        this.fireRunes++;
    }

    public int getWaterRunes() {
        return waterRunes;
    }

    public void addWaterRunes() {
        this.waterRunes++;
    }

    public int getEarthRunes() {
        return earthRunes;
    }

    public void addEarthRunes() {
        this.earthRunes++;
    }

    public int getWindRunes() {
        return windRunes;
    }

    public void addWindRunes() {
        this.windRunes++;
    }

    public int getTimeRunes() {
        return timeRunes;
    }

    public void addTimeRunes() {
        this.timeRunes++;
    }

    public int getLifeRunes() {
        return lifeRunes;
    }

    public void addLifeRunes() {
        this.lifeRunes++;
    }

    public int getSunRunes() {
        return sunRunes;
    }

    public void addSunRunes() {
        this.sunRunes++;
    }

    public int getMoonRunes() {
        return moonRunes;
    }

    public void addMoonRunes() {
        this.moonRunes++;
    }


    public BlockPos getAltarPos() {
        return altarPos;
    }

    public void setAltarPos(BlockPos altarPos) {
        this.altarPos = altarPos;
    }

    public RitualLevel getRcLevel() {
        return rcLevel;
    }

    public void setRcLevel(RitualLevel rcLevel) {
        this.rcLevel = rcLevel;
    }

    public void addTypes(CircleTypes type) {
        types.add(type);
    }

    public ItemStack getRitualStaff() {
        return ritualStaff;
    }

    public void setRitualStaff(ItemStack ritualStaff) {
        this.ritualStaff = ritualStaff;
    }
}
