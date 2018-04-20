package com.OOGraph.surfaces.shaders.vertex;

import com.OOGraph.surfaces.Surface;
import com.OOGraph.math.Vector;

public class VertexUtilities {
    public static Vector CartesianToScreen(int width, int height, Vector input) {
        int halfWidth = width / 2;
        int halfHeight = height / 2;
        Vector tVec = input
            .multiply(new Vector(halfWidth, halfHeight))
            .sum(new Vector(halfWidth, halfHeight));
        return tVec;
    }

    public static Vector CartesianToScreen(Surface<?> surface, Vector input) {
        int halfWidth = surface.getWidth() / 2;
        int halfHeight = surface.getHeight() / 2;
        Vector tVec = input
                .multiply(new Vector(halfWidth, halfHeight))
                .sum(new Vector(halfWidth, halfHeight));
        return tVec;
    }
}
