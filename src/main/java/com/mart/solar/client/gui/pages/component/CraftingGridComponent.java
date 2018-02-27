package com.mart.solar.client.gui.pages.component;

import javafx.collections.transformation.SortedList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.util.Arrays;
import java.util.LinkedList;

public class CraftingGridComponent extends PageComponent {

    private LinkedList<Item> itemList = new LinkedList<>();
    private Item craftedItem = Items.AIR;

    public CraftingGridComponent(Item... item){
        itemList.addAll(Arrays.asList(item));
    }

    @Override
    public void draw(int x, int y, FontRenderer fontRenderer) {
        GL11.glTranslatef(0, 5, 0);
        GlStateManager.enableBlend();
        GlStateManager.disableLighting();
        GlStateManager.color(255, 255, 255);

        //Draw Output Item
        GlStateManager.pushMatrix();
        GlStateManager.translate((170 - 32) / 2, 0, 0);
        GlStateManager.scale(2, 2, 0);
        RenderHelper.enableStandardItemLighting();
        Minecraft.getMinecraft().getRenderItem().renderItemIntoGUI(new ItemStack(this.craftedItem), 0, 0);
        RenderHelper.disableStandardItemLighting();
        GlStateManager.popMatrix();

        //Draw crafting grid
        GlStateManager.translate(0, 50, 0);
        Minecraft.getMinecraft().renderEngine.bindTexture(
                new ResourceLocation("solar", "textures/gui/book/crafting_grid.png"));

        GL11.glPushMatrix();
        GL11.glTranslatef((170 - 128) / 2, 0, 0);

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufferbuilder.pos(0, 128, 0.0D).tex(0, 1).endVertex();
        bufferbuilder.pos(128, 128, 0.0D).tex(1, 1).endVertex();
        bufferbuilder.pos(128,0, 0.0D).tex(1, 0).endVertex();
        bufferbuilder.pos(0, 0, 0.0D).tex(0, 0).endVertex();
        tessellator.draw();


        //Draw items in crafting grid
        GlStateManager.translate(8, 8, 0);

        int itemAmount = 0;
        for(Item item : this.itemList){
            int xLoc = itemAmount % 3;
            int yLoc = (int) Math.floor(itemAmount / 3);
            GlStateManager.pushMatrix();
            GL11.glTranslatef(xLoc * 40, yLoc * 40, 0);

            GlStateManager.disableLighting();
            RenderHelper.enableStandardItemLighting();
            GL11.glScalef(2, 2, 0);
            Minecraft.getMinecraft().getRenderItem().renderItemIntoGUI(new ItemStack(item), 0, 0);
            RenderHelper.disableStandardItemLighting();

            GlStateManager.enableLighting();
            GlStateManager.popMatrix();
            itemAmount++;
        }

        GlStateManager.enableLighting();
        GlStateManager.disableBlend();

        GlStateManager.popMatrix();
        GlStateManager.translate(0, 180, 0);
    }

    public CraftingGridComponent setCraftedItem(Item craftedItem) {
        this.craftedItem = craftedItem;
        return this;
    }
}
