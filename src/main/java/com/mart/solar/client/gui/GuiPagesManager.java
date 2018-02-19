package com.mart.solar.client.gui;

import com.mart.solar.client.gui.pages.GuiPage;
import com.mart.solar.client.gui.pages.component.PageImageComponent;
import com.mart.solar.client.gui.pages.component.PageTextComponent;
import jdk.internal.util.xml.impl.Input;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.resources.IResource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;
import org.apache.commons.lang3.text.WordUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class GuiPagesManager {

    public static List<GuiPage> guiPages;

    public GuiPagesManager(){

    }

    private static void init(){
        guiPages = new ArrayList<>();

        guiPages.add(new GuiPage("Broken Altar",
                new PageTextComponent(I18n.format("guide.brokenaltar.info")),
                new PageImageComponent(new ResourceLocation("solar", "textures/gui/crafting/dull_amulet.png"))));


        guiPages.add(new GuiPage("Ritual Amulet", new PageTextComponent(I18n.format("guide.brokenaltar.info"))));



        guiPages.add(new GuiPage("Celestial Altar", new PageTextComponent(I18n.format("guide.brokenaltar.info"))));
        guiPages.add(new GuiPage("Menhir", new PageTextComponent(I18n.format("guide.brokenaltar.info"))));
        guiPages.add(new GuiPage("Rune Infuser", new PageTextComponent(I18n.format("guide.brokenaltar.info"))));
        guiPages.add(new GuiPage("Silver", new PageTextComponent(I18n.format("guide.brokenaltar.info"))));
        guiPages.add(new GuiPage("Runes", new PageTextComponent(I18n.format("guide.brokenaltar.info"))));
    }

    public static List<GuiPage> getGuiPages() {
        if(guiPages == null){
            init();
        }
        return guiPages;
    }

}
