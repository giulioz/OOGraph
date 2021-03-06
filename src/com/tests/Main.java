package com.tests;

import com.OOGraph.assets.ObjLoader;
import com.OOGraph.io.files.IOColorRGB24;
import com.OOGraph.io.live.GraphicsFrame;
import com.OOGraph.io.live.SwingGraphicsFrame;
import com.OOGraph.math.Vector;
import com.OOGraph.primitives.meshes.Mesh;
import com.OOGraph.primitives.vertices.TexturedNormalVertex;
import com.OOGraph.primitives.vertices.Vertex;
import com.OOGraph.raster.*;
import com.OOGraph.raster.colors.ColorRGB24;
import com.OOGraph.raster.shaders.TexturedNormalPixelShader;
import com.OOGraph.raster.surfaces.Surface;
import com.OOGraph.raster.surfaces.SurfaceRGB24;
import com.OOGraph.raster.surfaces.SurfaceZBuffer;
import com.OOGraph.scenegraph.nodes.GroupNode;
import com.OOGraph.scenegraph.nodes.MeshSceneNode;
import com.OOGraph.scenegraph.nodes.TexturedQuadNode;

import java.io.FileInputStream;
import java.io.FileOutputStream;
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

        // Background Texture
        Surface<ColorRGB24> backgroundTexture = null;
        try {
            backgroundTexture = IOColorRGB24.getInstance().loadFile(new FileInputStream("tex.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Texture 1
        Surface<ColorRGB24> texture1 = null;
        try {
            texture1 = IOColorRGB24.getInstance().loadFile((new FileInputStream("test.jpg")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Cube node
        GroupNode groupNode = new GroupNode();
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
        Surface<ColorRGB24> texture2 = null;
        try {
            texture2 = IOColorRGB24.getInstance().loadFile(new FileInputStream("water.jpg"));
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
        // CameraNode cameraNode = new CameraNode(new Vector(0,0,1), new Vector(0,0,0), new Vector(0,1,0));
        GroupNode cameraNode = new GroupNode();
        cameraNode.setRotation(new Vector(-0.3f, 0, 0));
        cameraNode.setScale(new Vector(0.7f, 0.7f, 0.7f));
        cameraNode.addChildren(groupNode);

        // Save frame to file
        try {
            cameraNode.draw(frame.getFramebuffer().cartesianToSurfaceMatrix());
            IOColorRGB24.getInstance().saveFile(new FileOutputStream("out.jpg"), frame.getFramebuffer());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Rendering cycle
        float t = 0.0f;
        while (frame.isOpen()) {
            zBuffer.clear();
            drawer.clear(new ColorRGB24(0, 0, 0));
            drawer.getSurface().blit(backgroundTexture, drawer.getSurface().getRect());

            cube.setRotation(new Vector(0, t * 2.0f, 0));
            groupNode.setRotation(new Vector(0, t, 0));
            cameraNode.draw(frame.getFramebuffer().cartesianToSurfaceMatrix());

            frame.swapBuffers();
            t += 0.1f;
        }
    }
}

class TexturedCubeNode extends GroupNode {
    TexturedCubeNode(RasterMeshRenderer<TexturedNormalVertex, ColorRGB24> renderer) {
        super();

        TexturedQuadNode front = new TexturedQuadNode(renderer);
        front.setPosition(new Vector(0, 0, -1));
        front.setRotation(new Vector(0, (float) (Math.PI), 0));
        this.addChildren(front);

        TexturedQuadNode rear = new TexturedQuadNode(renderer);
        rear.setPosition(new Vector(0, 0, 1));
        rear.setRotation(new Vector(0, 0, 0));
        this.addChildren(rear);

        TexturedQuadNode left = new TexturedQuadNode(renderer);
        left.setPosition(new Vector(-1, 0, 0));
        left.setRotation(new Vector(0, (float) (Math.PI / 2.0f), 0));
        this.addChildren(left);

        TexturedQuadNode right = new TexturedQuadNode(renderer);
        right.setPosition(new Vector(1, 0, 0));
        right.setRotation(new Vector(0, (float) (-Math.PI / 2.0f), 0));
        this.addChildren(right);

        TexturedQuadNode top = new TexturedQuadNode(renderer);
        top.setPosition(new Vector(0, 1, 0));
        top.setRotation(new Vector((float) (-Math.PI / 2.0f), 0, 0));
        this.addChildren(top);

        TexturedQuadNode bottom = new TexturedQuadNode(renderer);
        bottom.setPosition(new Vector(0, -1, 0));
        bottom.setRotation(new Vector((float) (Math.PI / 2.0f), 0, 0));
        this.addChildren(bottom);
    }
}
