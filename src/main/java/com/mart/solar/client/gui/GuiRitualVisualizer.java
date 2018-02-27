package com.mart.solar.client.gui;

import com.mart.solar.api.ritual.Ritual;
import com.mart.solar.api.ritual.RitualComponent;
import com.mart.solar.client.gui.button.GuiCategoryButton;
import com.mart.solar.common.registry.ModBlocks;
import com.mart.solar.common.registry.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

import java.io.IOException;

public class GuiRitualVisualizer extends GuiBase {

    public static int WIDTH = 380;
    public static int HEIGHT = 340;

    private static ResourceLocation old_book_background = new ResourceLocation("solar", "textures/gui/book/ritual_layout.png");
    private static ResourceLocation blankRune = new ResourceLocation("solar", "textures/gui/book/blank_rune.png");
    private static ResourceLocation ritualBackground = new ResourceLocation("solar", "textures/gui/book/ritual_background.png");


    private Ritual ritual;

    public GuiRitualVisualizer(Ritual ritual){
        this.ritual = ritual;
    }

    @Override
    public void initGui() {
        int buttonAmount = 0;

        int x = (this.width - WIDTH) / 2;
        int y = (this.height - HEIGHT) / 2;
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

        GlStateManager.color(255, 255, 255);

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufferbuilder.pos(0, HEIGHT, 0.0D).tex(0, 1).endVertex();
        bufferbuilder.pos(WIDTH, HEIGHT, 0.0D).tex(1, 1).endVertex();
        bufferbuilder.pos(WIDTH,0, 0.0D).tex(1, 0).endVertex();
        bufferbuilder.pos(0, 0, 0.0D).tex(0, 0).endVertex();
        tessellator.draw();

        GlStateManager.translate(22, 2, 0);
        Minecraft.getMinecraft().renderEngine.bindTexture(ritualBackground);
        BufferBuilder ritualBrackgroundBuffer = tessellator.getBuffer();
        ritualBrackgroundBuffer.begin(7, DefaultVertexFormats.POSITION_TEX);
        ritualBrackgroundBuffer.pos(0, 336, 0.0D).tex(0, 1).endVertex();
        ritualBrackgroundBuffer.pos(336, 336, 0.0D).tex(1, 1).endVertex();
        ritualBrackgroundBuffer.pos(336,0, 0.0D).tex(1, 0).endVertex();
        ritualBrackgroundBuffer.pos(0, 0, 0.0D).tex(0, 0).endVertex();
        tessellator.draw();

        GlStateManager.translate(10 * 16, 10 * 16, 0);

        RenderHelper.enableGUIStandardItemLighting();
        Minecraft.getMinecraft().getRenderItem().renderItemIntoGUI(new ItemStack(Item.getItemFromBlock(ModBlocks.blockAltar)), 0, 0);

        for(RitualComponent component : ritual.getRitualComponents()){
            BlockPos pos = component.getComponentPos();

            if(component.getRuneType() == null){
                GlStateManager.pushMatrix();
                GlStateManager.translate(16 * pos.getX(), 16 * pos.getZ(), 0);
                Minecraft.getMinecraft().renderEngine.bindTexture(blankRune);
                Tessellator runeTess = Tessellator.getInstance();
                BufferBuilder bruneBuffer = runeTess.getBuffer();
                bruneBuffer.begin(7, DefaultVertexFormats.POSITION_TEX);
                bruneBuffer.pos(0,     16,    0.0D).tex(0, 1).endVertex();
                bruneBuffer.pos(16,    16,    0.0D).tex(1, 1).endVertex();
                bruneBuffer.pos(16,    0,     0.0D).tex(1, 0).endVertex();
                bruneBuffer.pos(0,     0,     0.0D).tex(0, 0).endVertex();
                tessellator.draw();
                GlStateManager.popMatrix();
                continue;
            }

            int runeByte = component.getRuneType().ordinal();


            Minecraft.getMinecraft().getRenderItem().renderItemIntoGUI(new ItemStack(ModItems.RUNES, 1, runeByte), 16 * pos.getX(), 16 * pos.getZ());

        }

        RenderHelper.disableStandardItemLighting();

        GlStateManager.enableLighting();
        GlStateManager.disableAlpha();
        GlStateManager.disableBlend();

        GlStateManager.popMatrix();
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        if(mouseButton == 1){
            GuiBook playerGui = GuiPagesManager.getPlayerGUI(Minecraft.getMinecraft().player);
            Minecraft.getMinecraft().displayGuiScreen(playerGui.getPreviousGui());
        }
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}