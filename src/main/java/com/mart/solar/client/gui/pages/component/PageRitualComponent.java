package com.mart.solar.client.gui.pages.component;

import com.mart.solar.api.ritual.Ritual;
import com.mart.solar.client.gui.GuiBook;
import com.mart.solar.client.gui.GuiPagesManager;
import com.mart.solar.client.gui.GuiRitualVisualizer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

import javax.vecmath.Matrix3f;
import javax.vecmath.Matrix4f;
import javax.vecmath.Point3f;
import java.awt.*;
import java.nio.FloatBuffer;

public class PageRitualComponent extends PageComponent{

    private Ritual ritual;
    private int width, height;
    private String text = "Open ritual drawing";
    private boolean hovered;

    public PageRitualComponent(Ritual ritual){
        this.ritual = ritual;
        this.width = Minecraft.getMinecraft().fontRenderer.getStringWidth(this.text);
        this.height = 8;
        this.hovered = false;
    }

    @Override
    public void draw(int mouseX, int mouseY, FontRenderer fontRenderer) {
        int yTranslate = (160 - this.width) / 2;
        GlStateManager.translate(yTranslate, 30, 0);
        Matrix4f mat = getMatrix(GL11.GL_MODELVIEW_MATRIX);
        Point3f point = new Point3f();
        mat.transform(point);
        this.hovered = mouseX >= point.x && mouseY >= point.y && mouseX < point.x + this.width && mouseY < point.y + this.height;

        Minecraft mc = Minecraft.getMinecraft();

        GlStateManager.enableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.disableLighting();

        mc.fontRenderer.drawString(this.text, 0, 0, Color.BLACK.getRGB());

        if (this.hovered)
        {
            this.drawHorizontalLine(0, this.width, 9, Color.BLACK.getRGB());
        }

        GlStateManager.enableLighting();
        GlStateManager.disableAlpha();
        GlStateManager.disableBlend();
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY) {
        if(!this.hovered){
            return;
        }

        GuiRitualVisualizer ritualVisualizer = new GuiRitualVisualizer(this.ritual);
        GuiBook book = GuiPagesManager.getPlayerGUI(Minecraft.getMinecraft().player);
        book.setCurrentGui(ritualVisualizer);
        Minecraft.getMinecraft().displayGuiScreen(ritualVisualizer);
    }

    private static final FloatBuffer MATRIX_BUF = BufferUtils.createFloatBuffer(16);

    public static Matrix4f getMatrix(int matrix) {
        GlStateManager.getFloat(matrix, MATRIX_BUF);
        Matrix4f mat = new Matrix4f();
        for (int i = 0; i < 16; i++) {
            mat.setElement(i % 4, i / 4, MATRIX_BUF.get(i));
        }
        return mat;
    }

    /**
     * Draws a solid color rectangle with the specified coordinates and color.
     */
    public static void drawRect(int left, int top, int right, int bottom, int color)
    {
        if (left < right)
        {
            int i = left;
            left = right;
            right = i;
        }

        if (top < bottom)
        {
            int j = top;
            top = bottom;
            bottom = j;
        }

        float f3 = (float)(color >> 24 & 255) / 255.0F;
        float f = (float)(color >> 16 & 255) / 255.0F;
        float f1 = (float)(color >> 8 & 255) / 255.0F;
        float f2 = (float)(color & 255) / 255.0F;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.color(f, f1, f2, f3);
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION);
        bufferbuilder.pos((double)left, (double)bottom, 0.0D).endVertex();
        bufferbuilder.pos((double)right, (double)bottom, 0.0D).endVertex();
        bufferbuilder.pos((double)right, (double)top, 0.0D).endVertex();
        bufferbuilder.pos((double)left, (double)top, 0.0D).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    /**
     * Draws a thin horizontal line between two points.
     */
    protected void drawHorizontalLine(int startX, int endX, int y, int color)
    {
        if (endX < startX)
        {
            int i = startX;
            startX = endX;
            endX = i;
        }

        drawRect(startX, y, endX + 1, y + 1, color);
    }
}
