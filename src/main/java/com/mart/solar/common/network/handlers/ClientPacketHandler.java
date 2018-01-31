package com.mart.solar.common.network.handlers;

import com.mart.solar.Solar;
import com.mart.solar.common.network.packets.PacketTime;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;

import java.io.IOException;

public class ClientPacketHandler extends ServerPacketHandler {

    @SubscribeEvent
    public void onClientPacket(FMLNetworkEvent.ClientCustomPacketEvent event) throws IOException {
        channelName = event.getPacket().channel();

        if (channelName.equalsIgnoreCase(Solar.networkChannelName)) {
            PacketTime.processPacketOnClientSide(event.getPacket().payload(), event.getPacket().getTarget());
        }
    }
}
