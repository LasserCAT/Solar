package com.mart.solar.network;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

public class SolarPacketHandler {

    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel("solar");

    public SolarPacketHandler() {

    }

    public static void init() {
        //INSTANCE.registerMessage(InfuserPacketHandler.class, InfuserPacket.class, 0, Side.CLIENT);
    }

}
