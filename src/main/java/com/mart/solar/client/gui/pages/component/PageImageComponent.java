package com.mart.solar.client.gui.pages.component;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class PageImageComponent extends PageComponent {

    private ResourceLocation resourceLocation;

    public PageImageComponent(ResourceLocation location){
        this.resourceLocation = location;
    }

    @Override
    public void draw(int xBegin, int yBegin, FontRenderer fontRenderer) {
        GL11.glTranslatef(0, 5, 0);

        GlStateManager.enableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.disableLighting();

        Minecraft.getMinecraft().renderEngine.bindTexture(resourceLocation);

        GlStateManager.color(255, 255, 255, 1);

        GL11.glScalef(0.5f, 0.5f, 0.5f);

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufferbuilder.pos(0, 106, 0.0D).tex(0, 1).endVertex();
        bufferbuilder.pos(106, 106, 0.0D).tex(1, 1).endVertex();
        bufferbuilder.pos(106,0, 0.0D).tex(1, 0).endVertex();
        bufferbuilder.pos(0, 0, 0.0D).tex(0, 0).endVertex();
        tessellator.draw();

        GL11.glScalef(2f, 2f, 2f);

        GL11.glTranslatef(0, 53, 0);

        GlStateManager.enableLighting();
        GlStateManager.disableAlpha();
        GlStateManager.disableBlend();
    }
}
