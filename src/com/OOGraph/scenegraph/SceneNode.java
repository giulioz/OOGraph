package com.OOGraph.scenegraph;

import com.OOGraph.math.Matrix;
import com.OOGraph.primitives.Vertex;

import java.util.Collection;

public interface SceneNode<Tvertex extends Vertex> {
    default void update(float dt) {
        if (getEnabled()) {
            for (SceneNode children : getChildren()) {
                children.update(dt);
            }
        }
    }

    default void draw(Matrix parentTransform, Renderer<Tvertex> renderer) {
        if (getEnabled()) {
            Matrix t = parentTransform.multiply(getTransform());
            for (SceneNode<Tvertex> children : getChildren()) {
                children.draw(t, renderer);
            }
        }
    }

    boolean getEnabled();
    void setEnabled(boolean enabled);

    Collection<SceneNode<Tvertex>> getChildren();
    void addChildren(SceneNode<Tvertex> children);
    void removeChildren(SceneNode<Tvertex> children);

    default Matrix getTransform() {
        return SceneGraphFactory.getFactory().createIdentityMatrix();
    }
}
