package com.mart.solar.client.render.entityrender;

import com.mart.solar.common.entity.EntitySpellContainer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nullable;

public class RenderSpell<T extends Entity> extends Render<T> {

    public static final Factory FACTORY = new Factory();

    protected RenderSpell(RenderManager renderManager) {
        super(renderManager);
    }

    @Override
    public void doRender(T entity, double x, double y, double z, float entityYaw, float partialTicks) {
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.disableLighting();
        GlStateManager.translate((float)x, (float)y, (float)z);
        GlStateManager.enableRescaleNormal();
        GlStateManager.rotate(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);

//        EntitySpellContainer spellContainer = (EntitySpellContainer) entity;
//todo: make spell dependent
//        if(spellContainer.isDay()){
//            GlStateManager.color(1.0f, 1.0f, 1.0f, 0.2f);
//        }

        this.bindTexture(getEntityTexture(entity));
        Tessellator tessellatorSpell = Tessellator.getInstance();
        BufferBuilder bufferbuilderSpell = tessellatorSpell.getBuffer();
        GlStateManager.enableRescaleNormal();

        bufferbuilderSpell.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufferbuilderSpell.pos(-0.5D, 0.0D, 0.0D).tex(0D, 0.0D).endVertex();
        bufferbuilderSpell.pos(0.5D, 0.0D, 0.0D).tex(1D, 0.0D).endVertex();
        bufferbuilderSpell.pos(0.5D, 1.0D, 0.0D).tex(1D, 1D).endVertex();
        bufferbuilderSpell.pos(-0.5D, 1.0D, 0.0D).tex(0.0D, 1D).endVertex();
        tessellatorSpell.draw();

        GlStateManager.disableRescaleNormal();
        GlStateManager.enableLighting();
        GlStateManager.disableAlpha();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();

        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.disableLighting();
        GlStateManager.translate((float)x, (float)y + 0.01, (float)z);
        GlStateManager.enableRescaleNormal();
        GlStateManager.rotate(-90, 1.0F, 0.0F, 0.0F);

        this.bindTexture(new ResourceLocation("solar:textures/spell-border.png"));
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        GlStateManager.enableRescaleNormal();

        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufferbuilder.pos(-8D, -8.0D, 0.0D).tex(0D, 0.0D).endVertex();
        bufferbuilder.pos(8D, -8.0D, 0.0D).tex(1D, 0.0D).endVertex();
        bufferbuilder.pos(8D, 8D, 0.0D).tex(1D, 1D).endVertex();
        bufferbuilder.pos(-8D, 8D, 0.0D).tex(0.0D, 1D).endVertex();
        tessellator.draw();

        GlStateManager.disableRescaleNormal();
        GlStateManager.enableLighting();
        GlStateManager.disableAlpha();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(T entity) {
        return new ResourceLocation("solar:textures/light.png");
    }

    public static class Factory implements IRenderFactory<EntitySpellContainer>
    {
        @Override
        public Render<? super EntitySpellContainer> createRenderFor(RenderManager manager)
        {
            return new RenderSpell(manager);
        }
    }
}
