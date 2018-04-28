package com.tests;

import com.OOGraph.assets.ImgLoader;
import com.OOGraph.assets.ObjLoader;
import com.OOGraph.io.GraphicsFrame;
import com.OOGraph.io.SwingGraphicsFrame;
import com.OOGraph.math.Vector;
import com.OOGraph.primitives.meshes.Mesh;
import com.OOGraph.primitives.vertices.TexturedNormalVertex;
import com.OOGraph.primitives.vertices.Vertex;
import com.OOGraph.raster.*;
import com.OOGraph.raster.colors.ColorRGB24;
import com.OOGraph.raster.shaders.TexturedNormalPixelShader;
import com.OOGraph.raster.surfaces.SurfaceRGB24;
import com.OOGraph.raster.surfaces.SurfaceZBuffer;
import com.OOGraph.scenegraph.nodes.GroupNode;
import com.OOGraph.scenegraph.nodes.MeshSceneNode;
import com.OOGraph.scenegraph.nodes.TexturedQuadNode;

import java.io.FileInputStream;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // Graphics frame
        GraphicsFrame<ColorRGB24> frame = new SwingGraphicsFrame();
        frame.open();
        frame.setSize(800, 600);
        SurfaceDrawer<ColorRGB24> drawer = new SurfaceDrawer<>(frame.getFramebuffer());

        // ZBuffer
        ZBuffer zBuffer = new SurfaceZBuffer(frame.getWidth(), frame.getHeight());

        // Texture 1
        SurfaceRGB24 texture1 = null;
        try {
            texture1 = ImgLoader.getInstance().loadAsset(new FileInputStream("test.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Cube node
        GroupNode<Vertex> groupNode = new GroupNode<>();
        TexturedCubeNode cube = new TexturedCubeNode(
                new RasterMeshRenderer<>(
                        frame.getFramebuffer(),
                        new TexturedNormalPixelShader<>(frame.getFramebuffer(), new Vector(0, 1, 1), texture1),
                        zBuffer
                )
        );
        cube.setScale(new Vector(0.3f, 0.3f, 0.3f));
        cube.setPosition(new Vector(1, 0, 0));
        groupNode.addChildren(cube);

        // Texture 2
        SurfaceRGB24 texture2 = null;
        try {
            texture2 = ImgLoader.getInstance().loadAsset(new FileInputStream("water.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Sphere node
        Mesh<TexturedNormalVertex> sphereMesh = null;
        try {
            sphereMesh = ObjLoader.getInstance().loadAsset(new FileInputStream("figure.obj"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        MeshSceneNode<TexturedNormalVertex> sphere = new MeshSceneNode<>(
                sphereMesh,
                new RasterMeshRenderer<>(
                        frame.getFramebuffer(),
                        new TexturedNormalPixelShader<>(frame.getFramebuffer(), new Vector(0, 1, 1), texture2),
                        zBuffer
                )
        );
        sphere.setScale(new Vector(1, 1, 1).multiply(0.7f));
        groupNode.addChildren(sphere);

        // CameraNode root node
        // CameraNode cameraNodeNode = new CameraNode(new Vector(0,0,1), new Vector(0,0,0), new Vector(0,1,0));
        GroupNode<Vertex> cameraNodeNode = new GroupNode<>();
        cameraNodeNode.setRotation(new Vector(-0.3f, 0, 0));
        cameraNodeNode.setScale(new Vector(0.7f, 0.7f, 0.7f));
        cameraNodeNode.addChildren(groupNode);

        // Rendering cycle
        float t = 0.0f;
        while (frame.isOpen()) {
            zBuffer.clear();
            drawer.clear(new ColorRGB24(0, 0, 0));

            cube.setRotation(new Vector(0, t * 2.0f, 0));
            groupNode.setRotation(new Vector(0, t, 0));
            cameraNodeNode.draw(frame.getFramebuffer().cartesianToSurfaceMatrix());

            frame.swapBuffers();
            t += 0.1f;
        }
    }
}

class TexturedCubeNode extends GroupNode {
    TexturedQuadNode front, rear, left, right, top, bottom;
    RasterMeshRenderer<TexturedNormalVertex, ColorRGB24> renderer;

    public TexturedCubeNode(RasterMeshRenderer<TexturedNormalVertex, ColorRGB24> renderer) {
        super();
        this.renderer = renderer;

        front = new TexturedQuadNode(renderer);
        front.setPosition(new Vector(0, 0, -1));
        front.setRotation(new Vector(0, (float) (Math.PI), 0));
        this.addChildren(front);

        rear = new TexturedQuadNode(renderer);
        rear.setPosition(new Vector(0, 0, 1));
        rear.setRotation(new Vector(0, 0, 0));
        this.addChildren(rear);

        left = new TexturedQuadNode(renderer);
        left.setPosition(new Vector(-1, 0, 0));
        left.setRotation(new Vector(0, (float) (Math.PI / 2.0f), 0));
        this.addChildren(left);

        right = new TexturedQuadNode(renderer);
        right.setPosition(new Vector(1, 0, 0));
        right.setRotation(new Vector(0, (float) (-Math.PI / 2.0f), 0));
        this.addChildren(right);

        top = new TexturedQuadNode(renderer);
        top.setPosition(new Vector(0, 1, 0));
        top.setRotation(new Vector((float) (-Math.PI / 2.0f), 0, 0));
        this.addChildren(top);

        bottom = new TexturedQuadNode(renderer);
        bottom.setPosition(new Vector(0, -1, 0));
        bottom.setRotation(new Vector((float) (Math.PI / 2.0f), 0, 0));
        this.addChildren(bottom);
    }
}
