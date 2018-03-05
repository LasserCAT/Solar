package com.mart.solar.common.registry;

import com.mart.solar.api.enums.RuneType;
import com.mart.solar.common.items.ItemRune;
import com.mart.solar.common.items.*;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.Locale;

@GameRegistry.ObjectHolder("solar")
@Mod.EventBusSubscriber(modid = "solar")
public class ModItems {

    @GameRegistry.ObjectHolder("ritual_amulet")
    public static Item RITUAL_AMULET = Items.AIR;

    @GameRegistry.ObjectHolder("silver_ingot")
    public static Item SILVER_INGOT = Items.AIR;

    @GameRegistry.ObjectHolder("silver_nugget")
    public static Item SILVER_NUGGET = Items.AIR;

    @GameRegistry.ObjectHolder("journal")
    public static Item JOURNAL = Items.AIR;

    @GameRegistry.ObjectHolder("dull_amulet")
    public static Item DULL_AMULET = Items.AIR;

    @GameRegistry.ObjectHolder("solar_focus")
    public static Item SOLAR_FOCUS = Items.AIR;

    @GameRegistry.ObjectHolder("lunar_focus")
    public static Item LUNAR_FOCUS = Items.AIR;

    public static final Item RUNES = new ItemRune<>(RuneType.class);

    public static void init(RegistryEvent.Register<Item> event) {

        register(new ItemDullAmulet("dull_amulet"), event);
        register(new ItemRitualAmulet("ritual_amulet"), event);
        register(new ItemSilverIngot("silver_ingot"), event);
        register(new ItemSilverNugget("silver_nugget"), event);
        register(new ItemGuideBook("journal"), event);
        register(new ItemSolarFocus("solar_focus"), event);
        register(new ItemLunarFocus("lunar_focus"), event);

        event.getRegistry().register(RUNES.setRegistryName("runes"));
    }

    private static <T extends Item> T register(T item, RegistryEvent.Register<Item> event) {
        event.getRegistry().register(item);

        if (item instanceof ItemBase) {
            ((ItemBase) item).registerItemModel();
        }

        return item;
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        init(event);
        ModBlocks.initItemBlocks(event);
    }

    @SubscribeEvent
    public static void registerItems(ModelRegistryEvent event) {
        for (RuneType type : RuneType.values())
            ModelLoader.setCustomModelResourceLocation(RUNES, type.ordinal(), new ModelResourceLocation(RUNES.getRegistryName(), "type=" + type.name().toLowerCase(Locale.ENGLISH)));
    }

}