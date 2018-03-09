package com.mart.solar.client.gui;

import com.mart.solar.api.ritual.RitualManager;
import com.mart.solar.client.gui.pages.GuiPage;
import com.mart.solar.client.gui.pages.component.CraftingGridComponent;
import com.mart.solar.client.gui.pages.component.PageRitualComponent;
import com.mart.solar.client.gui.pages.component.PageTextComponent;
import com.mart.solar.common.registry.ModBlocks;
import com.mart.solar.common.registry.ModItems;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

import java.util.*;

public class GuiPagesManager {

    private static List<GuiCategory> categories;

    private static Map<UUID, GuiBook> playerGUI = new HashMap<>();


    public GuiPagesManager(){

    }

    private static void init(){
        categories = new ArrayList<>();

        GuiCategory journalCategory = new GuiCategory(ModItems.JOURNAL, "Solar");
        GuiCategory ritualCategory = new GuiCategory(ModItems.RITUAL_AMULET, "Rituals");

        categories.add(journalCategory);
        categories.add(ritualCategory);


        journalCategory.addPage(new GuiPage("Me",
                new PageTextComponent(I18n.format("guide.me.info1")),
                new PageTextComponent(I18n.format("guide.me.info2")),
                new PageTextComponent(I18n.format("guide.me.info2.2")).pageNumber(2),
                new PageTextComponent(I18n.format("guide.me.info3")).pageNumber(2)
        ));

        journalCategory.addPage(new GuiPage("Solar",
                new PageTextComponent(I18n.format("guide.solar.info"))));

        journalCategory.addPage(new GuiPage(I18n.format("tile.solar.altar.name"),
                new PageTextComponent(I18n.format("guide.celestial_altar.info"))
        ));

        journalCategory.addPage(new GuiPage(I18n.format("item.solar.ritual_amulet.name"),
                new PageTextComponent(I18n.format("guide.ritual_amulet.info")),
                new CraftingGridComponent(
                        Items.AIR,          Items.GOLD_INGOT,       Items.AIR,
                        Items.GOLD_INGOT,   ModItems.SILVER_INGOT,  Items.GOLD_INGOT,
                        Items.AIR,          Items.GOLD_INGOT,       Items.AIR)
                        .setCraftedItem(ModItems.DULL_AMULET)
                        .pageNumber(2)
        ));

        journalCategory.addPage(new GuiPage(I18n.format("tile.solar.menhir.name"),
                new PageTextComponent(I18n.format("guide.menhir.info")),
                new PageTextComponent(I18n.format("guide.menhir.creation"))
        ));

        journalCategory.addPage(new GuiPage("Runes",
                new PageTextComponent(I18n.format("guide.runes.info"))
        ));

        journalCategory.addPage(new GuiPage(I18n.format("tile.solar.rune_infuser.name"),
                new PageTextComponent(I18n.format("guide.rune_infuser.info1")),
                new PageTextComponent(I18n.format("guide.rune_infuser.info2")),
                new PageTextComponent(I18n.format("guide.rune_infuser.info2.1")).pageNumber(2),
                new PageTextComponent(I18n.format("guide.rune_infuser.info3")).pageNumber(2),
                new PageTextComponent(I18n.format("guide.rune_infuser.info4")).pageNumber(2),
                new PageTextComponent(I18n.format("guide.rune_infuser.crafting_info")).pageNumber(3),
                new CraftingGridComponent(
                        Items.AIR,                              Items.GOLD_INGOT,                       Items.AIR,
                        Items.GOLD_INGOT,                       Item.getItemFromBlock(Blocks.PLANKS),   Items.GOLD_INGOT,
                        Item.getItemFromBlock(Blocks.PLANKS),   Item.getItemFromBlock(Blocks.PLANKS),   Item.getItemFromBlock(Blocks.PLANKS))
                        .setCraftedItem(ModItems.SOLAR_FOCUS)
                        .pageNumber(3),
                new CraftingGridComponent(
                        Item.getItemFromBlock(Blocks.PLANKS),   Item.getItemFromBlock(Blocks.PLANKS),   Item.getItemFromBlock(Blocks.PLANKS),
                        Items.GOLD_INGOT,                       ModItems.SOLAR_FOCUS,                   Items.GOLD_INGOT,
                        ModItems.SILVER_INGOT,                  ModItems.LUNAR_FOCUS,                   ModItems.SILVER_INGOT)
                        .setCraftedItem(Item.getItemFromBlock(ModBlocks.RUNE_INFUSER))
                        .pageNumber(4)
        ));

        journalCategory.addPage(new GuiPage(I18n.format("tile.solar.flower_moon.name"),
                new PageTextComponent(I18n.format("guide.moon_flower.info"))
        ));

        journalCategory.addPage(new GuiPage(I18n.format("tile.solar.flower_fiery.name"),
                new PageTextComponent(I18n.format("guide.fiery_flower.info"))
        ));

        journalCategory.addPage(new GuiPage(I18n.format("tile.solar.sundial.name"),
                new PageTextComponent(I18n.format("guide.sundial.info")),
                new CraftingGridComponent(
                        Item.getItemFromBlock(Blocks.PLANKS), Item.getItemFromBlock(Blocks.PLANKS), Item.getItemFromBlock(Blocks.PLANKS),
                        Item.getItemFromBlock(Blocks.PLANKS), Items.CLOCK,                          Item.getItemFromBlock(Blocks.PLANKS),
                        Item.getItemFromBlock(Blocks.PLANKS), Item.getItemFromBlock(Blocks.PLANKS), Item.getItemFromBlock(Blocks.PLANKS))
                        .setCraftedItem(Item.getItemFromBlock(ModBlocks.SUNDIAL))
                        .pageNumber(4)
        ));

        journalCategory.addPage(new GuiPage("Rituals",
                new PageTextComponent(I18n.format("guide.rituals.info")),
                new PageTextComponent(I18n.format("guide.rituals.layout"))
        ));

        journalCategory.addPage(new GuiPage("Spells",
                new PageTextComponent(I18n.format("guide.spells.info"))
        ));

        //Rituals

        ritualCategory.addPage(new GuiPage(I18n.format("ritual.solar.ritual_clear_skies"),
                new PageTextComponent(I18n.format("guide.ritual_clear_skies.info")),
                new PageRitualComponent(RitualManager.getByRegName("ritual_clear_skies"))
        ));

        ritualCategory.addPage(new GuiPage(I18n.format("ritual.solar.ritual_rain"),
                new PageTextComponent(I18n.format("guide.ritual_rain.info")),
                new PageRitualComponent(RitualManager.getByRegName("ritual_rain"))
        ));

        ritualCategory.addPage(new GuiPage(I18n.format("ritual.solar.ritual_high_tide"),
                new PageTextComponent(I18n.format("guide.ritual_high_tide.info")),
                new PageRitualComponent(RitualManager.getByRegName("ritual_high_tide"))
        ));

        ritualCategory.addPage(new GuiPage(I18n.format("ritual.solar.ritual_summer_heat"),
                new PageTextComponent(I18n.format("guide.ritual_summer_heat.info")),
                new PageRitualComponent(RitualManager.getByRegName("ritual_summer_heat"))
        ));

        ritualCategory.addPage(new GuiPage(I18n.format("ritual.solar.ritual_rising_moon"),
                new PageTextComponent(I18n.format("guide.ritual_rising_moon.info")),
                new PageRitualComponent(RitualManager.getByRegName("ritual_rising_moon"))
        ));

        ritualCategory.addPage(new GuiPage(I18n.format("ritual.solar.ritual_rising_sun"),
                new PageTextComponent(I18n.format("guide.ritual_rising_sun.info")),
                new PageRitualComponent(RitualManager.getByRegName("ritual_rising_sun"))
        ));

        ritualCategory.addPage(new GuiPage(I18n.format("ritual.solar.ritual_solar_protection"),
                new PageTextComponent(I18n.format("guide.ritual_solar_protection.info")),
                new PageRitualComponent(RitualManager.getByRegName("ritual_solar_protection"))
        ));

        ritualCategory.addPage(new GuiPage(I18n.format("ritual.solar.ritual_lunar_embrace"),
                new PageTextComponent(I18n.format("guide.ritual_lunar_embrace.info")),
                new PageRitualComponent(RitualManager.getByRegName("ritual_lunar_embrace"))
        ));

        ritualCategory.addPage(new GuiPage(I18n.format("ritual.solar.ritual_hunt"),
                new PageTextComponent(I18n.format("guide.ritual_hunt.info")),
                new PageRitualComponent(RitualManager.getByRegName("ritual_hunt"))
        ));

        ritualCategory.addPage(new GuiPage(I18n.format("ritual.solar.ritual_botanical_growth"),
                new PageTextComponent(I18n.format("guide.ritual_botanical_growth.info")),
                new PageRitualComponent(RitualManager.getByRegName("ritual_botanical_growth"))
        ));
    }

    public static GuiBook getPlayerGUI(EntityPlayer player){
        GuiBook gui = playerGUI.get(player.getUniqueID());
        if(gui == null){
            playerGUI.put(player.getUniqueID(), new GuiBook());
            return  playerGUI.get(player.getUniqueID());
        }
        else{
            return gui;
        }

    }

    public static List<GuiCategory> getCategories() {
        if(categories== null){
            init();
        }
        return categories;
    }

    public static Map<UUID, GuiBook> getPlayerGUI() {
        return playerGUI;
    }

    public static GuiBase getDefaultCategory() {
        return getCategories().get(0);
    }
}
