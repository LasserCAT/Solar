package com.mart.solar.client.gui.pages;

import com.mart.solar.client.gui.GuiBase;
import com.mart.solar.client.gui.GuiBook;
import com.mart.solar.client.gui.GuiPagesManager;
import com.mart.solar.client.gui.button.NextButton;
import com.mart.solar.client.gui.button.PreviousButton;
import com.mart.solar.client.gui.pages.component.PageComponent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

public class GuiPage extends GuiBase{

    private String pageTitle;

    private int currentPage = 1;
    private int pagesCount = 0;

    private LinkedList<PageComponent> pageComponents = new LinkedList<>();

    public GuiPage(String pageTitle, PageComponent... component){
        this.pageTitle = pageTitle;
        this.pageComponents.addAll(Arrays.asList(component));
    }

    public GuiPage(String pageTitle, LinkedList<PageComponent> pageComponents){
        this.pageTitle = pageTitle;
        this.pageComponents = pageComponents;
    }

    @Override
    public void initGui() {
        super.initGui();

        int x = (this.width - WIDTH) / 2;
        int y = (this.height - HEIGHT) / 2;
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
                pg.draw(mouseX, mouseY, fontRenderer);
            }
        });

        GL11.glPopMatrix();

        GL11.glPushMatrix();

        GL11.glTranslatef(x + 195, y + 20, 0);

        this.pageComponents.forEach(pg -> {
            if(pg.getPageNumber() == currentPage + 1){
                pg.draw(mouseX, mouseY, fontRenderer);
            }
        });

        GL11.glPopMatrix();

    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);

        if(mouseButton == 1){
            GuiBook playerGui = GuiPagesManager.getPlayerGUI(Minecraft.getMinecraft().player);
            Minecraft.getMinecraft().displayGuiScreen(playerGui.getPreviousGui());
        }

        this.pageComponents.forEach(pg -> pg.mouseClicked(mouseX, mouseY));

    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        super.actionPerformed(button);

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

    public GuiPage getNewInstance(){
        return new GuiPage(this.pageTitle, this.pageComponents);
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
