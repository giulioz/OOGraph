package com.po2018;

import com.v2.*;
import com.v2.surfaces.colors.ColorRGB24;
import com.v2.surfaces.shaders.ColorShader;

public class Main {

    public static void main(String[] args) {
        SwingGraphicsFrame frame = new SwingGraphicsFrame();
        frame.open();
        frame.setSize(800, 600);
        Graphics2D<ColorRGB24> graphics = new Graphics2D<>(frame.getFramebuffer());

        float t = 0.0f;
        while (frame.isOpen()) {
            graphics.clear(new ColorRGB24(0, 0, 0));
            graphics.drawLine(0, 0, frame.getWidth() - 1, frame.getHeight() - 1, new ColorRGB24(255, 0, 0));
            graphics.drawRectangle(20, 30, 100, 150, new ColorRGB24(0, 0, 255));

            graphics.setShader(new ColorShader<ColorRGB24>(new ColorRGB24(50, 50, 100)));
            graphics.fillRectangle(100, 200, 200, 50);

            graphics.setShader(new Shader<ColorRGB24,Object>() {
                @Override
                public Object getUniforms() {
                    return null;
                }
                @Override
                public void setUniforms(Object data) {

                }

                @Override
                public ColorRGB24 getColor(Point coord) {
                    float intensity = (float)((Math.sin(coord.getX() / 10.0) + 1.0) / 2.0);
                    return new ColorRGB24(0, (int)(255 * intensity), 0);
                }
            });
            graphics.fillRectangle(50, 80, 200, 50);

            frame.swapBuffers();
            t += 0.1f;
        }
    }
}
