package com.OOGraph.primitives;

import com.OOGraph.math.Vector;

public class ColoredVertex extends Vertex {
    protected Vector color;
    public Vector getColorVector() { return color; }

    public ColoredVertex(Vector position, Vector color) {
        super(position);
        this.color = color;
    }

    public ColoredVertex(Vector position, float r, float g, float b) {
        super(position);
        this.color = new Vector(r, g, b);
    }
}
