package com.OOGraph.scenegraph;

import com.OOGraph.primitives.Mesh;
import com.OOGraph.primitives.Vertex;

public interface Renderer<TVertex extends Vertex> {
    void drawMesh(Mesh<TVertex> mesh);
    void fill();
}
