package com.OOGraph.primitives;

import com.OOGraph.math.Vector;

public class NormalVertex extends Vertex {
    private Vector normal;

    public Vector getNormal() {
        return normal;
    }

    public NormalVertex(Vector position, Vector normal) {
        super(position);
        this.normal = normal;
    }
}
