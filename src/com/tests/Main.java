package com.tests;

import com.OOGraph.assets.ImgLoader;
import com.OOGraph.io.GraphicsFrame;
import com.OOGraph.io.SwingGraphicsFrame;
import com.OOGraph.primitives.*;
import com.OOGraph.rastersurfaces.RasterRenderer;
import com.OOGraph.rastersurfaces.SurfaceRGB24;
import com.OOGraph.rastersurfaces.colors.ColorRGB24;
import com.OOGraph.rastersurfaces.shaders.ColoredNormalPixelShader;
import com.OOGraph.rastersurfaces.shaders.ColoredPixelShader;
import com.OOGraph.rastersurfaces.shaders.FillPixelShader;
import com.OOGraph.math.Matrix;
import com.OOGraph.math.Vector;
import com.OOGraph.scenegraph.Camera;
import com.OOGraph.scenegraph.MeshSceneNode;
import com.OOGraph.scenegraph.Renderer;
import com.OOGraph.scenegraph.SceneGraphFactory;

import java.io.FileInputStream;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        // Graphics frame
        GraphicsFrame<ColorRGB24> frame = new SwingGraphicsFrame();
        frame.open();
        frame.setSize(800, 600);

        // Texture
        SurfaceRGB24 texture = null;
        try {
            texture = ImgLoader.getInstance().loadAsset(new FileInputStream("test.bmp"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Clear Renderer
        RasterRenderer<Vertex, ColorRGB24> clearRenderer = new RasterRenderer<>(
                frame.getFramebuffer(),
                new FillPixelShader<>(new ColorRGB24(0, 0, 0))
        );

        // Mesh Renderer
        Renderer<ColoredNormalVertex> meshRenderer = new RasterRenderer<>(
                frame.getFramebuffer(),
                new ColoredNormalPixelShader(new Vector(0, 0, 1))
        );

        // Mesh node
        Mesh<ColoredNormalVertex> mesh = new ArrayMesh<>();
        mesh.add(new Triangle<>(
                new ColoredNormalVertex(new Vector(-0.5f, -0.5f, 0.0f), new Vector(0, 0, -1), 1, 0, 0),
                new ColoredNormalVertex(new Vector(0.5f, -0.5f, 0.0f), new Vector(0, 0, -1), 0, 1, 0),
                new ColoredNormalVertex(new Vector(0.0f, 0.5f, 0.0f), new Vector(0, 0, -1), 0, 0, 1)
        ));
        MeshSceneNode<ColoredNormalVertex> triangleNode = new MeshSceneNode<>(mesh);

        // Camera root node
        Camera<ColoredNormalVertex> cameraNode = new Camera<>();
        cameraNode.addChildren(triangleNode);

        // Rendering cycle
        float t = 0.0f;
        while (frame.isOpen()) {
            clearRenderer.fill();
            triangleNode.setRotation(new Vector(t, t, t));
            cameraNode.draw(frame.getFramebuffer().cartesianToSurfaceMatrix(), meshRenderer);

            frame.swapBuffers();
            t += 0.1f;
        }
    }
}
