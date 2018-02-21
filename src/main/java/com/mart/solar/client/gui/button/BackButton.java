package com.mart.solar.client.gui.button;

import com.mart.solar.client.gui.pages.GuiPage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.config.GuiButtonExt;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class BackButton extends GuiButtonExt {

    public BackButton(int x0, int y0, int buttonID){
        this(buttonID, x0, y0, "");
        this.width = 16;
        this.height = 16;
    }

    public BackButton(int buttonId, int x, int y, String buttonText) {
        super(buttonId, x, y, buttonText);
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
        this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
        this.mouseDragged(mc, mouseX, mouseY);
        GL11.glPushMatrix();

        GlStateManager.enableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.disableLighting();

        if(this.hovered){
            Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("solar", "textures/gui/hover_back_arrow.png"));
        }
        else{
            Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("solar", "textures/gui/back_arrow.png"));
        }


        GL11.glTranslatef(this.x, this.y, 0);

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufferbuilder.pos(0, 16, 0.0D).tex(0, 1).endVertex();
        bufferbuilder.pos(16, 16, 0.0D).tex(1, 1).endVertex();
        bufferbuilder.pos(16,0, 0.0D).tex(1, 0).endVertex();
        bufferbuilder.pos(0, 0, 0.0D).tex(0, 0).endVertex();
        tessellator.draw();

        GlStateManager.enableLighting();
        GlStateManager.disableAlpha();
        GlStateManager.disableBlend();

        GL11.glPopMatrix();

    }

}
