package com.v2;

public class Vertex {
    private Point position;

    public Vertex(Point position) {
        this.position = position;
    }

    public Vertex(int x, int y) {
        this.position = new Point(x, y);
    }

    public Point getPosition() {
        return position;
    }
}
