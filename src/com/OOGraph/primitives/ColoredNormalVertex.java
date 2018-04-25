package com.OOGraph.primitives;

import com.OOGraph.math.Matrix;
import com.OOGraph.math.Vector;

public class ColoredNormalVertex extends ColoredVertex {
    Vector normal;

    public ColoredNormalVertex(Vector position, Vector normal, Vector color) {
        super(position, color);
        this.normal = normal;
    }

    public ColoredNormalVertex(Vector position, Vector normal, float r, float g, float b) {
        super(position, r, g, b);
        this.normal = normal;
    }

    public Vector getNormal() {
        return normal;
    }

    @Override
    public ColoredNormalVertex createCopy() {
        return new ColoredNormalVertex(new Vector(position), new Vector(normal), new Vector(color));
    }

    @Override
    public ColoredNormalVertex transformMatrix(Matrix transform) {
        return new ColoredNormalVertex(position.multiply(transform), normal.multiplyNormal(transform), color);
    }
}
