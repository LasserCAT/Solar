package com.mart.solar.client.events;

import com.mart.solar.Solar;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber(modid = Solar.MODID)
public class ClientEventHandler {

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void onTextureStitch(TextureStitchEvent event){
        System.out.println("Yeaahahahhaha I joind need to register this yuhfyvgbhvvbuahbvfnjkafnjafnjpasvnjvsbsdvubsaubasbhjasdfbhj");
        event.getMap().registerSprite(new ResourceLocation(Solar.MODID, "light"));
    }

}
