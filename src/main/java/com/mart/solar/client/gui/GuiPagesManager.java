package com.mart.solar.client.gui;

import com.mart.solar.client.gui.pages.GuiPage;
import com.mart.solar.client.gui.pages.component.PageImageComponent;
import com.mart.solar.client.gui.pages.component.PageImageLargeComponent;
import com.mart.solar.client.gui.pages.component.PageTextComponent;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public class GuiPagesManager {

    public static List<GuiPage> guiPages;

    public GuiPagesManager(){

    }

    private static void init(){
        guiPages = new ArrayList<>();

        guiPages.add(new GuiPage("Broken Altar",
                new PageTextComponent(I18n.format("guide.brokenaltar.info"))));


        guiPages.add(new GuiPage("Ritual Amulet",
                new PageTextComponent(I18n.format("guide.ritualamulet.info")),
                new PageImageComponent(new ResourceLocation("solar", "textures/gui/crafting/dull_amulet.png"))
        ));
        guiPages.add(new GuiPage("Celestial Altar",
                new PageTextComponent(I18n.format("guide.celestialaltar.info"))
        ));
        guiPages.add(new GuiPage("Menhir",
                new PageTextComponent(I18n.format("guide.menhir.info"))
        ));
        guiPages.add(new GuiPage("Rune Infuser",
                new PageTextComponent(I18n.format("guide.runeinfuser.info")),
                new PageTextComponent(I18n.format("guide.runeinfuser.craftinginfo")),
                new PageImageComponent(new ResourceLocation("solar", "textures/gui/crafting/focus.png")),
                new PageImageComponent(new ResourceLocation("solar", "textures/gui/crafting/infuser.png"))
        ));
        guiPages.add(new GuiPage("Runes",
                new PageTextComponent(I18n.format("guide.runes.info")),
                new PageImageLargeComponent(new ResourceLocation("solar", "textures/gui/crafting/infuserrecipe.png"))
        ));

        //Rituals

        guiPages.add(new GuiPage("Rite of Clear Skies",
                new PageTextComponent(I18n.format("guide.ritualclearskies.info")),
                new PageImageLargeComponent(new ResourceLocation("solar", "textures/gui/rituallayout/clearskies.png"))
        ));

        guiPages.add(new GuiPage("Rite of Rain",
                new PageTextComponent(I18n.format("guide.ritualrain.info")),
                new PageImageLargeComponent(new ResourceLocation("solar", "textures/gui/rituallayout/rain.png"))
        ));

        guiPages.add(new GuiPage("Rite of Storms",
                new PageTextComponent(I18n.format("guide.ritualstorm.info")),
                new PageImageLargeComponent(new ResourceLocation("solar", "textures/gui/rituallayout/storm.png"))
        ));

        guiPages.add(new GuiPage("Rite of High Tides",
                new PageTextComponent(I18n.format("guide.ritualhightide.info")),
                new PageImageLargeComponent(new ResourceLocation("solar", "textures/gui/rituallayout/hightides.png"))
        ));

        guiPages.add(new GuiPage("Rite of Summer Heat",
                new PageTextComponent(I18n.format("guide.ritualsummerheat.info")),
                new PageImageLargeComponent(new ResourceLocation("solar", "textures/gui/rituallayout/summerheat.png"))
        ));

        guiPages.add(new GuiPage("Rite of Rising Moon",
                new PageTextComponent(I18n.format("guide.ritualrisingmoon.info")),
                new PageImageLargeComponent(new ResourceLocation("solar", "textures/gui/rituallayout/risingmoon.png"))
        ));

        guiPages.add(new GuiPage("Rite of Rising Sun",
                new PageTextComponent(I18n.format("guide.ritualrisingsun.info")),
                new PageImageLargeComponent(new ResourceLocation("solar", "textures/gui/rituallayout/risingsun.png"))
        ));

        guiPages.add(new GuiPage("Rite of Solar Protection",
                new PageTextComponent(I18n.format("guide.ritualsolarprotection.info")),
                new PageImageLargeComponent(new ResourceLocation("solar", "textures/gui/rituallayout/solarprotection.png"))
        ));


    }

    public static List<GuiPage> getGuiPages() {
        if(guiPages == null){
            init();
        }
        return guiPages;
    }

}
