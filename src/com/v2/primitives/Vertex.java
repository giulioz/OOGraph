package com.v2.primitives;

import com.v2.Point;
import com.v2.vectormath.Vector;

public class Vertex {
    protected Vector position;

    public Vertex(Vector position) {
        this.position = position;
    }

    public Vertex(float x, float y) {
        this.position = new Vector(x, y);
    }

    public Vector getPosition() {
        return position;
    }

    public Point getPoint() {
        return new Point((int)position.getComponent(0), (int)position.getComponent(1));
    }
}
