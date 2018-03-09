package com.mart.solar.common.rituals;

import com.mart.solar.api.enums.RuneType;
import com.mart.solar.api.ritual.Ritual;
import com.mart.solar.api.ritual.RitualComponent;
import com.mart.solar.common.tileentities.TileAltar;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.List;

public class RitualRain extends Ritual {

    public RitualRain() {
        super("ritual_rain");
    }

    @Override
    public void performRitual(TileAltar altar, EntityPlayer player) {
        if (!altar.getWorld().isRemote) {
            altar.getWorld().getWorldInfo().setThundering(false);
            altar.getWorld().getWorldInfo().setRaining(true);
            altar.getWorld().getWorldInfo().setRainTime(14000);
        }
    }

    @Override
    public List<RitualComponent> getRitualComponents() {
        List<RitualComponent> ritualComponents = new ArrayList<>();

        ritualComponents.add(new RitualComponent(new BlockPos(0, 0, -4), RuneType.WATER));
        ritualComponents.add(new RitualComponent(new BlockPos(0, 0, 4), RuneType.WATER));
        ritualComponents.add(new RitualComponent(new BlockPos(-4, 0, 0), RuneType.WIND));
        ritualComponents.add(new RitualComponent(new BlockPos(4, 0, 0), RuneType.WIND));

        ritualComponents.add(new RitualComponent(new BlockPos(3, 0, -3), null));
        ritualComponents.add(new RitualComponent(new BlockPos(3, 0, 3), null));
        ritualComponents.add(new RitualComponent(new BlockPos(-3, 0, -3), null));
        ritualComponents.add(new RitualComponent(new BlockPos(-3, 0, 3), null));

        return ritualComponents;
    }

    @Override
    public int amuletEnergy() {
        return 0;
    }
}
