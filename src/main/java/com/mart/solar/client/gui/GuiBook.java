package com.mart.solar.client.gui;


import java.util.LinkedList;

public class GuiBook {

    private GuiBase currentGui;
    private LinkedList<GuiBase> previousPages = new LinkedList<>();

    public GuiBook(){
        this.currentGui = GuiPagesManager.getDefaultCategory();
    }

    public GuiBase getCurrentGui() {
        return currentGui;
    }

    public void setCurrentGui(GuiBase currentGui) {
        this.previousPages.add(this.currentGui);
        this.currentGui = currentGui;
    }

    public GuiBase getPreviousGui() {
        this.currentGui = this.previousPages.pollLast();
        return currentGui;
    }

    public void clearList(){
        this.previousPages.clear();
    }
}
