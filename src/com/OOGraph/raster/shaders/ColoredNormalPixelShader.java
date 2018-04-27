package com.OOGraph.raster.shaders;

import com.OOGraph.math.MathHelper;
import com.OOGraph.primitives.vertices.ColoredNormalVertex;
import com.OOGraph.raster.colors.ColorRGB24;
import com.OOGraph.math.Vector;

public class ColoredNormalPixelShader implements PixelShader<ColorRGB24, ColoredNormalVertex> {
    Vector lightDir;

    public ColoredNormalPixelShader(Vector lightDir) {
        this.lightDir = lightDir;
    }

    @Override
    public ColorRGB24 getColor(float x, float y, ColoredNormalVertex va, ColoredNormalVertex vb, ColoredNormalVertex vc, Vector barycentric) {
        Vector color = va.getColor().multiply(barycentric.get(0))
                .sum(vb.getColor().multiply(barycentric.get(1)))
                .sum(vc.getColor().multiply(barycentric.get(2)));
        float intensity = MathHelper.clamp(va.getNormal().dot(lightDir), 0.0f, 1.0f);
        return new ColorRGB24(
                (int)(color.get(0) * intensity * 255),
                (int)(color.get(1) * intensity * 255),
                (int)(color.get(2) * intensity * 255)
        );
    }
}
