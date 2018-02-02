package com.mart.solar.client.render;

import com.mart.solar.common.tileentities.TileAltar;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class RenderAltar extends TileEntitySpecialRenderer<TileAltar> {

    private static Minecraft mc = Minecraft.getMinecraft();

    @Override
    public void render(TileAltar tileEntity, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        ItemStack renderItem = tileEntity.getHeldItem();

        System.out.println(renderItem);

        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y, z);
        this.renderItem(tileEntity.getWorld(), renderItem, partialTicks);
        GlStateManager.popMatrix();



    }

    private void renderItem(World world, ItemStack stack, float partialTicks) {
        RenderItem itemRenderer = mc.getRenderItem();
        System.out.println("does hit this dough");
        if (!stack.isEmpty()) {
            System.out.println("renders");
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
}
