package com.mart.solar.client.render;

import com.mart.solar.tileentities.TileRuneInfuser;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class RenderInfuser extends TileEntitySpecialRenderer<TileRuneInfuser> {

    public static Minecraft mc = Minecraft.getMinecraft();


    @Override
    public void renderTileEntityFast(TileRuneInfuser runeInfuser, double x, double y, double z, float partialTicks, int destroyStage, float partial, BufferBuilder buffer) {
        ItemStack inputStack = runeInfuser.getRune();
        ItemStack modifierStack = runeInfuser.getModifier();

        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y, z);
        this.renderRune(runeInfuser.getWorld(), inputStack, partialTicks);
        GlStateManager.popMatrix();

        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y, z);
        this.renderModifier(runeInfuser.getWorld(), modifierStack, partialTicks);
        GlStateManager.popMatrix();
    }

    private void renderRune(World world, ItemStack stack, float partialTicks) {
        RenderItem itemRenderer = mc.getRenderItem();
        if (stack != null) {
            GlStateManager.translate(0.5, 1, 0.5);
            EntityItem entityitem = new EntityItem(world, 0.0D, 0.0D, 0.0D, stack);
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

    private void renderModifier(World world, ItemStack stack, float partialTicks) {
        RenderItem itemRenderer = mc.getRenderItem();
        if (stack != null) {
            GlStateManager.translate(0.5, 2, 0.5);
            EntityItem entityitem = new EntityItem(world, 0.0D, 0.0D, 0.0D, stack);
            entityitem.getItem().setCount(1);
            entityitem.hoverStart = 0.0F;
            GlStateManager.pushMatrix();
            GlStateManager.disableLighting();

            float rotation = (float) (720.0 * (System.currentTimeMillis() & 0x3FFFL) / 0x3FFFL);

            GlStateManager.rotate(rotation, 0.0F, 1.0F, 0);
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
