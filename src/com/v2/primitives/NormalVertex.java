package com.v2.primitives;

import com.v2.Point;
import com.v2.vectormath.Vector;

public class NormalVertex extends Vertex {
    private Vector normal;

    public Vector getNormal() {
        return normal;
    }

    public NormalVertex(Point position, Vector normal) {
        super(position);
        this.normal = normal;
    }

    public NormalVertex(int x, int y, Vector normal) {
        super(x, y);
        this.normal = normal;
    }
}
