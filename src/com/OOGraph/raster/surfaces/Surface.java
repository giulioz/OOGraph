package com.OOGraph.raster.surfaces;

import com.OOGraph.math.Matrix;
import com.OOGraph.math.Point;
import com.OOGraph.math.Rectangle;
import com.OOGraph.math.Vector;
import com.OOGraph.raster.colors.Color;
import com.OOGraph.raster.colors.ColorFactory;

public interface Surface<T> {
    byte[] getSurfaceData();

    T getLinear(int index);
    void setLinear(int index, T value);

    default <Tsource extends Color> void setLinearConvert(int index, Tsource value) {
        this.setLinear(index, getColorFactory().fromRGBVector(value.getVector()));
    }

    T getXY(int x, int y);
    void setXY(int x, int y, T value);

    default <Tsource extends Color> void setXYConvert(int x, int y, Tsource value) {
        this.setXY(x, y, getColorFactory().fromRGBVector(value.getVector()));
    }

    int getWidth();
    int getHeight();
    int getLength();

    ColorFactory<T> getColorFactory();

    default Rectangle getRect() {
        return new Rectangle(0, 0, getWidth() - 1, getHeight() - 1);
    }

    default void fill(T val) {
        for (int i = 0; i < this.getLength(); i++) {
            setLinear(i, val);
        }
    }

    default void fill(T val, Rectangle dest) {
        for (int yIndex = 0; yIndex < getHeight(); yIndex++) {
            for (int xIndex = 0; xIndex < getWidth(); xIndex++) {
                setXY(xIndex, yIndex, val);
            }
        }
    }

    default void blit(Surface<T> source) {
        blit(source, new Point(0, 0), new Point(0, 0));
    }

    default void blit(Surface<T> source, Point destinationPos) {
        blit(source, destinationPos, new Point(0, 0));
    }

    default void blit(Surface<T> source, Point destinationPos, Point sourcePos) {
        blit(source, new Rectangle(destinationPos.x, destinationPos.y, getWidth(), getHeight()),
                new Rectangle(sourcePos.x, sourcePos.y, source.getWidth(),source.getHeight()));
    }

    default void blit(Surface<T> source, Rectangle destinationRect) {
        blit(source, destinationRect, new Rectangle(0, 0, source.getWidth(), source.getHeight()));
    }

    default void blit(Surface<T> source, Rectangle destinationRect, Rectangle sourceRect) {
        for (int yIndexDst = destinationRect.getY(), yIndexSrc = sourceRect.getY();
             yIndexDst < destinationRect.getHeight() && yIndexSrc < sourceRect.getHeight();
             yIndexDst++, yIndexSrc++) {
            for (int xIndexDst = destinationRect.getX(), xIndexSrc = sourceRect.getX();
                 xIndexDst < destinationRect.getWidth() && xIndexSrc < sourceRect.getWidth();
                 xIndexDst++, xIndexSrc++) {
                setXY(xIndexDst, yIndexDst, source.getXY(xIndexSrc, yIndexSrc));
            }
        }
    }

    default <Tsource extends Color> void blitConvert(Surface<Tsource> source) {
        blitConvert(source, new Point(0, 0), new Point(0, 0));
    }

    default <Tsource extends Color> void blitConvert(Surface<Tsource> source, Point destinationPos) {
        blitConvert(source, destinationPos, new Point(0, 0));
    }

    default <Tsource extends Color> void blitConvert(Surface<Tsource> source, Point destinationPos, Point sourcePos) {
        blitConvert(source, new Rectangle(destinationPos.x, destinationPos.y, getWidth(), getHeight()),
                new Rectangle(sourcePos.x, sourcePos.y, source.getWidth(),source.getHeight()));
    }

    default <Tsource extends Color> void blitConvert(Surface<Tsource> source, Rectangle destinationRect) {
        blitConvert(source, destinationRect, new Rectangle(0, 0, source.getWidth(), source.getHeight()));
    }

    default <Tsource extends Color> void blitConvert(Surface<Tsource> source, Rectangle destinationRect, Rectangle sourceRect) {
        for (int yIndexDst = destinationRect.getY(), yIndexSrc = sourceRect.getY();
             yIndexDst < destinationRect.getHeight() && yIndexSrc < sourceRect.getHeight();
             yIndexDst++, yIndexSrc++) {
            for (int xIndexDst = destinationRect.getX(), xIndexSrc = sourceRect.getX();
                 xIndexDst < destinationRect.getWidth() && xIndexSrc < sourceRect.getWidth();
                 xIndexDst++, xIndexSrc++) {
                setXYConvert(xIndexDst, yIndexDst, source.getXY(xIndexSrc, yIndexSrc));
            }
        }
    }

    default Vector cartesianToSurface(Vector input) {
        int halfWidth = this.getWidth() / 2;
        int halfHeight = this.getHeight() / 2;
        float aspect = (float)this.getWidth() / this.getHeight();
        return input
                .multiply(new Vector(halfWidth, -halfHeight * aspect))
                .sum(new Vector(halfWidth, halfHeight));
    }

    default Matrix cartesianToSurfaceMatrix() {
        int halfWidth = this.getWidth() / 2;
        int halfHeight = this.getHeight() / 2;
        float aspect = (float)this.getWidth() / this.getHeight();
        return Matrix.createTranslation(4, 4, new Vector(halfWidth, halfHeight))
                .multiply(Matrix.createScale(4, 4, new Vector(halfWidth, -halfHeight * aspect)));
    }
}
