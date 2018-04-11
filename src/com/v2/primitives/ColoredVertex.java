package com.v2.primitives;

import com.v2.Point;
import com.v2.surfaces.colors.ColorRGB24;

public class ColoredVertex extends Vertex {
    private ColorRGB24 color;

    public ColorRGB24 getColor() {
        return color;
    }

    public ColoredVertex(Point position, ColorRGB24 color) {
        super(position);
        this.color = color;
    }

    public ColoredVertex(int x, int y, ColorRGB24 color) {
        super(x, y);
        this.color = color;
    }
}
