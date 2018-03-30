package com.gzgr.vectormath;

public class Integer implements Number {
    private int value;

    public Integer(int value) {
        this.value = value;
    }

    @Override
    public void setInt(int val) {
        value = val;
    }

    @Override
    public void setFloat(float val) {
        value = (int)val;
    }

    @Override
    public void setByte(byte val) {
        value = val;
    }

    @Override
    public void setDouble(double val) {
        value = (int)val;
    }

    @Override
    public int getInt() {
        return (int)value;
    }

    @Override
    public float getFloat() {
        return (float)value;
    }

    @Override
    public byte getByte() {
        return (byte)value;
    }

    @Override
    public double getDouble() {
        return value;
    }

    @Override
    public Number copy() {
        return new Integer(value);
    }

    @Override
    public Number sum(Number b) {
        value = (value + b.getInt());
        return this;
    }

    @Override
    public Number subtract(Number b) {
        value = (value - b.getInt());
        return this;
    }

    @Override
    public Number negate() {
        value = (-value);
        return this;
    }

    @Override
    public Number multiply(Number b) {
        value = (value * b.getInt());
        return this;
    }

    @Override
    public Number divide(Number b) {
        value = (value / b.getInt());
        return this;
    }

    @Override
    public Number abs() {
        value = (int)(Math.abs(value));
        return this;
    }

    @Override
    public Number mod(Number b) {
        value = (int)(value % b.getDouble());
        return this;
    }

    @Override
    public Number squareRoot() {
        value = (int)(Math.sqrt(value));
        return this;
    }

    @Override
    public Number sin() {
        value = (int)(Math.sin(value));
        return this;
    }

    @Override
    public Number cos() {
        value = (int)(Math.cos(value));
        return this;
    }

    @Override
    public Number tan() {
        value = (int)(Math.tan(value));
        return this;
    }

    @Override
    public Number asin() {
        value = (int)(Math.asin(value));
        return this;
    }

    @Override
    public Number acos() {
        value = (int)(Math.acos(value));
        return this;
    }

    @Override
    public Number atan() {
        value = (int)(Math.atan(value));
        return this;
    }

    @Override
    public Number log() {
        value = (int)(Math.log(value));
        return this;
    }

    @Override
    public Number pow(Number exponent) {
        value = (int)(Math.pow(value, exponent.getDouble()));
        return this;
    }

    @Override
    public boolean equals(Number b) {
        return value == b.getDouble();
    }

    @Override
    public boolean greaterThan(Number b) {
        return value > b.getDouble();
    }

    @Override
    public boolean lessThan(Number b) {
        return value < b.getDouble();
    }
}
