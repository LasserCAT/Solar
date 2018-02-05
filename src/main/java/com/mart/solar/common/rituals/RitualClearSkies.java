package com.mart.solar.common.rituals;

import com.mart.solar.api.enums.RuneType;
import com.mart.solar.api.ritual.Ritual;
import com.mart.solar.api.ritual.RitualComponent;
import com.mart.solar.common.tileentities.TileAltar;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.List;

public class RitualClearSkies extends Ritual {

    public RitualClearSkies() {
        super("Rite of the Clear Skies", 1000, 0);

        setRegistryName("ritualclearskies");
    }

    @Override
    public void performRitual(TileAltar altar) {
        if (!altar.getWorld().isRemote) {
            altar.getWorld().getWorldInfo().setRaining(false);
            altar.getWorld().getWorldInfo().setThundering(false);
        }
    }

    @Override
    public List<RitualComponent> getRitualComponents() {
        List<RitualComponent> ritualComponents = new ArrayList<>();

        ritualComponents.add(new RitualComponent(new BlockPos(0, 0, -4), RuneType.WIND));
        ritualComponents.add(new RitualComponent(new BlockPos(0, 0, 4), RuneType.WIND));
        ritualComponents.add(new RitualComponent(new BlockPos(-4, 0, 0), RuneType.WIND));
        ritualComponents.add(new RitualComponent(new BlockPos(4, 0, 0), RuneType.WIND));

        ritualComponents.add(new RitualComponent(new BlockPos(3, 0, -3), null));
        ritualComponents.add(new RitualComponent(new BlockPos(3, 0, 3), null));
        ritualComponents.add(new RitualComponent(new BlockPos(-3, 0, -3), null));
        ritualComponents.add(new RitualComponent(new BlockPos(-3, 0, 3), null));

        return ritualComponents;
    }
}
