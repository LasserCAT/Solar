package com.mart.solar.common;

import com.mart.solar.common.registry.ModBlocks;
import com.mart.solar.common.registry.ModEntities;
import com.mart.solar.common.registry.ModItems;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;

@Mod.EventBusSubscriber
public class CommonProxy {

    public void registerItemRenderer(Item item, int meta) {

    }

    public void preInit() {

    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        ModBlocks.init(event);
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        ModItems.init(event);
        ModBlocks.initItemBlocks(event);
    }

    @SubscribeEvent
    public static void registerEntities(RegistryEvent.Register<EntityEntry> event) {
        ModEntities.regEntities(event);
    }

}
