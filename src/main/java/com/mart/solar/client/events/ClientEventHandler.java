package com.mart.solar.client.events;

import com.mart.solar.Solar;
import com.mart.solar.common.spells.SpellLunarEmbrace;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber(modid = Solar.MODID)
public class ClientEventHandler {

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void onTextureStitch(TextureStitchEvent event){
        event.getMap().registerSprite(new ResourceLocation(Solar.MODID, "light"));
    }

    @SubscribeEvent
    public static void onMobDamage(LivingDamageEvent event){
        if (event.getSource() == DamageSource.ON_FIRE) {
            if(event.getEntityLiving().getEntityData().getBoolean(SpellLunarEmbrace.MOB_TAG)){
                event.getEntityLiving().setFire(0);
                event.setCanceled(true);
            }
        }
    }

}
