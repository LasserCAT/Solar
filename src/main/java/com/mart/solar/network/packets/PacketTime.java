package com.mart.solar.network.packets;

import com.mart.solar.Solar;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;
import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.internal.FMLProxyPacket;
import net.minecraftforge.fml.relauncher.Side;

import java.io.IOException;

public class PacketTime {

    public final static int packetTypeIDEntity = 1;

    public PacketTime() {
    }

    public static FMLProxyPacket createEntityPacket(long worldTime) throws IOException {
        ByteBufOutputStream bbos = new ByteBufOutputStream(Unpooled.buffer());

        bbos.writeInt(packetTypeIDEntity);
        bbos.writeLong(worldTime);

        FMLProxyPacket thePacket = new FMLProxyPacket(new PacketBuffer(bbos.buffer()), Solar.networkChannelName);

        bbos.close();

        return thePacket;
    }

    public static void processPacketOnClientSide(ByteBuf parBB, Side parSide) throws IOException {
        if (parSide == Side.CLIENT) {

            World theWorld = Minecraft.getMinecraft().world;
            ByteBufInputStream bbis = new ByteBufInputStream(parBB);

            int packetTypeID = bbis.readInt();

            switch (packetTypeID) {
                case packetTypeIDEntity: {
                    long time = bbis.readLong();

                    theWorld.setWorldTime(time);

                    break;
                }
            }

            bbis.close();
        }
    }

    public static void processPacketOnServerSide(ByteBuf payload, Side parSide) {
        if (parSide == Side.SERVER) {

        }
    }
}