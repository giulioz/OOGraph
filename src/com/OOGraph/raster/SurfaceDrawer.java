package com.OOGraph.raster;

import com.OOGraph.math.Point;
import com.OOGraph.math.Rectangle;
import com.OOGraph.primitives.meshes.Mesh;
import com.OOGraph.primitives.vertices.Vertex;
import com.OOGraph.raster.shaders.PixelShader;
import com.OOGraph.primitives.vertices.Triangle;
import com.OOGraph.math.Vector;

public class SurfaceDrawer<Tcolor> {
    protected Surface<Tcolor> surface;

    public SurfaceDrawer(Surface<Tcolor> surface) {
        this.surface = surface;
    }

    public Surface<Tcolor> getSurface() {
        return surface;
    }

    public void clear(Tcolor clearColor) {
        surface.fill(clearColor);
    }

    public void plotPixel(int x, int y, Tcolor color) {
        surface.setXY(x, y, color);
    }
    public void plotPixel(Point p, Tcolor color) {
        plotPixel(p.x, p.y, color);
    }

    public void drawLine(Point a, Point b, Tcolor color) {
        drawLine(a.x, a.y, b.x, b.y, color);
    }

    public void drawLine(int ax, int ay, int bx, int by, Tcolor color) {
        int dx =  Math.abs(bx - ax), sx = ax < bx ? 1 : -1;
        int dy = -Math.abs(by - ay), sy = ay < by ? 1 : -1;
        int err = dx + dy; /* error value e_xy */

        for (;;) {
            plotPixel(ax, ay, color);
            if (ax == bx && ay == by) break;
            float e2 = 2 * err; /* error value e_xy */
            if (e2 >= dy) { err += dy; ax += sx; } /* e_xy+e_x > 0 */
            if (e2 <= dx) { err += dx; ay += sy; } /* e_xy+e_y < 0 */
        }
    }

    public void drawRectangle(Rectangle rect, Tcolor color) {
        drawLine(rect.getUpperLeft(), rect.getUpperRight(), color);
        drawLine(rect.getUpperRight(), rect.getLowerRight(), color);
        drawLine(rect.getLowerRight(), rect.getLowerLeft(), color);
        drawLine(rect.getLowerLeft(), rect.getUpperLeft(), color);
    }

    public void drawRectangle(int x, int y, int w, int h, Tcolor color) {
        drawRectangle(new Rectangle(x, y, w, h), color);
    }

    public void fillRectangle(Rectangle rect, Tcolor color) {
        fillRectangle(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight(), color);
    }

    public void fillRectangle(int x, int y, int w, int h, Tcolor color) {
        for (int py = y; py < y+h; py++) {
            for (int px = x; px < x+w; px++) {
                surface.setXY(px, py, color);
            }
        }
    }
}
