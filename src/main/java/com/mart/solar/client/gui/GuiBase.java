package com.mart.solar.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiBase extends GuiScreen{

    public static int WIDTH = 380;
    public static int HEIGHT = 340;

    private ResourceLocation old_book_background = new ResourceLocation("solar", "textures/gui/book.jpg");


    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();

        int height = 340;


        int x = (this.width - WIDTH) / 2;
        int y = (this.height - height) / 2;

        GlStateManager.pushMatrix();

        GlStateManager.translate(x, y, 0);

        GlStateManager.enableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.disableLighting();

        Minecraft.getMinecraft().renderEngine.bindTexture(old_book_background);

        GlStateManager.color(255, 255, 255, 1);

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufferbuilder.pos(0, height, 0.0D).tex(0, 1).endVertex();
        bufferbuilder.pos(WIDTH, height, 0.0D).tex(1, 1).endVertex();
        bufferbuilder.pos(WIDTH,0, 0.0D).tex(1, 0).endVertex();
        bufferbuilder.pos(0, 0, 0.0D).tex(0, 0).endVertex();
        tessellator.draw();

        GlStateManager.enableLighting();
        GlStateManager.disableAlpha();
        GlStateManager.disableBlend();

        GlStateManager.popMatrix();

//
//        mc.renderEngine.bindTexture(old_book_background);
//
//        drawScaledCustomSizeModalRect(x, y, 0, 0, 470, 363, WIDTH, HEIGHT, 470, 363);

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}
