package com.mart.solar.common.rituals;

import com.mart.solar.Solar;
import com.mart.solar.api.enums.CircleTypes;
import com.mart.solar.api.enums.RuneType;
import com.mart.solar.api.ritual.OldRitual;
import com.mart.solar.api.ritual.Ritual;
import com.mart.solar.api.ritual.RitualComponent;
import com.mart.solar.common.network.packets.PacketTime;
import com.mart.solar.common.tileentities.TileAltar;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RitualRisingMoon extends Ritual {

    private boolean activated = false;

    public RitualRisingMoon() {
        super("Ritual of the Rising Moon", 1000, 0);

        setRegistryName("ritualrisingmoon");

        MinecraftForge.EVENT_BUS.register(this);
    }


    @SubscribeEvent
    public void worldTick(TickEvent.WorldTickEvent event) throws IOException {
        if (activated) {
            if (event.world.getWorldTime() % 24000 > 13500 && event.world.getWorldTime() % 24000 < 24000) {
                activated = false;
            } else {
                event.world.setWorldTime(event.world.getWorldTime() + 10);
                Solar.channel.sendToAll(PacketTime.createEntityPacket(event.world.getWorldTime()));
            }
        }
    }

    @Override
    public void performRitual(TileAltar altar, EntityPlayer player) {
        if ((altar.getWorld().getWorldTime() % 24000 <= 13000 || altar.getWorld().getWorldTime() % 24000 >= 23999) && altar.getLunarEnergy() >= getRitualLunarCost()) {
            activated = true;
        }
    }

    @Override
    public List<RitualComponent> getRitualComponents() {
        List<RitualComponent> ritualComponents = new ArrayList<>();

        ritualComponents.add(new RitualComponent(new BlockPos(0, 0, -4), RuneType.TIME));
        ritualComponents.add(new RitualComponent(new BlockPos(0, 0, 4), RuneType.TIME));
        ritualComponents.add(new RitualComponent(new BlockPos(-4, 0, 0), RuneType.MOON));
        ritualComponents.add(new RitualComponent(new BlockPos(4, 0, 0), RuneType.MOON));

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
