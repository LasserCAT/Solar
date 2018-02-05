package com.mart.solar.common.rituals;

import com.mart.solar.Solar;
import com.mart.solar.api.enums.CircleTypes;
import com.mart.solar.api.ritual.OldRitual;
import com.mart.solar.common.network.packets.PacketTime;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.io.IOException;

public class RitualRisingMoon extends OldRitual {

    boolean activated = false;

    public RitualRisingMoon() {
        super("OldRitual of the Rising Moon", 1000, 0);

        MinecraftForge.EVENT_BUS.register(this);

        types.add(CircleTypes.MOON);
        timeRunes = 2;

        runes.put(0, 5);
        runes.put(4, 5);
    }

    @Override
    public void activateRitual(EntityPlayer player, float solar, float lunar) {
        if (player.getEntityWorld().getWorldTime() % 24000 > 13000 && player.getEntityWorld().getWorldTime() % 24000 < 23999 || lunar < getRitualLunarCost()) {
            player.sendMessage(new TextComponentString("OldRitual failed"));
        } else {
            activated = true;
        }
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
}
