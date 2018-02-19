package com.mart.solar.client.gui;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

public class GuiBase extends GuiScreen{

    public static int WIDTH = 400;
    public static int HEIGHT = 256;

    private ResourceLocation old_book_background = new ResourceLocation("solar", "textures/gui/book.jpg");


    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();

        int x = (this.width - WIDTH) / 2;
        int y = (this.height - HEIGHT) / 2;

        mc.renderEngine.bindTexture(old_book_background);

        drawScaledCustomSizeModalRect(x, y, 0, 0, 500, 363, WIDTH, HEIGHT, 500, 363);

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}
