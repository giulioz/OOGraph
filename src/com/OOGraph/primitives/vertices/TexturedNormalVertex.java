package com.OOGraph.primitives.vertices;

import com.OOGraph.math.Matrix;
import com.OOGraph.math.Vector;

public class TexturedNormalVertex extends Vertex {
    Vector normal;
    Vector uv;

    public TexturedNormalVertex(Vector position, Vector normal, Vector uv) {
        super(position);
        this.normal = normal;
        this.uv = uv;
    }

    public Vector getNormal() {
        return normal;
    }

    public Vector getUv() {
        return uv;
    }

    @Override
    public TexturedNormalVertex transformMatrix(Matrix transform) {
        return new TexturedNormalVertex(position.multiply(transform), normal.multiplyNormal(transform), uv);
    }
}
