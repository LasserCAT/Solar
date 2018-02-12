package com.mart.solar.common.rituals;

import com.mart.solar.Solar;
import com.mart.solar.api.enums.RuneType;
import com.mart.solar.api.ritual.Ritual;
import com.mart.solar.api.ritual.RitualComponent;
import com.mart.solar.common.network.packets.PacketTime;
import com.mart.solar.common.tileentities.TileAltar;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RitualRisingSun extends Ritual {

    private boolean activated = false;

    public RitualRisingSun() {
        super("Ritual of the Rising Sun", 1000, 0);

        this.setRegistryName("ritualrisingsun");

        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void worldTick(TickEvent.WorldTickEvent event) throws IOException {
        if (activated) {
            if (event.world.getWorldTime() % 24000 > 0 && event.world.getWorldTime() % 24000 < 13000) {
                activated = false;
            } else {
                event.world.setWorldTime(event.world.getWorldTime() + 10);
                Solar.channel.sendToAll(PacketTime.createEntityPacket(event.world.getWorldTime()));
            }
        }
    }


    @Override
    public void performRitual(TileAltar altar, EntityPlayer player) {
        if ((altar.getWorld().getWorldTime() % 24000 <= 0 || altar.getWorld().getWorldTime() % 24000 >= 13000) && altar.getSolarEnergy() >= getRitualSolarCost()) {
            activated = true;
        }
    }

    @Override
    public List<RitualComponent> getRitualComponents() {
        List<RitualComponent> ritualComponents = new ArrayList<>();

        ritualComponents.add(new RitualComponent(new BlockPos(0, 0, -4), RuneType.TIME));
        ritualComponents.add(new RitualComponent(new BlockPos(0, 0, 4), RuneType.TIME));
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
