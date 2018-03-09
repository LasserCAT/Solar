package com.mart.solar.client.gui;

import com.mart.solar.client.gui.button.BookButton;
import com.mart.solar.client.gui.pages.GuiPage;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.item.Item;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GuiCategory extends GuiBase{

    private final Item displayItem;
    private String categoryName;

    private List<GuiPage> pages = new ArrayList<>();

    @Override
    public void initGui() {
        super.initGui();
        int xOffset = 20;
        int yOffset = 25;

        int x = (this.width - WIDTH) / 2;
        int y = (this.height - HEIGHT) / 2;

        int buttonAmount = 0;
        for(GuiPage guiPage : this.pages){
            BookButton button = new BookButton(guiPage, x + xOffset, y + yOffset + (buttonAmount * 10), buttonAmount);
            this.addButton(button);
            buttonAmount++;
        }

    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        super.actionPerformed(button);
        if(button instanceof BookButton){
            BookButton bookButton = (BookButton) button;
            bookButton.openPage();
        }
    }

    public GuiCategory(Item displayItem, String categoryName) {
        this.displayItem = displayItem;
        this.categoryName = categoryName;
    }

    public Item getDisplayItem() {
        return displayItem;
    }

    public void addPage(GuiPage page){
        this.pages.add(page);
    }

    public String getCategoryName() {
        return categoryName;
    }
}
