package com.OOGraph.scenegraph;

import com.OOGraph.math.Matrix;
import com.OOGraph.primitives.Vertex;

import java.util.Collection;
import java.util.LinkedList;

public abstract class SceneGraphFactory {
    private static SceneGraphFactory factory;
    public static SceneGraphFactory getFactory() {
        if (factory == null)
            factory = new DefaultSceneGraphFactory();
        return factory;
    }

    public abstract <T extends Vertex> Collection<SceneNode<T>> createSceneNodeCollection();
    public abstract Matrix createIdentityMatrix();
}

class DefaultSceneGraphFactory extends SceneGraphFactory {
    @Override
    public <T extends Vertex> Collection<SceneNode<T>> createSceneNodeCollection() {
        return new LinkedList<>();
    }

    @Override
    public Matrix createIdentityMatrix() {
        return new Matrix(4, 4);
    }
}
