package com.po2018;

import com.v2.*;
import com.v2.assets.ImgLoader;
import com.v2.primitives.ColoredVertex;
import com.v2.primitives.Vertex;
import com.v2.surfaces.Graphics2D;
import com.v2.surfaces.SurfaceRGB24;
import com.v2.surfaces.colors.ColorRGB24;
import com.v2.surfaces.shaders.pixel.ColoredPixelShader;
import com.v2.surfaces.shaders.pixel.FillPixelShader;
import com.v2.primitives.Triangle;
import com.v2.surfaces.shaders.pixel.PixelShader;
import com.v2.surfaces.shaders.vertex.IdentityVertexShader;
import com.v2.surfaces.shaders.vertex.VertexShader;
import com.v2.vectormath.Matrix;
import com.v2.vectormath.Vector;

import java.io.FileInputStream;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        SwingGraphicsFrame frame = new SwingGraphicsFrame();
        frame.open();
        frame.setSize(800, 600);
        Graphics2D<ColorRGB24,ColoredVertex> graphics = new Graphics2D<>(
                frame.getFramebuffer(),
                new FillPixelShader<>(new ColorRGB24(255, 0, 0)));

        SurfaceRGB24 texture = null;
        try {
            texture = ImgLoader.getInstance().loadAsset(new FileInputStream("test.bmp"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        float t = 0.0f;
        while (frame.isOpen()) {
            graphics.clear(new ColorRGB24(0, 0, 0));
            graphics.setPixelShader(new FillPixelShader<>(new ColorRGB24(255, 0, 0)));
            graphics.drawLine(0, 0, frame.getWidth() - 1, frame.getHeight() - 1);

            graphics.setPixelShader(new FillPixelShader<>(new ColorRGB24(0, 255, 255)));
            graphics.drawRectangle(20, 30, 100, 150);

            graphics.setPixelShader(new FillPixelShader<>(new ColorRGB24(50, 50, 100)));
            graphics.fillRectangle(100, 200, 200, 50);

            graphics.setPixelShader((x, y, va, vb, vc, barycentric) -> {
                float intensity = (float)((Math.sin(x / 10.0) + 1.0) / 2.0);
                return new ColorRGB24(0, (int)(255 * intensity), 0);
            });
            graphics.fillRectangle(50, 80, 200, 50);

            Triangle<ColoredVertex> triangle = new Triangle<>(
                    new ColoredVertex(0.0f, 0.0f, 1, 0, 0),
                    new ColoredVertex(0.0f, 1.0f, 0, 1, 0),
                    new ColoredVertex(1.0f, 1.0f, 0, 0, 1)
            );
            graphics.setPixelShader(new ColoredPixelShader());
            final float rt = t;
            graphics.fillTriangle(triangle, (ColoredVertex input) -> {
                Vector pos = input.getPosition();
                Matrix screen = Matrix.createTranslation(4, 4, new Vector(4, 4, graphics.getSurface().getWidth() / 2, graphics.getSurface().getHeight() / 2 ))
                        .multiply(Matrix.createScale(4,4, new Vector(graphics.getSurface().getWidth() / 2, graphics.getSurface().getHeight() / 2)));
                //Matrix mat = Matrix.createRotationY_4x4(rt).multiply(screen);
                Vector tPos = pos.multiply(screen);
                return new ColoredVertex(tPos, input.getColorVector());
            });

            graphics.getSurface().blit(texture, new Point(100, 400));

            frame.swapBuffers();
            t += 0.1f;
        }
    }
}
