package com.mart.solar.common.network.handlers;

import com.mart.solar.Solar;
import com.mart.solar.common.network.packets.PacketTime;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;

import java.io.IOException;

public class ServerPacketHandler {

    protected String channelName;

    @SubscribeEvent
    public void onServerPacket(FMLNetworkEvent.ServerCustomPacketEvent event) throws IOException
    {
        channelName = event.getPacket().channel();

        if (channelName.equalsIgnoreCase(Solar.networkChannelName))
        {
            PacketTime.processPacketOnServerSide(event.getPacket().payload(), event.getPacket().getTarget());
        }
    }
}
