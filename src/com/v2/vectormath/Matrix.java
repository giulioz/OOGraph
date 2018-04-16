package com.v2.vectormath;

public class Matrix {
    private int width, height;
    private float[][] mat;

    public Matrix(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Matrix(int width, int height, float[][] mat) {
        this.width = width;
        this.height = height;
        this.mat = mat;
    }
}
