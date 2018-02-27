package com.mart.solar.client.gui;

import com.mart.solar.client.gui.button.GuiCategoryButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.io.IOException;

public class GuiBase extends GuiScreen{

    public static int WIDTH = 380;
    public static int HEIGHT = 340;

    private ResourceLocation old_book_background = new ResourceLocation("solar", "textures/gui/book/bookfull.png");

    @Override
    public void initGui() {
        super.initGui();
        int buttonAmount = 0;

        int x = (this.width - WIDTH) / 2;
        int y = (this.height - HEIGHT) / 2;

        for(GuiCategory category : GuiPagesManager.getCategories()){
            this.addButton(new GuiCategoryButton(category, buttonAmount, x-40, y + (buttonAmount * 40), ""));
            buttonAmount++;
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();

        int x = (this.width - WIDTH) / 2;
        int y = (this.height - HEIGHT) / 2;

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
        bufferbuilder.pos(0, HEIGHT, 0.0D).tex(0, 1).endVertex();
        bufferbuilder.pos(WIDTH, HEIGHT, 0.0D).tex(1, 1).endVertex();
        bufferbuilder.pos(WIDTH,0, 0.0D).tex(1, 0).endVertex();
        bufferbuilder.pos(0, 0, 0.0D).tex(0, 0).endVertex();
        tessellator.draw();

        GlStateManager.enableLighting();
        GlStateManager.disableAlpha();
        GlStateManager.disableBlend();

        GlStateManager.popMatrix();

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if(button instanceof GuiCategoryButton){
            GuiCategoryButton categoryButton = (GuiCategoryButton) button;
            categoryButton.openCategory();
        }
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}
