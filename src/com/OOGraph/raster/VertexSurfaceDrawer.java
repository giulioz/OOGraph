package com.OOGraph.raster;

import com.OOGraph.math.Point;
import com.OOGraph.math.Rectangle;
import com.OOGraph.math.Vector;
import com.OOGraph.primitives.meshes.Mesh;
import com.OOGraph.primitives.vertices.Triangle;
import com.OOGraph.primitives.vertices.Vertex;
import com.OOGraph.raster.shaders.PixelShader;
import com.OOGraph.raster.surfaces.Surface;

public class VertexSurfaceDrawer<Tcolor, Tvertex extends Vertex> extends SurfaceDrawer<Tcolor> {
    private PixelShader<Tcolor, Tvertex> pixelShader;

    public VertexSurfaceDrawer(Surface<Tcolor> surface, PixelShader<Tcolor, Tvertex> pixelShader) {
        super(surface);
        this.pixelShader = pixelShader;
    }

    public PixelShader<Tcolor, Tvertex> getPixelShader() {
        return pixelShader;
    }

    public void setPixelShader(PixelShader<Tcolor, Tvertex> pixelShader) {
        this.pixelShader = pixelShader;
    }

    public void fillTriangle(Triangle<Tvertex> triangle) {
        Rectangle boundingBox = triangle.getBoundingBox(surface.getRect());
        int startX = boundingBox.getX(), endX = startX + boundingBox.getWidth();
        int startY = boundingBox.getY(), endY = startY + boundingBox.getHeight();
        Point p = new Point(0, 0);
        for (p.y = startY; p.y <= endY; p.y++) {
            for (p.x = startX; p.x <= endX; p.x++) {
                Vector bcScreen = Point.barycentric(p, triangle.getA().getPoint(), triangle.getB().getPoint(), triangle.getC().getPoint());
                if (!(bcScreen.get(0) < 0 || bcScreen.get(1) < 0 || bcScreen.get(2) < 0)) {
                    surface.setXY(p.x, p.y, pixelShader.getColor(p.x, p.y, triangle.getA(), triangle.getB(), triangle.getC(), bcScreen));
                }
            }
        }
    }

    public void fillTriangle(Triangle<Tvertex> triangle, ZBuffer zBuffer) {
        Rectangle boundingBox = triangle.getBoundingBox(surface.getRect());
        int startX = boundingBox.getX(), endX = startX + boundingBox.getWidth();
        int startY = boundingBox.getY(), endY = startY + boundingBox.getHeight();
        Point p = new Point(0, 0);
        for (p.y = startY; p.y <= endY; p.y++) {
            for (p.x = startX; p.x <= endX; p.x++) {
                Vector bcScreen = Point.barycentric(p, triangle.getA().getPoint(), triangle.getB().getPoint(), triangle.getC().getPoint());
                Vector internalPoint = triangle.getA().getPosition().multiply(bcScreen.get(0))
                        .sum(triangle.getB().getPosition().multiply(bcScreen.get(1)))
                        .sum(triangle.getC().getPosition().multiply(bcScreen.get(2)));
                if (!(bcScreen.get(0) < 0 || bcScreen.get(1) < 0 || bcScreen.get(2) < 0) && zBuffer.zWrite(p, internalPoint.get(2))) {
                    surface.setXY(p.x, p.y, pixelShader.getColor(p.x, p.y, triangle.getA(), triangle.getB(), triangle.getC(), bcScreen));
                }
            }
        }
    }

    public void drawMesh(Mesh<Tvertex> mesh) {
        for (Triangle<Tvertex> t : mesh) {
            fillTriangle(t);
        }
    }

    public void drawMesh(Mesh<Tvertex> mesh, ZBuffer zBuffer) {
        for (Triangle<Tvertex> t : mesh) {
            fillTriangle(t, zBuffer);
        }
    }
}
