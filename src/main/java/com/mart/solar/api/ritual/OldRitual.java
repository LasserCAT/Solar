package com.mart.solar.api.ritual;

import com.mart.solar.api.enums.CircleTypes;
import com.mart.solar.common.spells.Spell;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.registries.IForgeRegistryEntry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class OldRitual {

    protected String ritualName;
    protected float ritualSolarCost;
    protected float ritualLunarCost;
    protected boolean hasSpell = false;

    protected int fireRunes = 0;
    protected int waterRunes = 0;
    protected int earthRunes = 0;
    protected int windRunes = 0;
    protected int timeRunes = 0;
    protected int lifeRunes = 0;
    protected int sunRunes = 0;
    protected int moonRunes = 0;

    protected  List<CircleTypes> types = new ArrayList<>();

    protected Map<Integer, Integer> runes = new HashMap<>();

    protected Spell spell;


    public OldRitual(String ritualName, float ritualSolarCost, float ritualLunarCost) {
        this.ritualName = ritualName;
        this.ritualSolarCost = ritualSolarCost;
        this.ritualLunarCost = ritualLunarCost;
    }

    public abstract void activateRitual(EntityPlayer player, float solar, float lunar);

    public Map<Integer, Integer> getRunes() {
        return runes;
    }

    public int getFireRunes() {
        return fireRunes;
    }

    public int getWaterRunes() {
        return waterRunes;
    }

    public int getEarthRunes() {
        return earthRunes;
    }

    public int getWindRunes() {
        return windRunes;
    }

    public int getTimeRunes() {
        return timeRunes;
    }

    public int getLifeRunes() {
        return lifeRunes;
    }

    public int getSunRunes() {
        return sunRunes;
    }

    public int getMoonRunes() {
        return moonRunes;
    }

    public List<CircleTypes> getTypes() {
        return types;
    }

    public String getRitualName() {
        return ritualName;
    }

    public void setRitualName(String ritualName) {
        this.ritualName = ritualName;
    }

    public float getRitualSolarCost() {
        return ritualSolarCost;
    }

    public void setRitualSolarCost(float ritualSolarCost) {
        this.ritualSolarCost = ritualSolarCost;
    }

    public float getRitualLunarCost() {
        return ritualLunarCost;
    }

    public void setRitualLunarCost(float ritualLunarCost) {
        this.ritualLunarCost = ritualLunarCost;
    }

    public boolean isHasSpell() {
        return hasSpell;
    }

    public Spell getSpell() {
        return spell;
    }
}
