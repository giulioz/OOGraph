package com.OOGraph.raster.shaders;

import com.OOGraph.math.MathHelper;
import com.OOGraph.math.Vector;
import com.OOGraph.primitives.vertices.TexturedNormalVertex;
import com.OOGraph.raster.Surface;
import com.OOGraph.raster.colors.ColorRGB24;

public class TexturedNormalPixelShader implements PixelShader<ColorRGB24, TexturedNormalVertex> {
    private Vector lightDir;
    private Surface<ColorRGB24> texture;

    public TexturedNormalPixelShader(Vector lightDir, Surface<ColorRGB24> texture) {
        this.lightDir = lightDir;
        this.texture = texture;
    }

    @Override
    public ColorRGB24 getColor(float x, float y, TexturedNormalVertex va, TexturedNormalVertex vb, TexturedNormalVertex vc, Vector barycentric) {
        Vector texCoord = va.getUv().multiply(barycentric.get(0))
                .sum(vb.getUv().multiply(barycentric.get(1)))
                .sum(vc.getUv().multiply(barycentric.get(2)))
                .wrap(new Vector(1,1))
                .multiply(new Vector(texture.getWidth() - 1, texture.getHeight() - 1));
        Vector normal = va.getNormal().multiply(barycentric.get(0))
                .sum(vb.getNormal().multiply(barycentric.get(1)))
                .sum(vc.getNormal().multiply(barycentric.get(2))).normalize();
        ColorRGB24 color = texture.getXY((int)texCoord.get(0), (int)texCoord.get(1));
        float intensity = MathHelper.clamp(normal.dot(lightDir.normalize()), 0.0f, 1.0f);
        return new ColorRGB24(
                (byte)(MathHelper.getUnsignedByte(color.getR()) * intensity),
                (byte)(MathHelper.getUnsignedByte(color.getG()) * intensity),
                (byte)(MathHelper.getUnsignedByte(color.getB()) * intensity)
        );
    }
}
