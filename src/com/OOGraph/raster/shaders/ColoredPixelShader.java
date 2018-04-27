package com.OOGraph.raster.shaders;

import com.OOGraph.primitives.vertices.ColoredVertex;
import com.OOGraph.raster.colors.ColorRGB24;
import com.OOGraph.math.Vector;

public class ColoredPixelShader implements PixelShader<ColorRGB24, ColoredVertex> {

    @Override
    public ColorRGB24 getColor(float x, float y, ColoredVertex va, ColoredVertex vb, ColoredVertex vc, Vector barycentric) {
        Vector color = va.getColor().multiply(barycentric.get(0))
                .sum(vb.getColor().multiply(barycentric.get(1)))
                .sum(vc.getColor().multiply(barycentric.get(2)));
        return new ColorRGB24(
                (int)(color.get(0) * 255),
                (int)(color.get(1) * 255),
                (int)(color.get(2) * 255)
        );
    }
}
