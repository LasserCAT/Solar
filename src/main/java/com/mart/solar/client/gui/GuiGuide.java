package com.mart.solar.client.gui;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class GuiGuide extends GuiScreen {

    public GuiGuide(){

    }

    @Override
    public void initGui() {
        super.initGui();
    }

    @Override
    public void updateScreen() {
        super.updateScreen();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        GlStateManager.color(1F, 1F, 1F, 1F);
        mc.renderEngine.bindTexture(new ResourceLocation("solar", "textures/items/firerune.png"));
        drawTexturedModalRect(100, 100, 0, 0, 100, 100);
    }
}
