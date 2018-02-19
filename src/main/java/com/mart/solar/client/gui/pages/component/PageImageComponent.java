package com.mart.solar.client.gui.pages.component;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class PageImageComponent implements PageComponent {

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

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufferbuilder.pos(0, 106, 0.0D).tex(0, 106).endVertex();
        bufferbuilder.pos(106, 106, 0.0D).tex(106, 106).endVertex();
        bufferbuilder.pos(106,0, 0.0D).tex(106, 0).endVertex();
        bufferbuilder.pos(0, 0, 0.0D).tex(0, 0).endVertex();
        tessellator.draw();

        GlStateManager.enableLighting();
        GlStateManager.disableAlpha();
        GlStateManager.disableBlend();
    }
}
