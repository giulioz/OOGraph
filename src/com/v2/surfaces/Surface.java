package com.v2.surfaces;

import com.v2.Point;
import com.v2.Rectangle;

public interface Surface<T> {
    byte[] getSurfaceData();

    T getLinear(int index);
    void setLinear(int index, T value);
    T getXY(int x, int y);
    void setXY(int x, int y, T value);

    int getWidth();
    int getHeight();
    int getLength();

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
        blit(source, new Rectangle(destinationPos.getX(), destinationPos.getY(), getWidth(), getHeight()),
                new Rectangle(sourcePos.getX(), sourcePos.getY(), source.getWidth(),source.getHeight()));
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
}
