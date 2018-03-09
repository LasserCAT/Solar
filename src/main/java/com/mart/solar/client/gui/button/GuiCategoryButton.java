package com.mart.solar.client.gui.button;

import com.mart.solar.client.gui.GuiBook;
import com.mart.solar.client.gui.GuiCategory;
import com.mart.solar.client.gui.GuiPagesManager;
import com.mart.solar.client.gui.pages.GuiPage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.config.GuiButtonExt;
import net.minecraftforge.fml.client.config.GuiUtils;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GuiCategoryButton extends GuiButtonExt{

    private Item displayItem;
    private GuiCategory category;

    public GuiCategoryButton(GuiCategory category, int id, int xPos, int yPos, String displayString){
        this(id, xPos, yPos, displayString);
        this.category = category;
        this.displayItem = category.getDisplayItem();
        this.width = 34;
        this.height = 37;
    }

    public GuiCategoryButton(int id, int xPos, int yPos, String displayString) {
        super(id, xPos, yPos, displayString);
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY, float partial) {
        this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;

        GL11.glPushMatrix();

        GlStateManager.enableBlend();
        GlStateManager.disableLighting();
        GlStateManager.color(255, 255, 255);

        Minecraft.getMinecraft().renderEngine.bindTexture(
                new ResourceLocation("solar", "textures/gui/book/category_piece.png"));

        GlStateManager.translate(x, y, 0);

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufferbuilder.pos(0, 38, 0.0D).tex(0, 1).endVertex();
        bufferbuilder.pos(34, 38, 0.0D).tex(1, 1).endVertex();
        bufferbuilder.pos(34,0, 0.0D).tex(1, 0).endVertex();
        bufferbuilder.pos(0, 0, 0.0D).tex(0, 0).endVertex();
        tessellator.draw();

        int xLoc = (34 - 16) / 2;
        int yLoc = (38 - 16) / 2;
        GlStateManager.pushMatrix();
        GL11.glTranslatef(xLoc, yLoc, 0);

        GlStateManager.disableLighting();
        RenderHelper.enableStandardItemLighting();
        Minecraft.getMinecraft().getRenderItem().renderItemIntoGUI(new ItemStack(this.displayItem), 0, 0);
        RenderHelper.disableStandardItemLighting();

        GlStateManager.enableLighting();
        GlStateManager.popMatrix();

        GlStateManager.enableLighting();
        GlStateManager.disableBlend();

        GL11.glPopMatrix();

        if(this.hovered){
            ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());
            List<String> text = new ArrayList<>();
            text.add(category.getCategoryName());
            GuiUtils.drawHoveringText(text, mouseX, mouseY, scaledResolution.getScaledWidth(), scaledResolution.getScaledHeight(), 200, Minecraft.getMinecraft().fontRenderer);
        }
    }

    public void openCategory(){
        GuiBook guiBook = GuiPagesManager.getPlayerGUI(Minecraft.getMinecraft().player);
        guiBook.clearList();
        guiBook.setCurrentGui(this.category);
        Minecraft.getMinecraft().displayGuiScreen(this.category);
    }
}
