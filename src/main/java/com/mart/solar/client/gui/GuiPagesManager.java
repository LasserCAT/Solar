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

        GuiCategory journalCategory = new GuiCategory(ModItems.JOURNAL);
        GuiCategory ritualCategory = new GuiCategory(ModItems.RITUAL_AMULET);

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

        journalCategory.addPage(new GuiPage("Celestial Altar",
                new PageTextComponent(I18n.format("guide.celestialaltar.info"))
        ));

        journalCategory.addPage(new GuiPage("Ritual Amulet",
                new PageTextComponent(I18n.format("guide.ritualamulet.info")),
                new CraftingGridComponent(
                        Items.AIR, Items.GOLD_INGOT, Items.AIR,
                        Items.GOLD_INGOT, ModItems.SILVER_INGOT, Items.GOLD_INGOT,
                        Items.AIR, Items.GOLD_INGOT, Items.AIR)
                        .setCraftedItem(ModItems.DULL_AMULET)
                        .pageNumber(2)

        ));

        journalCategory.addPage(new GuiPage("Menhir",
                new PageTextComponent(I18n.format("guide.menhir.info")),
                new PageTextComponent(I18n.format("guide.menhir.creation"))
        ));

        journalCategory.addPage(new GuiPage("Runes",
                new PageTextComponent(I18n.format("guide.runes.info"))
        ));

        journalCategory.addPage(new GuiPage("Rune Infuser",
                new PageTextComponent(I18n.format("guide.runeinfuser.info1")),
                new PageTextComponent(I18n.format("guide.runeinfuser.info2")),
                new PageTextComponent(I18n.format("guide.runeinfuser.info2.1")).pageNumber(2),
                new PageTextComponent(I18n.format("guide.runeinfuser.info3")).pageNumber(2),
                new PageTextComponent(I18n.format("guide.runeinfuser.craftinginfo")).pageNumber(2),
                new CraftingGridComponent(
                        Items.AIR, Items.GOLD_INGOT, Items.AIR,
                        Items.GOLD_INGOT, Item.getItemFromBlock(Blocks.PLANKS), Items.GOLD_INGOT,
                        Item.getItemFromBlock(Blocks.PLANKS), Item.getItemFromBlock(Blocks.PLANKS), Item.getItemFromBlock(Blocks.PLANKS))
                        .setCraftedItem(ModItems.SOLAR_FOCUS)
                        .pageNumber(3),
                new CraftingGridComponent(
                        Item.getItemFromBlock(Blocks.PLANKS), Item.getItemFromBlock(Blocks.PLANKS), Item.getItemFromBlock(Blocks.PLANKS),
                        Items.GOLD_INGOT, ModItems.SOLAR_FOCUS, Items.GOLD_INGOT,
                        ModItems.SILVER_INGOT, ModItems.LUNAR_FOCUS, ModItems.SILVER_INGOT)
                        .setCraftedItem(Item.getItemFromBlock(ModBlocks.runeInfuser))
                        .pageNumber(4)
        ));

        journalCategory.addPage(new GuiPage("Moon Flower",
                new PageTextComponent(I18n.format("guide.moonflower.info"))
        ));

        journalCategory.addPage(new GuiPage("Sundial",
                new PageTextComponent(I18n.format("guide.sundial.info")),
                new CraftingGridComponent(
                        Item.getItemFromBlock(Blocks.PLANKS), Item.getItemFromBlock(Blocks.PLANKS), Item.getItemFromBlock(Blocks.PLANKS),
                        Item.getItemFromBlock(Blocks.PLANKS), Items.CLOCK,                          Item.getItemFromBlock(Blocks.PLANKS),
                        Item.getItemFromBlock(Blocks.PLANKS), Item.getItemFromBlock(Blocks.PLANKS), Item.getItemFromBlock(Blocks.PLANKS))
                        .setCraftedItem(Item.getItemFromBlock(ModBlocks.sundial))
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

        ritualCategory.addPage(new GuiPage("Rite of Clear Skies",
                new PageTextComponent(I18n.format("guide.ritualclearskies.info")),
                new PageRitualComponent(RitualManager.getByRegName("ritualclearskies"))
        ));

        ritualCategory.addPage(new GuiPage("Rite of Rain",
                new PageTextComponent(I18n.format("guide.ritualrain.info")),
                new PageRitualComponent(RitualManager.getByRegName("ritualrain"))
        ));

        ritualCategory.addPage(new GuiPage("Rite of High Tides",
                new PageTextComponent(I18n.format("guide.ritualhightide.info")),
                new PageRitualComponent(RitualManager.getByRegName("ritualhightide"))
        ));

        ritualCategory.addPage(new GuiPage("Rite of Summer Heat",
                new PageTextComponent(I18n.format("guide.ritualsummerheat.info")),
                new PageRitualComponent(RitualManager.getByRegName("ritualsummerheat"))
        ));

        ritualCategory.addPage(new GuiPage("Rite of Rising Moon",
                new PageTextComponent(I18n.format("guide.ritualrisingmoon.info")),
                new PageRitualComponent(RitualManager.getByRegName("ritualrisingmoon"))
        ));

        ritualCategory.addPage(new GuiPage("Rite of Rising Sun",
                new PageTextComponent(I18n.format("guide.ritualrisingsun.info")),
                new PageRitualComponent(RitualManager.getByRegName("ritualrisingsun"))
        ));

        ritualCategory.addPage(new GuiPage("Rite of Solar Protection",
                new PageTextComponent(I18n.format("guide.ritualsolarprotection.info")),
                new PageRitualComponent(RitualManager.getByRegName("ritualsolarprotection"))
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
