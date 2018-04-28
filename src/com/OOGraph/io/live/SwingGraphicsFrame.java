package com.OOGraph.io.live;

import com.OOGraph.raster.surfaces.Surface;
import com.OOGraph.raster.surfaces.SurfaceRGB24;
import com.OOGraph.raster.colors.ColorRGB24;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class SwingGraphicsFrame extends JFrame implements GraphicsFrame<ColorRGB24> {
    private BufferedImage bufferedImage;
    private Surface<ColorRGB24> frameBuffer;
    private int width, height;

    public SwingGraphicsFrame() {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void createBuffer(int width, int height) {
        bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        frameBuffer = new SurfaceRGB24(width, height);
    }

    @Override
    public void setSize(int width, int height) {
        Insets i = getInsets();
        this.width = width;
        this.height = height;
        super.setSize(width, height + i.top);
        createBuffer(width, height);
    }

    @Override
    public boolean isOpen() {
        return super.isVisible();
    }

    @Override
    public void swapBuffers() {
        java.awt.Graphics2D graphics2D = (java.awt.Graphics2D) this.getGraphics();
        byte[] convertedData = frameBuffer.getSurfaceData();
        int j = 0;
        for (int y = 0; y < frameBuffer.getHeight(); y++) {
            for (int x = 0; x < frameBuffer.getWidth(); x++) {
                int color = ((convertedData[j] & 0xFF) << 16)
                        + ((convertedData[j + 1] & 0xFF) << 8)
                        + ((convertedData[j + 2] & 0xFF));
                bufferedImage.setRGB(x, y, color);
                j += 3;
            }
        }
        Insets i = getInsets();
        graphics2D.drawImage(bufferedImage, null, i.left, i.top);
    }

    @Override
    public Surface<ColorRGB24> getFramebuffer() {
        return frameBuffer;
    }

    @Override
    public void open() {
        this.setVisible(true);
    }

    @Override
    public void close() {
        this.close();
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }
}
