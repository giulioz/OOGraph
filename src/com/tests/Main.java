package com.tests;

import com.OOGraph.assets.ImgLoader;
import com.OOGraph.io.SwingGraphicsFrame;
import com.OOGraph.math.Point;
import com.OOGraph.primitives.ColoredVertex;
import com.OOGraph.surfaces.SurfaceDrawer;
import com.OOGraph.surfaces.SurfaceRGB24;
import com.OOGraph.surfaces.colors.ColorRGB24;
import com.OOGraph.surfaces.shaders.pixel.ColoredPixelShader;
import com.OOGraph.surfaces.shaders.pixel.FillPixelShader;
import com.OOGraph.primitives.Triangle;
import com.OOGraph.surfaces.shaders.vertex.VertexUtilities;
import com.OOGraph.math.Matrix;
import com.OOGraph.math.Vector;

import java.io.FileInputStream;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        SwingGraphicsFrame frame = new SwingGraphicsFrame();
        frame.open();
        frame.setSize(800, 600);
        SurfaceDrawer<ColorRGB24,ColoredVertex> graphics = new SurfaceDrawer<>(
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
                    new ColoredVertex(new Vector(-0.5f, -0.5f, 0.0f), 1, 0, 0),
                    new ColoredVertex(new Vector(0.5f, -0.5f, 0.0f), 0, 1, 0),
                    new ColoredVertex(new Vector(0.0f, 0.5f, 0.0f), 0, 0, 1)
            );
            graphics.setPixelShader(new ColoredPixelShader());
            final float rt = t;
            graphics.fillTriangle(triangle, input -> {
                Vector pos = input.getPosition();
                Matrix mat = Matrix.createRotationY_4x4(rt);
                Vector tPos = pos.multiply(mat);

                return new ColoredVertex(VertexUtilities.CartesianToScreen(graphics.getSurface(), tPos), input.getColorVector());
            });

            graphics.getSurface().blit(texture, new Point(100, 400));

            frame.swapBuffers();
            t += 0.1f;
        }
    }
}
