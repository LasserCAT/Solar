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

public class RitualSolarProtection extends Ritual {

    public RitualSolarProtection() {
        super("Rite of Solar Protection", 1000, 0);
        setRegistryName("ritualsolarprotection");
    }

    @Override
    public void performRitual(TileAltar altar, EntityPlayer player) {
        System.out.println("Gives spell");
        givePlayerSpell(player, ModSpells.spellSolarProtection, amuletEnergy());
    }

    @Override
    public List<RitualComponent> getRitualComponents() {
        List<RitualComponent> ritualComponents = new ArrayList<>();

        ritualComponents.add(new RitualComponent(new BlockPos(0, 0, -4), RuneType.SUN));
        ritualComponents.add(new RitualComponent(new BlockPos(0, 0, 4), RuneType.SUN));
        ritualComponents.add(new RitualComponent(new BlockPos(-4, 0, 0), RuneType.SUN));
        ritualComponents.add(new RitualComponent(new BlockPos(4, 0, 0), RuneType.SUN));

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
