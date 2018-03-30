package com.v2;

import com.v2.surfaces.Surface;

public class Graphics2D<T> {
    private Surface<T> surface;
    private Shader<T,?> shader;

    public Graphics2D(Surface<T> surface, Shader<T,?> shader) {
        this.surface = surface;
        this.shader = shader;
    }

    public Surface<T> getSurface() {
        return surface;
    }

    public Shader<T, ?> getShader() {
        return shader;
    }

    public void setShader(Shader<T, ?> shader) {
        this.shader = shader;
    }

    public void clear(T clearColor) {
        surface.fill(clearColor);
    }

    public void fill() {
        int i = 0;
        for (int y = 0; y < surface.getHeight(); y++) {
            for (int x = 0; x < surface.getWidth(); x++) {
                surface.setLinear(i, shader.getColor((float)x / surface.getWidth(), (float)y / surface.getHeight()));
                i++;
            }
        }
    }

    public void plotPixel(int x, int y) {
        surface.setXY(x, y, shader.getColor(0, 0));
    }

    public void plotPixel(Point p) {
        plotPixel(p.getX(), p.getY());
    }

    public void drawLine(Point a, Point b) {
        drawLine(a.getX(), a.getY(), b.getX(), b.getY());
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
        if (shader == null) throw new RuntimeException();
        for (int py = y; py < y+h; py++) {
            for (int px = x; px < x+w; px++) {
                surface.setXY(px, py, shader.getColor((float)(px-x)/w, (float)(py-y)/h));
            }
        }
    }

    public void fillTriangle(Triangle<?> triangle) {
        Rectangle boundingBox = triangle.getBoundingBox(surface.getRect());
        int startX = boundingBox.getX(), endX = startX + boundingBox.getWidth();
        int startY = boundingBox.getY(), endY = startY + boundingBox.getHeight();
        Point p = new Point(0, 0);
        for (p.y = startY; p.y <= endY; p.y++) {
            for (p.x = startX; p.x <= endX; p.x++) {
                float[] bcScreen = Point.barycentric(p, triangle.getA().getPosition(), triangle.getB().getPosition(), triangle.getC().getPosition());
                if (!(bcScreen[0] < 0 || bcScreen[1] < 0 || bcScreen[2] < 0)) {
                    plotPixel(p);
                }
            }
        }
    }
}
