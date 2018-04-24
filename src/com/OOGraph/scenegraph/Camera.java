package com.OOGraph.scenegraph;

import com.OOGraph.math.Matrix;
import com.OOGraph.primitives.Vertex;

import java.util.Collection;

public class Camera<Tvertex extends Vertex> implements SceneNode<Tvertex> {
    private Matrix view, projection;
    private Collection<SceneNode> children;

    public Camera() {
        this.view = SceneGraphFactory.getFactory().createIdentityMatrix();
        this.projection = SceneGraphFactory.getFactory().createIdentityMatrix();
        this.children = SceneGraphFactory.getFactory().createSceneNodeCollection();
    }

    public Camera(Matrix view, Matrix projection) {
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
}
