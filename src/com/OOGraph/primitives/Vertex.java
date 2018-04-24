package com.OOGraph.primitives;

import com.OOGraph.math.Point;
import com.OOGraph.math.Vector;

public class Vertex {
    protected Vector position;

    public Vertex(Vector position) {
        this.position = position;
    }

    public Vector getPosition() {
        return position;
    }

    public Point getPoint() {
        return new Point((int)position.get(0), (int)position.get(1));
    }

    public MatrixVertexTransformer getMatrixVertexTransformer() {
        return new MatrixVertexTransformer<Vertex>() {
            @Override
            public Vertex transform(Vertex in) {
                return new Vertex(in.position.multiply(getTransformMatrix()));
            }
        };
    }
}
