package com.v2.surfaces.shaders.pixel;

import com.v2.primitives.ColoredVertex;
import com.v2.surfaces.colors.ColorRGB24;
import com.v2.vectormath.Vector;

public class ColoredPixelShader implements PixelShader<ColorRGB24, ColoredVertex> {

    @Override
    public ColorRGB24 getColor(float x, float y, ColoredVertex va, ColoredVertex vb, ColoredVertex vc, Vector barycentric) {
        Vector color = va.getColorVector().multiply(barycentric.get(0))
                .sum(vb.getColorVector().multiply(barycentric.get(1)))
                .sum(vc.getColorVector().multiply(barycentric.get(2)));
        return new ColorRGB24(
                (int)(color.get(0) * 255),
                (int)(color.get(1) * 255),
                (int)(color.get(2) * 255)
        );
    }
}
