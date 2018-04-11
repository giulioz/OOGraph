package com.v2;

public class Point {
    public int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static float[] barycentric(Point P, Point a, Point b, Point c) {
        float[] _a = new float[]{c.x - a.x, b.x - a.x, a.x - P.x};
        float[] _b = new float[]{c.y - a.y, b.y - a.y, a.y - P.y};
        float vz = _a[0] * _b[1] - _a[1] * _b[0];
        if (Math.abs(vz) < 1) { // triangle is degenerate
            return new float[]{-1, 1, 1};
        }
        float vx = _a[1] * _b[2] - _a[2] * _b[1];
        float vy = _a[2] * _b[0] - _a[0] * _b[2];
        float x = 1.0f - (vx + vy) / vz;
        float y = vy / vz;
        float z = vx / vz;
        return new float[]{x, y, z};
    }

    public static Point negate(Point b) {
        return new Point(-b.x,-b.y);
    }

    public static Point sum(Point a, Point b) {
        return new Point(a.x+b.x,a.y+b.y);
    }

    public static Point subtract(Point a, Point b) {
        return new Point(a.x-b.x,a.y-b.y);
    }

    public static Point multiply(Point a, Point b) {
        return new Point(a.x*b.x,a.y*b.y);
    }

    public static Point divide(Point a, Point b) {
        return new Point(a.x/b.x,a.y/b.y);
    }

    public static Point sum(Point a, int b) {
        return new Point(a.x+b,a.y+b);
    }

    public static Point subtract(Point a, int b) {
        return new Point(a.x-b,a.y-b);
    }

    public static Point multiply(Point a, int b) {
        return new Point(a.x*b,a.y*b);
    }

    public static Point divide(Point a, int b) {
        return new Point(a.x/b,a.y/b);
    }
}
