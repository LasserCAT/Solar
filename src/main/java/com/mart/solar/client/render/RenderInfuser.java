package com.mart.solar.client.render;

import com.mart.solar.common.tileentities.TileRuneInfuser;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class RenderInfuser extends TileEntitySpecialRenderer<TileRuneInfuser> {

    private static Minecraft mc = Minecraft.getMinecraft();

    @Override
    public void render(TileRuneInfuser runeInfuser, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        ItemStack inputStack = runeInfuser.getRune();
        ItemStack modifierStack = runeInfuser.getModifier();

        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y, z);
        RenderUtil.renderLayingOnBlock(inputStack, runeInfuser);
        GlStateManager.popMatrix();

        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y, z);
        RenderUtil.renderItemFloatingOnTileEntity(modifierStack, runeInfuser);
        GlStateManager.popMatrix();
    }


}
