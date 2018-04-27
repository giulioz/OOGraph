package com.OOGraph.primitives.vertices;

import com.OOGraph.math.MathHelper;
import com.OOGraph.math.Matrix;
import com.OOGraph.math.Rectangle;

public class Triangle<T extends Vertex> {
    private T a, b, c;

    private Triangle() {

    }

    public Triangle(T a, T b, T c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public T getA() {
        return a;
    }

    public T getB() {
        return b;
    }

    public T getC() {
        return c;
    }

    public Rectangle getBoundingBox(Rectangle clamp) {
        int bboxminx = clamp.getWidth();
        int bboxmaxx = 0;
        int bboxminy = clamp.getHeight();
        int bboxmaxy = 0;

        bboxminx = MathHelper.max(0, MathHelper.min(bboxminx, a.getPoint().x));
        bboxmaxx = MathHelper.min(clamp.getWidth(), MathHelper.max(bboxmaxx, a.getPoint().x));
        bboxminy = MathHelper.max(0, MathHelper.min(bboxminy, a.getPoint().y));
        bboxmaxy = MathHelper.min(clamp.getHeight(), MathHelper.max(bboxmaxy, a.getPoint().y));

        bboxminx = MathHelper.max(0, MathHelper.min(bboxminx, b.getPoint().x));
        bboxmaxx = MathHelper.min(clamp.getWidth(), MathHelper.max(bboxmaxx, b.getPoint().x));
        bboxminy = MathHelper.max(0, MathHelper.min(bboxminy, b.getPoint().y));
        bboxmaxy = MathHelper.min(clamp.getHeight(), MathHelper.max(bboxmaxy, b.getPoint().y));

        bboxminx = MathHelper.max(0, MathHelper.min(bboxminx, c.getPoint().x));
        bboxmaxx = MathHelper.min(clamp.getWidth(), MathHelper.max(bboxmaxx, c.getPoint().x));
        bboxminy = MathHelper.max(0, MathHelper.min(bboxminy, c.getPoint().y));
        bboxmaxy = MathHelper.min(clamp.getHeight(), MathHelper.max(bboxmaxy, c.getPoint().y));

        return new Rectangle(bboxminx, bboxminy, bboxmaxx - bboxminx, bboxmaxy - bboxminy);
    }

    public Triangle<T> transformMatrix(Matrix transform) {
        Triangle<T> tcopy = new Triangle<>();
        tcopy.a = (T) a.transformMatrix(transform);
        tcopy.b = (T) b.transformMatrix(transform);
        tcopy.c = (T) c.transformMatrix(transform);
        return tcopy;
    }
}
