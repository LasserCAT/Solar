package com.mart.solar.client.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class RenderUtil{

    public static void renderItemFloatingOnTileEntity(ItemStack renderItem){
        RenderItem itemRenderer = Minecraft.getMinecraft().getRenderItem();
        if (!renderItem.isEmpty()) {
            GlStateManager.translate(0.5, 2, 0.5);
            GlStateManager.pushMatrix();
            GlStateManager.disableLighting();

            float rotation = (float) (720.0 * (System.currentTimeMillis() & 0x3FFFL) / 0x3FFFL);

            GlStateManager.rotate(rotation, 0.0F, 1.0F, 0);
            GlStateManager.scale(0.5F, 0.5F, 0.5F);
            RenderHelper.enableStandardItemLighting();
            itemRenderer.renderItem(renderItem, ItemCameraTransforms.TransformType.FIXED);
            RenderHelper.disableStandardItemLighting();

            GlStateManager.enableLighting();
            GlStateManager.popMatrix();
        }
    }

    public static void renderLayingOnBlock(ItemStack renderItem) {
        RenderItem itemRenderer = Minecraft.getMinecraft().getRenderItem();
        if (!renderItem.isEmpty()) {
            GlStateManager.translate(0.5, 1, 0.5);
            GlStateManager.pushMatrix();
            GlStateManager.disableLighting();

            GlStateManager.rotate(180, 0.0F, 1.0F, 1);
            GlStateManager.scale(0.5F, 0.5F, 0.5F);
            RenderHelper.enableStandardItemLighting();
            itemRenderer.renderItem(renderItem, ItemCameraTransforms.TransformType.FIXED);
            RenderHelper.disableStandardItemLighting();

            GlStateManager.enableLighting();
            GlStateManager.popMatrix();
        }
    }

    public static void renderLayingOnBlockWithCoords(ItemStack renderItem, double x, double y, double z) {
        RenderItem itemRenderer = Minecraft.getMinecraft().getRenderItem();

        GlStateManager.translate(x, y, z);
        GlStateManager.pushMatrix();
        GlStateManager.disableLighting();

        GlStateManager.rotate(180, 0.0F, 1.0F, 1);
        GlStateManager.scale(0.5F, 0.5F, 0.5F);
        RenderHelper.enableStandardItemLighting();
        itemRenderer.renderItem(renderItem, ItemCameraTransforms.TransformType.FIXED);
        RenderHelper.disableStandardItemLighting();

        GlStateManager.enableLighting();
        GlStateManager.popMatrix();
    }

    public static void renderRisingFromTopOfBlock(ItemStack renderItem, float height, float rotation) {
        RenderItem itemRenderer = Minecraft.getMinecraft().getRenderItem();
        if (!renderItem.isEmpty()) {
            GlStateManager.translate(0.5, 1 + height, 0.5);
            GlStateManager.pushMatrix();
            GlStateManager.disableLighting();

            GlStateManager.rotate(rotation, 0F, 1.0F, 1f);
            GlStateManager.scale(0.5F, 0.5F, 0.5F);
            RenderHelper.enableStandardItemLighting();
            itemRenderer.renderItem(renderItem, ItemCameraTransforms.TransformType.FIXED);
            RenderHelper.disableStandardItemLighting();

            GlStateManager.enableLighting();
            GlStateManager.popMatrix();
        }
    }

}
