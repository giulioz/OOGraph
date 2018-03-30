package com.gzgr.vectormath;

public class Vector3 implements Vector {
    private Number x, y, z;

    public Vector3(double x, double y, double z) {
        this.x = new Double(x);
        this.y = new Double(y);
        this.z = new Double(z);
    }

    public Vector3(Number x, Number y, Number z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Number getX() {
        return x;
    }

    public void setX(Number x) {
        this.x = x;
    }

    public Number getY() {
        return y;
    }

    public void setY(Number y) {
        this.y = y;
    }

    public Number getZ() {
        return z;
    }

    public void setZ(Number z) {
        this.z = z;
    }

    @Override
    public int getDimensions() {
        return 3;
    }

    @Override
    public void setComponent(int i, Number value) {
        switch (i) {
            case 0:
                x = value;
                break;
            case 1:
                y = value;
                break;
            case 2:
                z = value;
                break;
            default:
                throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public Number getComponent(int i) {
        switch (i) {
            case 0:
                return x;
            case 1:
                return y;
            case 2:
                return z;
            default:
                throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public Vector clone() {
        return new Vector3(x, y, z);
    }
}
