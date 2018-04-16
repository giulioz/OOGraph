package com.v2.primitives;

import com.v2.vectormath.Vector;

public class ColoredVertex extends Vertex {
    protected Vector color;
    public Vector getColorVector() { return color; }

    public ColoredVertex(Vector position, Vector color) {
        super(position);
        this.color = color;
    }

    public ColoredVertex(float x, float y, Vector color) {
        super(x, y);
        this.color = color;
    }

    public ColoredVertex(Vector position, float r, float g, float b) {
        super(position);
        this.color = new Vector(r, g, b);
    }

    public ColoredVertex(float x, float y, float r, float g, float b) {
        super(x, y);
        this.color = new Vector(r, g, b);
    }
}
