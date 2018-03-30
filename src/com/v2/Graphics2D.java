package com.v2;

import com.v2.surfaces.Surface;

public class Graphics2D<T> {
    private Surface<T> surface;
    private Shader<T, ?> shader;

    public Graphics2D(Surface<T> surface) {
        this.surface = surface;
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

    public void plotPixel(Point p, T color) {
        surface.setXY(p.getX(), p.getY(), color);
    }

    public void plotPixel(int x, int y, T color) {
        surface.setXY(x, y, color);
    }

    public void drawLine(Point a, Point b, T color) {
        drawLine(a.getX(), a.getY(), b.getX(), b.getY(), color);
    }

    public void drawLine(int ax, int ay, int bx, int by, T color) {
        int dx =  Math.abs(bx - ax), sx = ax < bx ? 1 : -1;
        int dy = -Math.abs(by - ay), sy = ay < by ? 1 : -1;
        int err = dx + dy; /* error value e_xy */

        for (;;) {  /* loop */
            plotPixel(ax, ay, color);
            if (ax == bx && ay == by) break;
            float e2 = 2 * err; /* error value e_xy */
            if (e2 >= dy) { err += dy; ax += sx; } /* e_xy+e_x > 0 */
            if (e2 <= dx) { err += dx; ay += sy; } /* e_xy+e_y < 0 */
        }
    }

    public void drawRectangle(Rectangle rect, T color) {
        drawLine(rect.getUpperLeft(), rect.getUpperRight(), color);
        drawLine(rect.getUpperRight(), rect.getLowerRight(), color);
        drawLine(rect.getLowerRight(), rect.getLowerLeft(), color);
        drawLine(rect.getLowerLeft(), rect.getUpperLeft(), color);
    }

    public void drawRectangle(int x, int y, int w, int h, T color) {
        drawRectangle(new Rectangle(x, y, w, h), color);
    }

    public void fillRectangle(Rectangle rect, T color) {
        fillRectangle(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight(), color);
    }

    public void fillRectangle(int x, int y, int w, int h, T color) {
        for (int py = y; py < y+h; py++) {
            for (int px = x; px < x+w; px++) {
                plotPixel(px, py, color);
            }
        }
    }

    public void fillRectangle(int x, int y, int w, int h) {
        if (shader == null) throw new RuntimeException();
        for (int py = y; py < y+h; py++) {
            for (int px = x; px < x+w; px++) {
                surface.setXY(px, py, shader.getColor(new Point(px-x, py-y)));
            }
        }
    }

    /*public void drawFace<TVertexFormat>(IFace<TVertexFormat, T> face) where TVertexFormat : IVertex2D{
        var bboxmin = new PointI(_surface.Size.Width - 1, _surface.Size.Height - 1);
        var bboxmax = new PointI(0, 0);
        var clamp = new PointI(_surface.Size.Width - 1, _surface.Size.Height - 1);

        bboxmin.X = MathHelper.Max(0, MathHelper.Min(bboxmin.X, face.A.Position.X));
        bboxmax.X = MathHelper.Min(clamp.X, MathHelper.Max(bboxmax.X, face.A.Position.X));
        bboxmin.Y = MathHelper.Max(0, MathHelper.Min(bboxmin.Y, face.A.Position.Y));
        bboxmax.Y = MathHelper.Min(clamp.Y, MathHelper.Max(bboxmax.Y, face.A.Position.Y));

        bboxmin.X = MathHelper.Max(0, MathHelper.Min(bboxmin.X, face.B.Position.X));
        bboxmax.X = MathHelper.Min(clamp.X, MathHelper.Max(bboxmax.X, face.B.Position.X));
        bboxmin.Y = MathHelper.Max(0, MathHelper.Min(bboxmin.Y, face.B.Position.Y));
        bboxmax.Y = MathHelper.Min(clamp.Y, MathHelper.Max(bboxmax.Y, face.B.Position.Y));

        bboxmin.X = MathHelper.Max(0, MathHelper.Min(bboxmin.X, face.C.Position.X));
        bboxmax.X = MathHelper.Min(clamp.X, MathHelper.Max(bboxmax.X, face.C.Position.X));
        bboxmin.Y = MathHelper.Max(0, MathHelper.Min(bboxmin.Y, face.C.Position.Y));
        bboxmax.Y = MathHelper.Min(clamp.Y, MathHelper.Max(bboxmax.Y, face.C.Position.Y));

        var p = new PointI(0, 0);
        for (p.Y=bboxmin.Y; p.Y<=bboxmax.Y; p.Y++) {
            for (p.X=bboxmin.X; p.X<=bboxmax.X; p.X++) {
                var bcScreen = PointI.Barycentric(p, face.A.Position, face.B.Position, face.C.Position);
                if (!(bcScreen.X < 0 || bcScreen.Y < 0 || bcScreen.Z < 0)) {
                    plotPixel(p, face.Shade(bcScreen));
                }
            }
        }
    }*/
}
