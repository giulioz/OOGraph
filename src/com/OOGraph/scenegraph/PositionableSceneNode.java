package com.OOGraph.scenegraph;

import com.OOGraph.math.Vector;
import com.OOGraph.primitives.vertices.Vertex;

public interface PositionableSceneNode extends SceneNode {
    Vector getPosition();
    void setPosition(Vector pos);
    Vector getRotation();
    void setRotation(Vector rot);
    Vector getScale();
    void setScale(Vector scale);
}
