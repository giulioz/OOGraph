package com.OOGraph.primitives;

import com.OOGraph.math.Matrix;

public abstract class MatrixVertexTransformer<T extends Vertex> implements VertexTransformer<T> {
    private Matrix transformMatrix;

    public Matrix getTransformMatrix() {
        return transformMatrix;
    }

    public void setTransformMatrix(Matrix transformMatrix) {
        this.transformMatrix = transformMatrix;
    }
}
