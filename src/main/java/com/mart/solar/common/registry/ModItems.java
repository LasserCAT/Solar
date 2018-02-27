package com.mart.solar.common.registry;

import com.mart.solar.api.enums.RuneType;
import com.mart.solar.common.items.ItemRune;
import com.mart.solar.common.items.*;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Locale;

public class ModItems {

    public static ItemRitualAmulet RITUAL_AMULET;
    public static ItemSilverIngot SILVER_INGOT;
    public static ItemSilverNugget SILVER_NUGGET;
    public static ItemGuideBook JOURNAL;
    public static ItemDullAmulet DULL_AMULET;
    public static ItemSolarFocus SOLAR_FOCUS;
    public static ItemLunarFocus LUNAR_FOCUS;

    public static final Item RUNES = new ItemRune<>(RuneType.class);

    public static void init(RegistryEvent.Register<Item> event) {

        DULL_AMULET = register(new ItemDullAmulet("dull_amulet"), event);
        RITUAL_AMULET = register(new ItemRitualAmulet("ritual_amulet"), event);
        SILVER_INGOT = register(new ItemSilverIngot("silver_ingot"), event);
        SILVER_NUGGET = register(new ItemSilverNugget("silver_nugget"), event);
        JOURNAL = register(new ItemGuideBook("journal"), event);
        SOLAR_FOCUS = register(new ItemSolarFocus("solar_focus"), event);
        LUNAR_FOCUS = register(new ItemLunarFocus("lunar_focus"), event);

        event.getRegistry().register(RUNES.setRegistryName("runes"));

        registerModels();
    }

    private static <T extends Item> T register(T item, RegistryEvent.Register<Item> event) {
        event.getRegistry().register(item);

        if (item instanceof ItemBase) {
            ((ItemBase) item).registerItemModel();
        }

        return item;
    }

    @SideOnly(Side.CLIENT)
    private static void registerModels() {
        for (RuneType type : RuneType.values())
            ModelLoader.setCustomModelResourceLocation(RUNES, type.ordinal(), new ModelResourceLocation(RUNES.getRegistryName(), "type=" + type.name().toLowerCase(Locale.ENGLISH)));
    }

}