package com.mart.solar.client.render;

import com.mart.solar.common.tileentities.TileAltar;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;

public class RenderAltar extends TileEntitySpecialRenderer<TileAltar> {

    @Override
    public void render(TileAltar tileEntity, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        if(!tileEntity.getWorld().isRemote){
            return;
        }

        ItemStack renderItem = tileEntity.getHeldItem();

        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y, z);

        RenderUtil.renderItemFloatingOnTileEntity(renderItem, tileEntity);

        GlStateManager.popMatrix();
    }


}
