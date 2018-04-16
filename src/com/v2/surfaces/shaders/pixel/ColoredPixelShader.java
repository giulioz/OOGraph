package com.v2.surfaces.shaders.pixel;

import com.v2.primitives.ColoredVertex;
import com.v2.surfaces.colors.ColorRGB24;
import com.v2.vectormath.Vector;

public class ColoredPixelShader implements PixelShader<ColorRGB24, Object, ColoredVertex> {
    @Override
    public Object getUniforms() {
        return null;
    }

    @Override
    public void setUniforms(Object data) { }

    @Override
    public ColorRGB24 getColor(float x, float y, ColoredVertex va, ColoredVertex vb, ColoredVertex vc, Vector barycentric) {
        Vector color = va.getColorVector().multiply(barycentric.getComponent(0))
                .sum(vb.getColorVector().multiply(barycentric.getComponent(1)))
                .sum(vc.getColorVector().multiply(barycentric.getComponent(2)));
        return new ColorRGB24(
                (int)(color.getComponent(0) * 255),
                (int)(color.getComponent(1) * 255),
                (int)(color.getComponent(2) * 255)
        );
    }
}
