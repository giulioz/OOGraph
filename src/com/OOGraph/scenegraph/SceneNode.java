package com.OOGraph.scenegraph;

import com.OOGraph.surfaces.Surface;

public interface SceneNode {
    void update(float dt);
    void draw(Surface surface);
}
