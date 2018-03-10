package com.mart.solar.common.rituals;

import com.mart.solar.common.items.enums.RuneType;
import com.mart.solar.api.ritual.Ritual;
import com.mart.solar.api.ritual.RitualComponent;
import com.mart.solar.common.spells.SpellSummerHeat;
import com.mart.solar.common.tileentities.TileAltar;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.List;

public class RitualSummerHeat extends Ritual {

    public RitualSummerHeat() {
        super("ritual_summer_heat");
    }

    @Override
    public void performRitual(TileAltar altar, EntityPlayer player) {
        givePlayerSpell(player, new SpellSummerHeat(), amuletEnergy());
    }

    @Override
    public List<RitualComponent> getRitualComponents() {
        List<RitualComponent> ritualComponents = new ArrayList<>();

        ritualComponents.add(new RitualComponent(new BlockPos(0, 0, -4), RuneType.FIRE));
        ritualComponents.add(new RitualComponent(new BlockPos(0, 0, 4), RuneType.FIRE));
        ritualComponents.add(new RitualComponent(new BlockPos(-4, 0, 0), RuneType.FIRE));
        ritualComponents.add(new RitualComponent(new BlockPos(4, 0, 0), RuneType.FIRE));

        ritualComponents.add(new RitualComponent(new BlockPos(3, 0, -3), null));
        ritualComponents.add(new RitualComponent(new BlockPos(3, 0, 3), null));
        ritualComponents.add(new RitualComponent(new BlockPos(-3, 0, -3), null));
        ritualComponents.add(new RitualComponent(new BlockPos(-3, 0, 3), null));

        return ritualComponents;
    }

    @Override
    public int amuletEnergy() {
        return 40;
    }
}
