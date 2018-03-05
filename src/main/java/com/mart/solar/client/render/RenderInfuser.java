package com.mart.solar.client.render;

import com.mart.solar.common.tileentities.TileRuneInfuser;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;

public class RenderInfuser extends TileEntitySpecialRenderer<TileRuneInfuser> {

    private static Minecraft mc = Minecraft.getMinecraft();

    @Override
    public void render(TileRuneInfuser runeInfuser, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        ItemStack inputStack = runeInfuser.getRune();
        ItemStack modifierStack = runeInfuser.getReagent();

        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y, z);
        RenderUtil.renderLayingOnBlock(inputStack);
        GlStateManager.popMatrix();

        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y, z);
        RenderUtil.renderItemFloatingOnTileEntity(modifierStack);
        GlStateManager.popMatrix();
    }


}
