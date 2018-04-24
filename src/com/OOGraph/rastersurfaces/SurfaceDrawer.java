package com.OOGraph.rastersurfaces;

import com.OOGraph.math.Point;
import com.OOGraph.math.Rectangle;
import com.OOGraph.primitives.Mesh;
import com.OOGraph.primitives.Vertex;
import com.OOGraph.rastersurfaces.shaders.PixelShader;
import com.OOGraph.primitives.Triangle;
import com.OOGraph.math.Vector;

public class SurfaceDrawer<Tcolor,Tvertex extends Vertex> {
    private Surface<Tcolor> surface;
    private PixelShader<Tcolor,Tvertex> pixelShader;

    public SurfaceDrawer(Surface<Tcolor> surface, PixelShader<Tcolor,Tvertex> pixelShader) {
        this.surface = surface;
        this.pixelShader = pixelShader;
    }

    public Surface<Tcolor> getSurface() {
        return surface;
    }

    public PixelShader<Tcolor,Tvertex> getPixelShader() {
        return pixelShader;
    }
    public void setPixelShader(PixelShader<Tcolor,Tvertex> pixelShader) {
        this.pixelShader = pixelShader;
    }

    public void clear(Tcolor clearColor) {
        surface.fill(clearColor);
    }

    public void fill() {
        int i = 0;
        for (int y = 0; y < surface.getHeight(); y++) {
            for (int x = 0; x < surface.getWidth(); x++) {
                surface.setLinear(i, pixelShader.getColor(x, y, null, null, null, null));
                i++;
            }
        }
    }

    public void plotPixel(int x, int y) {
        plotPixel(x, y, 0, 0);
    }
    public void plotPixel(Point p) {
        plotPixel(p.x, p.y);
    }
    public void plotPixel(int x, int y, int sx, int sy) {
        surface.setXY(x, y, pixelShader.getColor(sx, sy, null, null, null, null));
    }
    public void plotPixel(Point p, int sx, int sy) {
        plotPixel(p.x, p.y, sx, sy);
    }

    public void drawLine(Point a, Point b) {
        drawLine(a.x, a.y, b.x, b.y);
    }

    public void drawLine(int ax, int ay, int bx, int by) {
        int dx =  Math.abs(bx - ax), sx = ax < bx ? 1 : -1;
        int dy = -Math.abs(by - ay), sy = ay < by ? 1 : -1;
        int err = dx + dy; /* error value e_xy */

        for (;;) {
            plotPixel(ax, ay);
            if (ax == bx && ay == by) break;
            float e2 = 2 * err; /* error value e_xy */
            if (e2 >= dy) { err += dy; ax += sx; } /* e_xy+e_x > 0 */
            if (e2 <= dx) { err += dx; ay += sy; } /* e_xy+e_y < 0 */
        }
    }

    public void drawRectangle(Rectangle rect) {
        drawLine(rect.getUpperLeft(), rect.getUpperRight());
        drawLine(rect.getUpperRight(), rect.getLowerRight());
        drawLine(rect.getLowerRight(), rect.getLowerLeft());
        drawLine(rect.getLowerLeft(), rect.getUpperLeft());
    }

    public void drawRectangle(int x, int y, int w, int h) {
        drawRectangle(new Rectangle(x, y, w, h));
    }

    public void fillRectangle(Rectangle rect) {
        fillRectangle(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
    }

    public void fillRectangle(int x, int y, int w, int h) {
        for (int py = y; py < y+h; py++) {
            for (int px = x; px < x+w; px++) {
                surface.setXY(px, py, pixelShader.getColor(px, py, null, null, null, null));
            }
        }
    }

    public void fillTriangle(Triangle<Tvertex> triangle) {
        Triangle<Tvertex> pT = triangle;
        Rectangle boundingBox = pT.getBoundingBox(surface.getRect());
        int startX = boundingBox.getX(), endX = startX + boundingBox.getWidth();
        int startY = boundingBox.getY(), endY = startY + boundingBox.getHeight();
        Point p = new Point(0, 0);
        for (p.y = startY; p.y <= endY; p.y++) {
            for (p.x = startX; p.x <= endX; p.x++) {
                Vector bcScreen = Point.barycentric(p, pT.getA().getPoint(), pT.getB().getPoint(), pT.getC().getPoint());
                if (!(bcScreen.get(0) < 0 || bcScreen.get(1) < 0 || bcScreen.get(2) < 0)) {
                    surface.setXY(p.x, p.y, pixelShader.getColor(p.x, p.y, pT.getA(), pT.getB(), pT.getC(), bcScreen));
                }
            }
        }
    }

    public void drawMesh(Mesh<Tvertex> mesh) {
        for (Triangle<Tvertex> t : mesh) {
            fillTriangle(t);
        }
    }
}
