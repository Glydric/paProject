package it.unicam.pa.exam.app.Module;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Objects;

public class ColoredLine extends Line2D {
    private final Point begin;
    private final Point end;
    private final Color color;

    public ColoredLine(Point begin, Point end, Color color) {
        this.begin = begin;
        this.end = end;
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public double getX1() {
        return begin.x;
    }

    @Override
    public double getY1() {
        return begin.y;
    }

    @Override
    public Point2D getP1() {
        return begin;
    }

    @Override
    public double getX2() {
        return end.x;
    }

    @Override
    public double getY2() {
        return end.y;
    }

    @Override
    public Point2D getP2() {
        return end;
    }

    @Override
    public void setLine(double x1, double y1, double x2, double y2) {
        begin.setLocation(x1, y1);
        end.setLocation(x2, y2);
    }

    @Override
    public Rectangle2D getBounds2D() {
        return new Rectangle(begin.x, begin.y, end.x, end.y);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (ColoredLine) obj;
        return Objects.equals(this.begin, that.begin) &&
                Objects.equals(this.end, that.end) &&
                Objects.equals(this.color, that.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(begin, end, color);
    }

    @Override
    public String toString() {
        return "ColoredLine[" +
                "begin=" + begin + ", " +
                "end=" + end + ", " +
                "color=" + color + ']';
    }
}
