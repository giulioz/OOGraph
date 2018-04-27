package com.OOGraph.math;

public class Matrix {
    protected int rows, cols;
    protected float[][] mat;

    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.mat = new float[rows][cols];
        this.fillIdentity();
    }

    public Matrix(int rows, int cols, float[][] mat) {
        this.rows = rows;
        this.cols = cols;
        this.mat = mat;
    }

    private void fillIdentity() {
        for (int i = 0; i < rows && i < cols; i++) {
            mat[i][i] = 1.0f;
        }
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public float get(int r, int c) {
        return mat[r][c];
    }

    public Matrix multiply(Matrix other)
    {
        int aRows = this.mat.length;
        int aColumns = this.mat[0].length;
        int bRows = other.mat.length;
        int bColumns = other.mat[0].length;

        if (aColumns != bRows) {
            throw new IllegalArgumentException();
        }

        Matrix result = new Matrix(aRows, bColumns);
        for (int i = 0; i < aRows; i++) {
            for (int j = 0; j < bColumns; j++) {
                result.mat[i][j] = 0.0f;
            }
        }

        for (int i = 0; i < aRows; i++) { // aRow
            for (int j = 0; j < bColumns; j++) { // bColumn
                for (int k = 0; k < aColumns; k++) { // aColumn
                    result.mat[i][j] += this.mat[i][k] * other.mat[k][j];
                }
            }
        }

        return result;
    }

    public static Matrix createTranslation(int r, int c, Vector translation) {
        Matrix m = new Matrix(r, c);
        for (int i = 0; i < translation.getDimensions() && i < r; i++) {
            m.mat[i][c - 1] = translation.get(i);
        }
        return m;
    }

    public static Matrix createScale(int r, int c, Vector scale) {
        Matrix m = new Matrix(r, c);
        for (int i = 0; i < scale.getDimensions() && i < r && i < c; i++) {
            m.mat[i][i] = scale.get(i);
        }
        return m;
    }

    public static Matrix createScale(int r, int c, float scale) {
        Matrix m = new Matrix(r, c);
        for (int i = 0; i < r && i < c; i++) {
            m.mat[i][i] = scale;
        }
        return m;
    }

    public static Matrix createRotationX_4x4(float radians) {
        Matrix result = new Matrix(4, 4);

        float cos = (float)Math.cos(radians);
        float sin = (float)Math.sin(radians);

        result.mat[0][0] = 1.0f;
        result.mat[0][1] = 0.0f;
        result.mat[0][2] = 0.0f;
        result.mat[0][3] = 0.0f;

        result.mat[1][0] = 0.0f;
        result.mat[1][1] = cos;
        result.mat[1][2] = -sin;
        result.mat[1][3] = 0.0f;

        result.mat[2][0] = 0.0f;
        result.mat[2][1] = sin;
        result.mat[2][2] = -cos;
        result.mat[2][3] = 0.0f;

        result.mat[3][0] = 0.0f;
        result.mat[3][1] = 0.0f;
        result.mat[3][2] = 0.0f;
        result.mat[3][3] = 1.0f;

        return result;
    }

    public static Matrix createRotationY_4x4(float radians) {
        Matrix result = new Matrix(4, 4);

        float cos = (float)Math.cos(radians);
        float sin = (float)Math.sin(radians);

        result.mat[0][0] = cos;
        result.mat[0][1] = 0.0f;
        result.mat[0][2] = sin;
        result.mat[0][3] = 0.0f;

        result.mat[1][0] = 0.0f;
        result.mat[1][1] = 1.0f;
        result.mat[1][2] = 0.0f;
        result.mat[1][3] = 0.0f;

        result.mat[2][0] = -sin;
        result.mat[2][1] = 0.0f;
        result.mat[2][2] = cos;
        result.mat[2][3] = 0.0f;

        result.mat[3][0] = 0.0f;
        result.mat[3][1] = 0.0f;
        result.mat[3][2] = 0.0f;
        result.mat[3][3] = 1.0f;

        return result;
    }

    public static Matrix createRotationZ_4x4(float radians) {
        Matrix result = new Matrix(4, 4);

        float cos = (float)Math.cos(radians);
        float sin = (float)Math.sin(radians);

        result.mat[0][0] = cos;
        result.mat[0][1] = -sin;
        result.mat[0][2] = 0.0f;
        result.mat[0][3] = 0.0f;

        result.mat[1][0] = sin;
        result.mat[1][1] = cos;
        result.mat[1][2] = 0.0f;
        result.mat[1][3] = 0.0f;

        result.mat[2][0] = 0.0f;
        result.mat[2][1] = 0.0f;
        result.mat[2][2] = 1.0f;
        result.mat[2][3] = 0.0f;

        result.mat[3][0] = 0.0f;
        result.mat[3][1] = 0.0f;
        result.mat[3][2] = 0.0f;
        result.mat[3][3] = 1.0f;

        return result;
    }

    public static Matrix createLookAt_4x4(Vector cameraPosition, Vector cameraTarget, Vector cameraUpVector) {
        Vector zaxis = cameraPosition.subtract(cameraTarget).normalize();
        Vector xaxis = cameraUpVector.cross(zaxis).normalize();
        Vector yaxis = zaxis.cross(xaxis);

        Matrix result = new Matrix(4, 4);

        result.mat[0][0] = xaxis.get(0);
        result.mat[0][1] = xaxis.get(1);
        result.mat[0][2] = xaxis.get(2);
        result.mat[0][3] = 0.0f;
        result.mat[1][0] = yaxis.get(0);
        result.mat[1][1] = yaxis.get(1);
        result.mat[1][2] = yaxis.get(2);
        result.mat[1][3] = 0.0f;
        result.mat[2][0] = zaxis.get(0);
        result.mat[2][1] = zaxis.get(1);
        result.mat[2][2] = zaxis.get(2);
        result.mat[2][3] = 0.0f;
        result.mat[3][0] = -xaxis.dot(cameraPosition);
        result.mat[3][1] = -yaxis.dot(cameraPosition);
        result.mat[3][2] = -zaxis.dot(cameraPosition);
        result.mat[3][3] = 1.0f;

        return result;
    }

    public static Matrix createWorld_4x4(Vector position, Vector forward, Vector up) {
        Vector zaxis = forward.negate().normalize();
        Vector xaxis = up.cross(zaxis).normalize();
        Vector yaxis = zaxis.cross(xaxis);

        Matrix result = new Matrix(4, 4);

        result.mat[0][0] = xaxis.get(0);
        result.mat[0][1] = yaxis.get(0);
        result.mat[0][2] = zaxis.get(0);
        result.mat[0][3] = 0.0f;
        result.mat[1][0] = xaxis.get(1);
        result.mat[1][1] = yaxis.get(1);
        result.mat[1][2] = zaxis.get(1);
        result.mat[1][3] = 0.0f;
        result.mat[2][0] = xaxis.get(2);
        result.mat[2][1] = yaxis.get(2);
        result.mat[2][2] = zaxis.get(2);
        result.mat[2][3] = 0.0f;
        result.mat[3][0] = position.get(0);
        result.mat[3][1] = position.get(1);
        result.mat[3][2] = position.get(2);
        result.mat[3][3] = 1.0f;

        return result;
    }
}
