package com.OOGraph.primitives;

import java.util.List;

public interface Mesh<T extends Vertex> extends List<Triangle<T>> {
    Mesh<T> transformVertices(VertexTransformer<T> transformer);

    default MatrixVertexTransformer<T> getMatrixMultiplicator() {
        if (size() > 0) return get(0).getMatrixMultiplicator();
        else return null;
    }

    // funzioni fighe per le mesh
}
