package com.OOGraph.primitives.meshes;

import com.OOGraph.math.Matrix;
import com.OOGraph.primitives.vertices.Triangle;
import com.OOGraph.primitives.vertices.Vertex;

import java.util.ArrayList;

public class ArrayMesh<T extends Vertex> extends ArrayList<Triangle<T>> implements Mesh<T> {
    @Override
    public Mesh<T> transformMatrix(Matrix transform) {
        ArrayMesh<T> clonedList = new ArrayMesh<>();
        for (Triangle<T> triangle : this) {
            clonedList.add(triangle.transformMatrix(transform));
        }
        return clonedList;
    }
}
