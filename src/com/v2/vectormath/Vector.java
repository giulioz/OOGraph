package com.v2.vectormath;

public class Vector {
    private int dimensions;
    private float[] vect;

    public Vector(Vector copy) {
        this.dimensions = copy.dimensions;
        this.vect = new float[copy.dimensions];
        for (int i = 0; i < copy.dimensions; i++) {
            this.vect[i] = copy.vect[i];
        }
    }

    public Vector(int dimensions, float[] vect) {
        this.dimensions = dimensions;
        this.vect = vect;
    }

    public Vector(float x) {
        this.dimensions = 1;
        this.vect = new float[dimensions];
        vect[0] = x;
    }

    public Vector(float x, float y) {
        this.dimensions = 2;
        this.vect = new float[dimensions];
        vect[0] = x;
        vect[1] = y;
    }

    public Vector(float x, float y, float z) {
        this.dimensions = 3;
        this.vect = new float[dimensions];
        vect[0] = x;
        vect[1] = y;
        vect[2] = z;
    }

    public Vector(float x, float y, float z, float w) {
        this.dimensions = 5;
        this.vect = new float[dimensions];
        vect[0] = x;
        vect[1] = y;
        vect[2] = z;
        vect[3] = w;
    }

    public int getDimensions() { return dimensions; }
    public float getComponent(int i) { return vect[i]; }
    private void setComponent(int i, float value) { vect[i] = value; }

    public float dot(Vector b) {
        float sum = 0;
        for (int i = 0; i < getDimensions(); i++) {
            sum += getComponent(i) * b.getComponent(i);
        }
        return sum;
    }

    public Vector min(Vector b) {
        Vector out = new Vector(this);
        for (int i = 0; i < getDimensions(); i++) {
            if (getComponent(i) < (this.getComponent(i)))
                out.setComponent(i, getComponent(i));
        }
        return out;
    }

    public Vector max(Vector b) {
        Vector out = new Vector(this);
        for (int i = 0; i < getDimensions(); i++) {
            if (getComponent(i) > (this.getComponent(i)))
                this.setComponent(i, getComponent(i));
        }
        return this;
    }

    public Vector clamp(Vector min, Vector max) {
        for (int i = 0; i < getDimensions(); i++) {
            if (getComponent(i) < (min.getComponent(i)))
                this.setComponent(i, min.getComponent(i));
            else if (getComponent(i) > (max.getComponent(i)))
                this.setComponent(i, max.getComponent(i));
        }
        return this;
    }


    public Vector normalize() {
        return this.divide(this.length());
    }

    public float distance(Vector b) {
        return (this.subtract(b)).length();
    }

    public float lengthSquared() {
        return dot(this);
    }

    public float length() {
        return (float)Math.sqrt(lengthSquared());
    }

    public Vector sum(float scalar) {
        Vector out = new Vector(this);
        for (int i = 0; i < getDimensions(); i++) {
            out.setComponent(i, getComponent(i) + scalar);
        }
        return out;
    }

    public Vector sum(Vector vector) {
        Vector out = new Vector(this);
        for (int i = 0; i < getDimensions(); i++) {
            out.setComponent(i, getComponent(i) + vector.getComponent(i));
        }
        return out;
    }

    public Vector subtract(float scalar) {
        Vector out = new Vector(this);
        for (int i = 0; i < getDimensions(); i++) {
            out.setComponent(i, getComponent(i) - scalar);
        }
        return out;
    }

    public Vector subtract(Vector vector) {
        Vector out = new Vector(this);
        for (int i = 0; i < getDimensions(); i++) {
            out.setComponent(i, getComponent(i) - vector.getComponent(i));
        }
        return out;
    }

    public Vector negate() {
        Vector out = new Vector(this);
        for (int i = 0; i < getDimensions(); i++) {
            out.setComponent(i, -getComponent(i));
        }
        return out;
    }

    public Vector multiply(float scalar) {
        Vector out = new Vector(this);
        for (int i = 0; i < getDimensions(); i++) {
            out.setComponent(i, getComponent(i) * scalar);
        }
        return out;
    }

    public Vector multiply(Vector vector) {
        Vector out = new Vector(this);
        for (int i = 0; i < getDimensions(); i++) {
            out.setComponent(i, getComponent(i) * vector.getComponent(i));
        }
        return out;
    }

    public Vector divide(float scalar) {
        Vector out = new Vector(this);
        for (int i = 0; i < getDimensions(); i++) {
            out.setComponent(i, getComponent(i) / scalar);
        }
        return out;
    }

    public Vector divide(Vector vector) {
        Vector out = new Vector(this);
        for (int i = 0; i < getDimensions(); i++) {
            out.setComponent(i, getComponent(i) / vector.getComponent(i));
        }
        return out;
    }

    public boolean equals(Vector vector) {
        boolean flag = true;
        for (int i = 0; i < getDimensions(); i++) {
            flag &= getComponent(i) == vector.getComponent(i);
        }
        return flag;
    }

    public boolean greaterThan(Vector vector) {
        boolean flag = true;
        for (int i = 0; i < getDimensions(); i++) {
            flag &= getComponent(i) > vector.getComponent(i);
        }
        return flag;
    }

    public boolean lessThan(Vector vector) {
        boolean flag = true;
        for (int i = 0; i < getDimensions(); i++) {
            flag &= getComponent(i) < vector.getComponent(i);
        }
        return flag;
    }
}
