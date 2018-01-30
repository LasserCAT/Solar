package com.mart.solar.rituals;

import com.mart.solar.api.enums.CircleTypes;
import com.mart.solar.spells.Spell;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Ritual {

    private String ritualName;
    private float ritualSolarCost;
    private float ritualLunarCost;
    boolean hasSpell = false;

    int fireRunes = 0;
    int waterRunes = 0;
    int earthRunes = 0;
    int windRunes = 0;
    int timeRunes = 0;
    int lifeRunes = 0;
    int sunRunes = 0;
    int moonRunes = 0;

    List<CircleTypes> types = new ArrayList<>();

    Map<Integer, Integer> runes = new HashMap<>();

    Spell spell;


    public Ritual(String ritualName, float ritualSolarCost, float ritualLunarCost) {
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
