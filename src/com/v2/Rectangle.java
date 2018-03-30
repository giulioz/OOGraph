package com.v2;

public class Rectangle {
    private int x, y, width, height;

    public Rectangle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Point getUpperLeft() {
        return new Point(x, y);
    }

    public Point getLowerRight() {
        return new Point(x + width, y + height);
    }

    public Point getUpperRight() {
        return new Point(x + width, y);
    }

    public Point getLowerLeft() {
        return new Point(x, y + height);
    }

    public boolean contains(int x, int y) {
        return this.x <= x &&
            x < this.x + this.width &&
            this.y <= y &&
            y < this.y + this.height;
    }

    public boolean contains(Point pt) {
        return contains(pt.getX(), pt.getY());
    }

    public boolean contains(Rectangle rect) {
        return (this.x <= rect.x) && ((rect.x + rect.width) <= (this.x + this.width)) &&
            (this.y <= rect.y) && ((rect.y + rect.height) <= (this.y + this.height));
    }

    public Rectangle inflate(int width, int height)
    {
        int ax = x - width;
        int ay = y - height;
        int awidth = this.width + 2*width;
        int aheight =  this.height + 2*height;
        return new Rectangle(ax, ay, awidth, aheight);
    }

    public boolean intersectsWith(Rectangle rect)
    {
        return(rect.x < this.x + this.width) &&
            (this.x < (rect.x + rect.width)) &&
            (rect.y < this.y + this.height) &&
            (this.y < rect.y + rect.height);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Rectangle) {
            Rectangle r = (Rectangle)obj;
            return width == r.width && height == r.height && x == r.x && y == r.y;
        } else{
            return super.equals(obj);
        }
    }
}
