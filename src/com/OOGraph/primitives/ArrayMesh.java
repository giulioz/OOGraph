package com.OOGraph.primitives;

import com.OOGraph.math.Matrix;

import java.util.ArrayList;

public class ArrayMesh<T extends Vertex> extends ArrayList<Triangle<T>> implements Mesh<T> {
    @Override
    public Mesh<T> cloneMesh() {
        ArrayMesh<T> clonedList = new ArrayMesh<>();
        for (Triangle<T> triangle : this) {
            clonedList.add(new Triangle<>(triangle));
        }
        return clonedList;
    }

    @Override
    public Mesh<T> transformMatrix(Matrix transform) {
        ArrayMesh<T> clonedList = new ArrayMesh<>();
        for (Triangle<T> triangle : this) {
            clonedList.add(triangle.transformMatrix(transform));
        }
        return clonedList;
    }
}
