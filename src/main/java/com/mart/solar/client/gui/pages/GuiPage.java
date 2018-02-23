package com.mart.solar.client.gui.pages;

import com.mart.solar.client.gui.GuiBase;
import com.mart.solar.client.gui.GuiGuide;
import com.mart.solar.client.gui.button.BackButton;
import com.mart.solar.client.gui.button.NextButton;
import com.mart.solar.client.gui.button.PreviousButton;
import com.mart.solar.client.gui.pages.component.PageComponent;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.io.IOException;
import java.util.LinkedList;

public class GuiPage extends GuiBase{

    private String pageTitle;

    private int currentPage = 1;
    private int pagesCount = 0;

    private LinkedList<PageComponent> pageComponents = new LinkedList<>();

    public GuiPage(String pageTitle, PageComponent... component){
        this.pageTitle = pageTitle;
        for(PageComponent pageComponent : component){
            this.pageComponents.add(pageComponent);
        }
    }

    @Override
    public void initGui() {
        super.initGui();

        int x = (this.width - WIDTH) / 2;
        int y = (this.height - HEIGHT) / 2;
        this.addButton(new BackButton(x + (WIDTH/2) - 8, y + HEIGHT - 18, 100));
        this.addButton(new NextButton(x + WIDTH - 104 , y + HEIGHT - 18, 101, this));
        this.addButton(new PreviousButton(x + 95, y + HEIGHT - 18, 102, this));

        for(PageComponent pageComponent : pageComponents){
            if(pageComponent.getPageNumber() > this.pagesCount){
                pagesCount = pageComponent.getPageNumber();
            }
        }

    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);

        int x = (this.width - WIDTH) / 2;
        int y = (this.height - HEIGHT) / 2;

        GL11.glPushMatrix();

        if(currentPage == 1){
            GlStateManager.enableBlend();
            GlStateManager.enableAlpha();
            GlStateManager.disableLighting();

            float titleDistance = (WIDTH / 4) - (fontRenderer.getStringWidth(this.pageTitle) / 2);
            GL11.glTranslatef(x + titleDistance, y + 12, 0);
            fontRenderer.drawString(this.pageTitle, 0, 0, Color.BLACK.getRGB());

            GlStateManager.enableLighting();
            GlStateManager.disableAlpha();
            GlStateManager.disableBlend();

            GL11.glTranslatef(-titleDistance + 20, 12, 0);
        }
        else{
            GL11.glTranslatef(x + 20, y + 20, 0);
        }

        this.pageComponents.forEach(pg -> {
            if(pg.getPageNumber() == currentPage){
                pg.draw(x, y, fontRenderer);
            }
        });

        GL11.glPopMatrix();

        GL11.glPushMatrix();

        GL11.glTranslatef(x + 195, y + 20, 0);

        this.pageComponents.forEach(pg -> {
            if(pg.getPageNumber() == currentPage + 1){
                pg.draw(x, y, fontRenderer);
            }
        });

        GL11.glPopMatrix();

    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if (button instanceof BackButton) {
            Minecraft.getMinecraft().displayGuiScreen(new GuiGuide());
            return;
        }

        if(button instanceof NextButton){
            if(this.currentPage+1 >= getPagesCount()){
                return;
            }
            this.currentPage++;
        }

        if(button instanceof PreviousButton){
            if(this.currentPage == 1){
                return;
            }
            this.currentPage--;
        }
    }

    public int getPagesCount(){
        return pagesCount;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public String getPageTitle() {
        return pageTitle;
    }

}
