package com.mart.solar.common.rituals;

import com.mart.solar.Solar;
import com.mart.solar.api.enums.CircleTypes;
import com.mart.solar.common.network.packets.PacketTime;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.io.IOException;

public class RitualRisingSun extends Ritual {

    boolean activated = false;

    public RitualRisingSun() {
        super("Ritual of the Rising Sun", 1000, 0);

        MinecraftForge.EVENT_BUS.register(this);

        types.add(CircleTypes.SUN);
        fireRunes = 2;

        runes.put(2, 1);
        runes.put(6, 1);
    }

    @Override
    public void activateRitual(EntityPlayer player, float solar, float lunar) {
        if (player.getEntityWorld().getWorldTime() % 24000 > 0 && player.getEntityWorld().getWorldTime() % 24000 < 13000 || solar < getRitualSolarCost()) {
            player.sendMessage(new TextComponentString("Ritual failed"));
        } else {
            activated = true;
        }

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


}
