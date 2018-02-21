package com.mart.solar.client.gui.pages.component;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import org.apache.commons.lang3.text.WordUtils;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class PageTextComponent extends PageComponent{

    private final String[] text;

    public PageTextComponent(String text){
        this.text = WordUtils.wrap(text, 35).split("\r\n");
    }

    @Override
    public void draw(int x, int y, FontRenderer fontRenderer) {
        GL11.glTranslatef(0, 5, 0);

        GlStateManager.enableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.disableLighting();

        GlStateManager.color(255, 255, 255);

        for(String text : this.text){
            fontRenderer.drawString(text, 0, 0, Color.BLACK.getRGB());
            GL11.glTranslatef(0, 10, 0);
        }

        GlStateManager.enableLighting();
        GlStateManager.disableAlpha();
        GlStateManager.disableBlend();
    }
}
