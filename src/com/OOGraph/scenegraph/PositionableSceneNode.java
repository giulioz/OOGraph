package com.OOGraph.scenegraph;

import com.OOGraph.math.Vector;
import com.OOGraph.primitives.Vertex;

public interface PositionableSceneNode<Tvertex extends Vertex> extends SceneNode<Tvertex> {
    Vector getPosition();
    void setPosition(Vector pos);
    Vector getRotation();
    void setRotation(Vector rot);
    Vector getScale();
    void setScale(Vector scale);
}
