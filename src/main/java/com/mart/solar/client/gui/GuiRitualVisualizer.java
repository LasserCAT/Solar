package com.mart.solar.client.gui;

import com.mart.solar.api.ritual.Ritual;
import com.mart.solar.api.ritual.RitualComponent;
import com.mart.solar.common.registry.ModBlocks;
import com.mart.solar.common.registry.ModItems;
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
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.client.config.GuiUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

        List<String> tooltipList = new ArrayList<>();

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
        Minecraft.getMinecraft().getRenderItem().renderItemIntoGUI(new ItemStack(Item.getItemFromBlock(ModBlocks.ALTAR)), 0, 0);


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

                int currentPositionX = x + 22 + (10 * 16) + (16 * pos.getX());
                int currentPositionY = y + 2 + (10 * 16) + (16 * pos.getZ());

                boolean hovered = mouseX >= currentPositionX && mouseY >= currentPositionY && mouseX < currentPositionX + 16 && mouseY < currentPositionY + 16;
                if(hovered){
                    tooltipList.add("Empty Menhir");
                }

                continue;
            }

            int runeByte = component.getRuneType().ordinal();


            Minecraft.getMinecraft().getRenderItem().renderItemIntoGUI(new ItemStack(ModItems.RUNES, 1, runeByte), 16 * pos.getX(), 16 * pos.getZ());

            int currentPositionX = x + 22 + (10 * 16) + (16 * pos.getX());
            int currentPositionY = y + 2 + (10 * 16) + (16 * pos.getZ());

            boolean hovered = mouseX >= currentPositionX && mouseY >= currentPositionY && mouseX < currentPositionX + 16 && mouseY < currentPositionY + 16;
            if(hovered){
                tooltipList.add(new ItemStack(ModItems.RUNES, 1, runeByte).getDisplayName());
            }

        }

        RenderHelper.disableStandardItemLighting();

        GlStateManager.enableLighting();
        GlStateManager.disableAlpha();
        GlStateManager.disableBlend();

        GlStateManager.popMatrix();

        if(tooltipList.size() == 1){
            ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());
            GuiUtils.drawHoveringText(tooltipList, mouseX, mouseY, scaledResolution.getScaledWidth(), scaledResolution.getScaledHeight(), 200, Minecraft.getMinecraft().fontRenderer);

        }
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        if(mouseButton == 1){
            GuiBook playerGui = GuiPagesManager.getPlayerGUI(Minecraft.getMinecraft().player);
            Minecraft.getMinecraft().displayGuiScreen(playerGui.getPreviousGui());
        }
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        if (keyCode == 1)
        {
            GuiBook playerGui = GuiPagesManager.getPlayerGUI(Minecraft.getMinecraft().player);
            Minecraft.getMinecraft().displayGuiScreen(playerGui.getPreviousGui());
        }
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}