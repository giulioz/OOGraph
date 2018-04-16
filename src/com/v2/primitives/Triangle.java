package com.v2.primitives;

import com.v2.MathHelper;
import com.v2.Rectangle;
import com.v2.vectormath.Vector;

public class Triangle<T extends Vertex> {
    private T a, b, c;

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
}
