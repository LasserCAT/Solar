package com.mart.solar.common.rituals;

import com.mart.solar.api.enums.RuneType;
import com.mart.solar.api.ritual.Ritual;
import com.mart.solar.api.ritual.RitualComponent;
import com.mart.solar.common.registry.ModSpells;
import com.mart.solar.common.tileentities.TileAltar;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.List;

public class RitualHighTide extends Ritual {

    public RitualHighTide() {
        super("Rite of the High Tide", 0, 10000);
        setRegistryName("ritualhightide");
    }


    @Override
    public void performRitual(TileAltar altar, EntityPlayer player) {
        givePlayerSpell(player, ModSpells.spellHighTide);
    }

    @Override
    public List<RitualComponent> getRitualComponents() {
        List<RitualComponent> ritualComponents = new ArrayList<>();

        ritualComponents.add(new RitualComponent(new BlockPos(0, 0, -4), RuneType.WATER));
        ritualComponents.add(new RitualComponent(new BlockPos(0, 0, 4), RuneType.WATER));
        ritualComponents.add(new RitualComponent(new BlockPos(-4, 0, 0), RuneType.WATER));
        ritualComponents.add(new RitualComponent(new BlockPos(4, 0, 0), RuneType.WATER));

        ritualComponents.add(new RitualComponent(new BlockPos(3, 0, -3), null));
        ritualComponents.add(new RitualComponent(new BlockPos(3, 0, 3), null));
        ritualComponents.add(new RitualComponent(new BlockPos(-3, 0, -3), null));
        ritualComponents.add(new RitualComponent(new BlockPos(-3, 0, 3), null));

        return ritualComponents;
    }
}
