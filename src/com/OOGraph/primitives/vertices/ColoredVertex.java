package com.OOGraph.primitives.vertices;

import com.OOGraph.math.Matrix;
import com.OOGraph.math.Vector;

public class ColoredVertex extends Vertex {
    Vector color;

    public ColoredVertex(Vector position, Vector color) {
        super(position);
        this.color = color;
    }

    public ColoredVertex(Vector position, float r, float g, float b) {
        super(position);
        this.color = new Vector(r, g, b);
    }

    public Vector getColor() {
        return color;
    }

    @Override
    public ColoredVertex transformMatrix(Matrix transform) {
        return new ColoredVertex(position.multiply(transform), color);
    }
}
