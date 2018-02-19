package com.mart.solar.client.gui.pages.component;

import net.minecraft.client.gui.FontRenderer;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class PageTextComponent implements PageComponent{

    private final String[] text;

    public PageTextComponent(String text){
        this.text = text.split("<br>");
    }

    @Override
    public void draw(int x, int y, FontRenderer fontRenderer) {
        GL11.glScaled(0.7, 0.7, 0.7);

        for(String text : this.text){
            fontRenderer.drawString(text, 0, 0, Color.BLACK.getRGB());
            GL11.glTranslatef(0, 10, 0);
        }

        GL11.glScaled(1, 1, 1);
    }
}
