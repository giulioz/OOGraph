package com.OOGraph.raster.shaders;

import com.OOGraph.math.MathHelper;
import com.OOGraph.primitives.vertices.ColoredNormalVertex;
import com.OOGraph.raster.colors.Color;
import com.OOGraph.raster.colors.ColorFactory;
import com.OOGraph.raster.colors.ColorRGB24;
import com.OOGraph.math.Vector;
import com.OOGraph.raster.surfaces.Surface;

public class ColoredNormalPixelShader<T extends Color> implements PixelShader<T, ColoredNormalVertex> {
    Vector lightDir;
    private ColorFactory<T> colorFactory;

    public ColoredNormalPixelShader(Surface<T> surface, Vector lightDir) {
        this.colorFactory = surface.getColorFactory();
        this.lightDir = lightDir;
    }

    @Override
    public T getColor(float x, float y, ColoredNormalVertex va, ColoredNormalVertex vb, ColoredNormalVertex vc, Vector barycentric) {
        Vector color = va.getColor().multiply(barycentric.get(0))
                .sum(vb.getColor().multiply(barycentric.get(1)))
                .sum(vc.getColor().multiply(barycentric.get(2)));
        float intensity = MathHelper.clamp(va.getNormal().dot(lightDir), 0.0f, 1.0f);
        return colorFactory.fromRGBVector(color.multiply(intensity));
    }
}
