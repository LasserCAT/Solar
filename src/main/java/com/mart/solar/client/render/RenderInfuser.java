package com.mart.solar.client.render;

import com.mart.solar.Solar;
import com.mart.solar.common.tileentities.TileRuneInfuser;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

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

        if(!runeInfuser.isLinkedToAltar()){
            runeInfuser.getNearbyAltar();
            return;
        }

        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.disableLighting();
        GlStateManager.translate(0, 0.5, 0);

        int angle = (int) runeInfuser.getWorld().getWorldTime() % 360 ;

        int angle1 = angle;
        int angle2 = (angle + 90) % 360;
        int angle3 = (angle + 180) % 360;
        int angle4 = (angle + 270) % 360;

        Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(Solar.MODID, "textures/particles/infuser_particle.png"));

        drawRotatingParticle(x, y, z, angle1);
        drawRotatingParticle(x, y, z, angle2);
        drawRotatingParticle(x, y, z, angle3);
        drawRotatingParticle(x, y, z, angle4);

        GlStateManager.enableLighting();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();

    }

    private void drawRotatingParticle(double x, double y, double z, int angle){
        GlStateManager.pushMatrix();
        double drawX = x + Math.cos(Math.toRadians(angle));
        double drawZ = z + Math.sin(Math.toRadians(angle));

        GlStateManager.translate(drawX + 0.5, y, drawZ + 0.5);

        GlStateManager.rotate(-Minecraft.getMinecraft().getRenderManager().playerViewY, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);

        GlStateManager.color(255, 255, 0);

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufferbuilder.pos(-0.5D / 4, 0.0D, 0.0D).tex(0D, 0.0D).endVertex();
        bufferbuilder.pos(0.5D / 4, 0.0D, 0.0D).tex(1D, 0.0D).endVertex();
        bufferbuilder.pos(0.5D / 4, 1.0D / 4, 0.0D).tex(1D, 1D).endVertex();
        bufferbuilder.pos(-0.5D / 4, 1.0D / 4, 0.0D).tex(0.0D, 1D).endVertex();
        tessellator.draw();
        GlStateManager.popMatrix();
    }


}
