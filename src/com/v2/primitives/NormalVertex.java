package com.v2.primitives;

import com.v2.vectormath.Vector;

public class NormalVertex extends Vertex {
    private Vector normal;

    public Vector getNormal() {
        return normal;
    }

    public NormalVertex(Vector position, Vector normal) {
        super(position);
        this.normal = normal;
    }

    public NormalVertex(float x, float y, Vector normal) {
        super(x, y);
        this.normal = normal;
    }
}
