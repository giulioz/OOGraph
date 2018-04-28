package com.OOGraph.raster.shaders;

import com.OOGraph.primitives.vertices.ColoredVertex;
import com.OOGraph.raster.colors.Color;
import com.OOGraph.raster.colors.ColorFactory;
import com.OOGraph.math.Vector;
import com.OOGraph.raster.surfaces.Surface;

public class ColoredPixelShader<Tpixel extends Color> implements PixelShader<Tpixel, ColoredVertex> {
    private ColorFactory<Tpixel> colorFactory;

    public ColoredPixelShader(Surface<Tpixel> surface) {
        this.colorFactory = surface.getColorFactory();
    }

    @Override
    public Tpixel getColor(float x, float y, ColoredVertex va, ColoredVertex vb, ColoredVertex vc, Vector barycentric) {
        Vector color = va.getColor().multiply(barycentric.get(0))
                .sum(vb.getColor().multiply(barycentric.get(1)))
                .sum(vc.getColor().multiply(barycentric.get(2)));
        return colorFactory.fromRGBVector(color);
    }
}
