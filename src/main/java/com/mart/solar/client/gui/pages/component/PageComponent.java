package com.mart.solar.client.gui.pages.component;

import net.minecraft.client.gui.FontRenderer;

public abstract class PageComponent {

    protected int pageNumber;

    public PageComponent(){
        this.pageNumber = 1;
    }

    public void draw(int xBegin, int yBegin, FontRenderer fontRenderer){
    }

    public PageComponent pageNumber(int number){
        this.pageNumber = 0;
        return this;
    }

    public int getPageNumber() {
        return pageNumber;
    }
}
