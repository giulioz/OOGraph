package com.OOGraph.scenegraph;

import com.OOGraph.math.Matrix;

import java.util.Collection;

public interface SceneNode {
    default void update(float dt) {
        if (getEnabled()) {
            for (SceneNode children : getChildren()) {
                children.update(dt);
            }
        }
    }

    default void draw(Matrix parentTransform) {
        if (getEnabled()) {
            Matrix t = parentTransform.multiply(getTransform());
            for (SceneNode children : getChildren()) {
                children.draw(t);
            }
        }
    }

    boolean getEnabled();
    void setEnabled(boolean enabled);

    Collection<SceneNode> getChildren();

    void addChildren(SceneNode children);

    void removeChildren(SceneNode children);

    default Matrix getTransform() {
        return SceneGraphFactory.getFactory().createIdentityMatrix();
    }
}
