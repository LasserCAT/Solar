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

public class NextButton extends GuiButtonExt{

    private GuiPage guiPage;

    public NextButton(int x0, int y0, int buttonID, GuiPage guiPage){
        this(buttonID, x0, y0, "");
        this.width = 16;
        this.height = 16;
        this.guiPage = guiPage;
    }

    public NextButton(int id, int xPos, int yPos, String displayString) {
        super(id, xPos, yPos, displayString);
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
        if(this.guiPage.getCurrentPage() + 1 >= this.guiPage.getPagesCount()){
            return;
        }

        this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
        this.mouseDragged(mc, mouseX, mouseY);
        GL11.glPushMatrix();

        GlStateManager.enableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.disableLighting();



        GL11.glTranslatef(this.x, this.y, 0);

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        if(this.hovered){
            Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("solar", "textures/gui/hover_next_arrow.png"));
            bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
            bufferbuilder.pos(0, 10, 0.0D).tex(0, 1).endVertex();
            bufferbuilder.pos(18, 10, 0.0D).tex(1, 1).endVertex();
            bufferbuilder.pos(18,0, 0.0D).tex(1, 0).endVertex();
            bufferbuilder.pos(0, 0, 0.0D).tex(0, 0).endVertex();
        }
        else{
            Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("solar", "textures/gui/next_arrow.png"));
            bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
            bufferbuilder.pos(0, 10, 0.0D).tex(0, 1).endVertex();
            bufferbuilder.pos(18, 10, 0.0D).tex(1, 1).endVertex();
            bufferbuilder.pos(18,0, 0.0D).tex(1, 0).endVertex();
            bufferbuilder.pos(0, 0, 0.0D).tex(0, 0).endVertex();
        }
        tessellator.draw();

        GlStateManager.enableLighting();
        GlStateManager.disableAlpha();
        GlStateManager.disableBlend();

        GL11.glPopMatrix();

    }
}
