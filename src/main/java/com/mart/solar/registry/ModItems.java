package com.mart.solar.registry;

import com.mart.solar.api.enums.RuneTypes;
import com.mart.solar.api.util.ItemRuneEnum;
import com.mart.solar.items.ItemBase;
import com.mart.solar.items.ItemGuideBook;
import com.mart.solar.items.ItemRitualStaff;
import com.mart.solar.items.ItemSilverIngot;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

import java.util.Locale;

public class ModItems {

    public static ItemRitualStaff ritualStaff;
    public static ItemSilverIngot silverIngot;
    public static ItemGuideBook guideBook;

    public static final Item RUNES = new ItemRuneEnum<>(RuneTypes.class);

    public static void init(RegistryEvent.Register<Item> event) {

        ritualStaff = register(new ItemRitualStaff("ritualstaff"), event);
        silverIngot = register(new ItemSilverIngot("silverIngot"), event);
        guideBook = register(new ItemGuideBook(), event);

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
    public static void registerModels() {
        for (RuneTypes type : RuneTypes.values())
            ModelLoader.setCustomModelResourceLocation(RUNES, type.ordinal(), new ModelResourceLocation(RUNES.getRegistryName(), "type=" + type.name().toLowerCase(Locale.ENGLISH)));
    }

}