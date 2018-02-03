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

    public static void renderItemFloatingOnTileEntity(ItemStack renderItem, TileEntity tileEntity){
        RenderItem itemRenderer = Minecraft.getMinecraft().getRenderItem();
        if (!renderItem.isEmpty()) {
            GlStateManager.translate(0.5, 2, 0.5);
            EntityItem entityitem = new EntityItem(tileEntity.getWorld(), 0.0D, 0.0D, 0.0D, renderItem);
            entityitem.getItem().setCount(1);
            entityitem.hoverStart = 0.0F;
            GlStateManager.pushMatrix();
            GlStateManager.disableLighting();

            float rotation = (float) (720.0 * (System.currentTimeMillis() & 0x3FFFL) / 0x3FFFL);

            GlStateManager.rotate(rotation, 0.0F, 1.0F, 0);
            GlStateManager.scale(0.5F, 0.5F, 0.5F);
            RenderHelper.enableStandardItemLighting();
            itemRenderer.renderItem(entityitem.getItem(), ItemCameraTransforms.TransformType.FIXED);
            RenderHelper.disableStandardItemLighting();

            GlStateManager.enableLighting();
            GlStateManager.popMatrix();
        }
    }

    public static void renderLayingOnBlock(ItemStack renderItem, TileEntity tileEntity) {
        RenderItem itemRenderer = Minecraft.getMinecraft().getRenderItem();
        if (!renderItem.isEmpty()) {
            GlStateManager.translate(0.5, 1, 0.5);
            EntityItem entityitem = new EntityItem(tileEntity.getWorld(), 0.0D, 0.0D, 0.0D, renderItem);
            entityitem.getItem().setCount(1);
            entityitem.hoverStart = 0.0F;
            GlStateManager.pushMatrix();
            GlStateManager.disableLighting();

            GlStateManager.rotate(180, 0.0F, 1.0F, 1);
            GlStateManager.scale(0.5F, 0.5F, 0.5F);
            GlStateManager.pushAttrib();
            RenderHelper.enableStandardItemLighting();
            itemRenderer.renderItem(entityitem.getItem(), ItemCameraTransforms.TransformType.FIXED);
            RenderHelper.disableStandardItemLighting();
            GlStateManager.popAttrib();

            GlStateManager.enableLighting();
            GlStateManager.popMatrix();
        }
    }

    public static void renderRisingFromTopOfBlock(ItemStack renderItem, TileEntity tileEntity, float height, float rotation) {
        RenderItem itemRenderer = Minecraft.getMinecraft().getRenderItem();
        if (!renderItem.isEmpty()) {
            GlStateManager.translate(0.5, 1 + height, 0.5);
            EntityItem entityitem = new EntityItem(tileEntity.getWorld(), 0.0D, 0.0D, 0.0D, renderItem);
            entityitem.getItem().setCount(1);
            entityitem.hoverStart = 0.0F;
            GlStateManager.pushMatrix();
            GlStateManager.disableLighting();

            //GlStateManager.rotate(0, 0F, 1.0F, 0.5f); = Rechtop
            //GlStateManager.rotate(0, 0F, 1.0F, 1f); = Rechtop
            // GlStateManager.rotate(90, 0F, 1.0F, 1f); = https://gyazo.com/4d690ac37c9029ed9020240bdd14309e


            GlStateManager.rotate(rotation, 0F, 1.0F, 1f);
            GlStateManager.scale(0.5F, 0.5F, 0.5F);
            GlStateManager.pushAttrib();
            RenderHelper.enableStandardItemLighting();
            itemRenderer.renderItem(entityitem.getItem(), ItemCameraTransforms.TransformType.FIXED);
            RenderHelper.disableStandardItemLighting();
            GlStateManager.popAttrib();

            GlStateManager.enableLighting();
            GlStateManager.popMatrix();
        }
    }

}
