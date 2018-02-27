package com.mart.solar.client.gui.button;

import com.mart.solar.client.gui.GuiBook;
import com.mart.solar.client.gui.GuiPagesManager;
import com.mart.solar.client.gui.pages.GuiPage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.fml.client.config.GuiButtonExt;

import java.awt.*;

public class BookButton extends GuiButtonExt {

    private GuiPage page;

    public BookButton(GuiPage page, int x0, int y0, int buttonID){
        this(buttonID, x0, y0, page.getPageTitle());
        this.page = page;
        this.width = Minecraft.getMinecraft().fontRenderer.getStringWidth(page.getPageTitle());
        this.height = 8;
    }

    public BookButton(int buttonId, int x, int y, String buttonText) {
        super(buttonId, x, y, buttonText);
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
        this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
        this.mouseDragged(mc, mouseX, mouseY);

        GlStateManager.enableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.disableLighting();

        mc.fontRenderer.drawString(this.displayString, this.x, this.y, Color.BLACK.getRGB());

        if (this.hovered)
        {
           this.drawHorizontalLine(this.x, this.x + this.width, this.y + this.height, Color.BLACK.getRGB());
        }

        GlStateManager.enableLighting();
        GlStateManager.disableAlpha();
        GlStateManager.disableBlend();
    }

    public void openPage() {
        GuiPage newPage =  page.getNewInstance();

        GuiBook guiBook = GuiPagesManager.getPlayerGUI(Minecraft.getMinecraft().player);
        guiBook.setCurrentGui(newPage);
        Minecraft.getMinecraft().displayGuiScreen(newPage);
    }
}
