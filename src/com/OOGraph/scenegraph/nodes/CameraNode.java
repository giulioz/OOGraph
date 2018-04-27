package com.OOGraph.scenegraph.nodes;

import com.OOGraph.math.Matrix;
import com.OOGraph.math.Vector;
import com.OOGraph.primitives.vertices.Vertex;
import com.OOGraph.scenegraph.MeshRenderer;
import com.OOGraph.scenegraph.SceneGraphFactory;
import com.OOGraph.scenegraph.SceneNode;

import java.util.Collection;

public class CameraNode implements SceneNode<Vertex> {
    private Matrix view, projection;
    private Collection<SceneNode> children;

    public CameraNode() {
        this.view = SceneGraphFactory.getFactory().createIdentityMatrix();
        this.projection = SceneGraphFactory.getFactory().createIdentityMatrix();
        this.children = SceneGraphFactory.getFactory().createSceneNodeCollection();
    }

    public CameraNode(Vector camPos, Vector camDir, Vector camUp) {
        this.view = Matrix.createLookAt_4x4(camPos, camDir, camUp);
        this.projection = SceneGraphFactory.getFactory().createIdentityMatrix();
        this.children = SceneGraphFactory.getFactory().createSceneNodeCollection();
    }

    public CameraNode(Matrix view, Matrix projection) {
        this.view = view;
        this.projection = projection;
        this.children = SceneGraphFactory.getFactory().createSceneNodeCollection();
    }

    @Override
    public boolean getEnabled() {
        return true;
    }

    @Override
    public void setEnabled(boolean enabled) {
        // cannot disable a camera...
    }

    @Override
    public Collection<SceneNode> getChildren() {
        return children;
    }

    @Override
    public void addChildren(SceneNode children) {
        this.children.add(children);
    }

    @Override
    public void removeChildren(SceneNode children) {
        this.children.remove(children);
    }

    @Override
    public Matrix getTransform() {
        return projection.multiply(view);
    }

    @Override
    public MeshRenderer<Vertex> getMeshRenderer() {
        return null;
    }
}
