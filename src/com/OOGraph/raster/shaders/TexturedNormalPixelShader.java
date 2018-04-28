package com.OOGraph.raster.shaders;

import com.OOGraph.math.MathHelper;
import com.OOGraph.math.Vector;
import com.OOGraph.primitives.vertices.TexturedNormalVertex;
import com.OOGraph.raster.colors.Color;
import com.OOGraph.raster.colors.ColorFactory;
import com.OOGraph.raster.surfaces.Surface;

public class TexturedNormalPixelShader<T extends Color> implements PixelShader<T, TexturedNormalVertex> {
    private Vector lightDir;
    private Surface<T> texture;
    private ColorFactory<T> colorFactory;

    public TexturedNormalPixelShader(Surface<T> surface, Vector lightDir, Surface<T> texture) {
        this.colorFactory = surface.getColorFactory();
        this.lightDir = lightDir;
        this.texture = texture;
    }

    @Override
    public T getColor(float x, float y, TexturedNormalVertex va, TexturedNormalVertex vb, TexturedNormalVertex vc, Vector barycentric) {
        Vector texCoord = va.getUv().multiply(barycentric.get(0))
                .sum(vb.getUv().multiply(barycentric.get(1)))
                .sum(vc.getUv().multiply(barycentric.get(2)))
                .wrap(new Vector(1,1))
                .multiply(new Vector(texture.getWidth() - 1, texture.getHeight() - 1));
        Vector normal = va.getNormal().multiply(barycentric.get(0))
                .sum(vb.getNormal().multiply(barycentric.get(1)))
                .sum(vc.getNormal().multiply(barycentric.get(2))).normalize();
        T color = texture.getXY((int)texCoord.get(0), (int)texCoord.get(1));
        float intensity = MathHelper.clamp(normal.dot(lightDir.normalize()), 0.0f, 1.0f);
        return colorFactory.fromRGBVector(color.getVector().multiply(intensity));
    }
}
