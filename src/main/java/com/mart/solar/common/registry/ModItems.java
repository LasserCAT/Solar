package com.mart.solar.common.registry;

import com.mart.solar.api.enums.RuneType;
import com.mart.solar.api.util.ItemRuneEnum;
import com.mart.solar.common.items.*;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Locale;

public class ModItems {

    public static ItemRitualAmulet ritualAmulet;
    public static ItemSilverIngot silverIngot;
    public static ItemSilverNugget silverNugget;
    public static ItemGuideBook guideBook;
    public static ItemDullAmulet dullAmulet;
    public static ItemSolarFocus solarFocus;
    public static ItemLunarFocus lunarFocus;

    public static final Item RUNES = new ItemRuneEnum<>(RuneType.class);

    public static void init(RegistryEvent.Register<Item> event) {

        dullAmulet = register(new ItemDullAmulet(), event);
        ritualAmulet = register(new ItemRitualAmulet(), event);
        silverIngot = register(new ItemSilverIngot("silveringot"), event);
        silverNugget = register(new ItemSilverNugget("silver_nugget"), event);
        guideBook = register(new ItemGuideBook(), event);
        solarFocus = register(new ItemSolarFocus(), event);
        lunarFocus = register(new ItemLunarFocus(), event);

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