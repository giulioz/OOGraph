package com.OOGraph.primitives;

import com.OOGraph.math.Matrix;

import java.util.List;

public interface Mesh<T extends Vertex> extends List<Triangle<T>> {
    Mesh<T> cloneMesh();
    Mesh<T> transformMatrix(Matrix transform);

    // funzioni fighe per le mesh
}
