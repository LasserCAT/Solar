package com.mart.solar.client.gui;

import com.mart.solar.client.gui.button.BookButton;
import com.mart.solar.client.gui.pages.GuiPage;
import net.minecraft.client.gui.GuiButton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GuiGuide extends GuiBase {

    private List<BookButton> guiButtons;

    public GuiGuide(){

    }

    @Override
    public void initGui() {
        super.initGui();
        List<GuiPage> guiPages = GuiPagesManager.getGuiPages();
        this.guiButtons = new ArrayList<>();

        int xOffset = 5;
        int yOffset = 12;

        int x = (this.width - WIDTH) / 2;
        int y = (this.height - HEIGHT) / 2;

        int buttonAmount = 0;
        for(GuiPage guiPage : guiPages){
            BookButton button = new BookButton(guiPage, x + xOffset, y + (buttonAmount * yOffset + yOffset), buttonAmount);
            this.guiButtons.add(button);
            this.addButton(button);
            buttonAmount++;
        }
    }

    @Override
    public void updateScreen() {
        super.updateScreen();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);


    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        BookButton bButton = (BookButton) button;
        mc.displayGuiScreen(bButton.getPage());
    }

}
