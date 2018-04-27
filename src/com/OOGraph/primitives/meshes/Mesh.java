package com.OOGraph.primitives.meshes;

import com.OOGraph.math.Matrix;
import com.OOGraph.primitives.vertices.Triangle;
import com.OOGraph.primitives.vertices.Vertex;

import java.util.List;

public interface Mesh<T extends Vertex> extends List<Triangle<T>> {
    Mesh<T> transformMatrix(Matrix transform);

    // funzioni fighe per le mesh
}
