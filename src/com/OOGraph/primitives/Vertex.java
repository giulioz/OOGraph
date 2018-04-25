package com.OOGraph.primitives;

import com.OOGraph.math.Matrix;
import com.OOGraph.math.Point;
import com.OOGraph.math.Vector;

public class Vertex {
    Vector position;

    public Vertex(Vector position) {
        this.position = position;
    }

    public Vector getPosition() {
        return position;
    }

    public Point getPoint() {
        return new Point((int)position.get(0), (int)position.get(1));
    }

    public Vertex createCopy() {
        return new Vertex(new Vector(position));
    }

    public Vertex transformMatrix(Matrix transform) {
        return new Vertex(position.multiply(transform));
    }
}
