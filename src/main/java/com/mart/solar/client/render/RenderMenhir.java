package com.mart.solar.client.render;

import com.mart.solar.common.tileentities.TileMenhir;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;

public class RenderMenhir extends TileEntitySpecialRenderer<TileMenhir> {

    @Override
    public void render(TileMenhir tileEntity, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        ItemStack inputStack = tileEntity.getRune();

        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y, z);
        RenderUtil.renderLayingOnBlock(inputStack, tileEntity);
        GlStateManager.popMatrix();
    }
}
