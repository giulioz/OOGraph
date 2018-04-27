package com.OOGraph.scenegraph;

import com.OOGraph.primitives.meshes.Mesh;
import com.OOGraph.primitives.vertices.Vertex;

public interface MeshRenderer<TVertex extends Vertex> {
    void drawMesh(Mesh<TVertex> mesh);
}
