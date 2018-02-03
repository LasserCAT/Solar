package com.mart.solar.client.render;

import com.mart.solar.common.tileentities.TileAltar;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;

public class RenderAltar extends TileEntitySpecialRenderer<TileAltar> {

    @Override
    public void render(TileAltar tileEntity, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {

        ItemStack renderItem = tileEntity.getHeldItem();

        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y, z);

        if(tileEntity.isRecipeInProgress()){
            float heightModifier = 0;
            float rotateModifier = 0;

            if(tileEntity.getCurrentEnergyProgress() != 0){
                int onePercent = tileEntity.getEnergyCost() / 100;
                onePercent = (onePercent == 0) ? 1 : onePercent;
                int currentPercent = (int) Math.floor(tileEntity.getCurrentEnergyProgress() / onePercent);
                heightModifier = currentPercent/100f;
                rotateModifier = 180 - (currentPercent * 1.8f);
            }

            RenderUtil.renderRisingFromTopOfBlock(renderItem, tileEntity, heightModifier, rotateModifier);
        }
        else{
            RenderUtil.renderItemFloatingOnTileEntity(renderItem, tileEntity);
        }

        GlStateManager.popMatrix();
    }


}
