package com.OOGraph.primitives;

import com.OOGraph.math.Matrix;

import java.util.ArrayList;

public class ArrayMesh<T extends Vertex> extends ArrayList<Triangle<T>> implements Mesh<T> {
    @Override
    public Mesh<T> transformVertices(VertexTransformer<T> transformer) {
        ArrayMesh<T> copyMesh = new ArrayMesh<>();
        for (Triangle<T> tTriangle : this) {
            copyMesh.add(tTriangle.transformVertices(transformer));
        }
        return copyMesh;
    }
}
