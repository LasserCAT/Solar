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

public class PreviousButton extends GuiButtonExt {

    private GuiPage guiPage;

    public PreviousButton(int x0, int y0, int buttonID, GuiPage guiPage){
        this(buttonID, x0, y0, "");
        this.width = 16;
        this.height = 16;
        this.guiPage = guiPage;
    }

    public PreviousButton(int id, int xPos, int yPos, String displayString) {
        super(id, xPos, yPos, displayString);
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
        if(this.guiPage.getCurrentPage() <= 1){
            return;
        }
        this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
        this.mouseDragged(mc, mouseX, mouseY);
        GL11.glPushMatrix();

        GlStateManager.enableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.disableLighting();

        if(this.hovered){
            Minecraft.getMinecraft().renderEngine.bindTexture(
                    new ResourceLocation("solar", "textures/gui/hover_previous_arrow.png"));
        }
        else{
            Minecraft.getMinecraft().renderEngine.bindTexture(
                    new ResourceLocation("solar", "textures/gui/previous_arrow.png"));
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
