package com.mart.solar.common.rituals;

import com.mart.solar.api.enums.RuneType;
import com.mart.solar.api.ritual.Ritual;
import com.mart.solar.api.ritual.RitualComponent;
import com.mart.solar.common.spells.SpellBotanicalGrowth;
import com.mart.solar.common.tileentities.TileAltar;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.List;

public class RitualBotanicalGrowth extends Ritual {


    public RitualBotanicalGrowth(String registryName) {
        super(registryName);
    }

    @Override
    public void performRitual(TileAltar altar, EntityPlayer player) {
        givePlayerSpell(player, new SpellBotanicalGrowth(), amuletEnergy());
    }

    @Override
    public List<RitualComponent> getRitualComponents() {
        List<RitualComponent> ritualComponents = new ArrayList<>();

        ritualComponents.add(new RitualComponent(new BlockPos(0, 0, -4), RuneType.LIFE));
        ritualComponents.add(new RitualComponent(new BlockPos(0, 0, 4), RuneType.LIFE));
        ritualComponents.add(new RitualComponent(new BlockPos(-4, 0, 0), RuneType.LIFE));
        ritualComponents.add(new RitualComponent(new BlockPos(4, 0, 0), RuneType.LIFE));

        ritualComponents.add(new RitualComponent(new BlockPos(3, 0, -3), RuneType.SUN));
        ritualComponents.add(new RitualComponent(new BlockPos(3, 0, 3), RuneType.SUN));
        ritualComponents.add(new RitualComponent(new BlockPos(-3, 0, -3), RuneType.SUN));
        ritualComponents.add(new RitualComponent(new BlockPos(-3, 0, 3), RuneType.SUN));

        return ritualComponents;
    }

    @Override
    public int amuletEnergy() {
        return 0;
    }
}
