package com.mart.solar.client.gui.pages;

import com.mart.solar.client.gui.GuiBase;
import com.mart.solar.client.gui.pages.component.PageComponent;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.LinkedList;

public class GuiPage extends GuiBase{

    private String[] infoText;
    private String pageTitle;
    private Item representItem;
    private Block representBlock;

    private LinkedList<PageComponent> pageComponents = new LinkedList<>();

    public GuiPage(String pageTitle, PageComponent... component){
        this.pageTitle = pageTitle;
        for(PageComponent pageComponent : component){
            this.pageComponents.add(pageComponent);
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);

        int x = (this.width - WIDTH) / 2;
        int y = (this.height - HEIGHT) / 2;

        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        float titleDistance = (WIDTH / 4) - (fontRenderer.getStringWidth(this.pageTitle) / 2);
        GL11.glTranslatef(x + titleDistance, y + 15, 0);
        fontRenderer.drawString(this.pageTitle, 0, 0, Color.BLACK.getRGB());

        GL11.glTranslatef(-titleDistance + 3, 12, 0);

        for(PageComponent pageComponent : this.pageComponents){
            pageComponent.draw(x, y, fontRenderer);
        }

        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glPopMatrix();
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public Item getRepresentItem() {
        return representItem;
    }
}
