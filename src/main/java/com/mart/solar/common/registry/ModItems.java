package com.mart.solar.common.registry;

import com.mart.solar.Solar;
import com.mart.solar.common.blocks.base.BlockEnum;
import com.mart.solar.common.blocks.enums.EnumSunBurnt;
import com.mart.solar.common.items.enums.RuneType;
import com.mart.solar.common.items.ItemRune;
import com.mart.solar.common.items.*;
import com.mart.solar.common.items.base.ItemBase;
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

@GameRegistry.ObjectHolder(Solar.MODID)
@Mod.EventBusSubscriber(modid = Solar.MODID)
public class ModItems {

    public static final Item DULL_AMULET = Items.AIR;
    public static final Item RITUAL_AMULET = Items.AIR;
    public static final Item JOURNAL = Items.AIR;
    public static final Item SOLAR_FOCUS = Items.AIR;
    public static final Item LUNAR_FOCUS = Items.AIR;

    public static final Item SILVER_INGOT = Items.AIR;
    public static final Item SILVER_NUGGET = Items.AIR;

    public static final Item RUNES = new ItemRune<>(RuneType.class);

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(new ItemDullAmulet("dull_amulet"));
        event.getRegistry().register(new ItemRitualAmulet("ritual_amulet"));
        event.getRegistry().register(new ItemGuideBook("journal"));
        event.getRegistry().register(new ItemSolarFocus("solar_focus"));
        event.getRegistry().register(new ItemLunarFocus("lunar_focus"));
        event.getRegistry().register(new ItemSilverIngot("silver_ingot"));
        event.getRegistry().register(new ItemSilverNugget("silver_nugget"));

        event.getRegistry().register(RUNES.setRegistryName("runes"));

        ModBlocks.initItemBlocks(event);
    }

    @SubscribeEvent
    public static void registerItems(ModelRegistryEvent event) {
        ((ItemBase) DULL_AMULET).registerItemModel();
        ((ItemBase) RITUAL_AMULET).registerItemModel();
        ((ItemBase) JOURNAL).registerItemModel();
        ((ItemBase) SOLAR_FOCUS).registerItemModel();
        ((ItemBase) LUNAR_FOCUS).registerItemModel();
        ((ItemBase) SILVER_INGOT).registerItemModel();
        ((ItemBase) SILVER_NUGGET).registerItemModel();

        for (RuneType type : RuneType.values()) {
            ModelLoader.setCustomModelResourceLocation(RUNES, type.ordinal(), new ModelResourceLocation(RUNES.getRegistryName(), "type=" + type.name().toLowerCase(Locale.ENGLISH)));
        }
    }
}