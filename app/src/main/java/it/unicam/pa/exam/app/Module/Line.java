package it.unicam.pa.exam.app.Module;

import it.unicam.pa.exam.api.LineInterface;

import java.awt.*;
import java.util.Objects;

public final class Line implements LineInterface {
    private final Coordinate begin;
    private final Coordinate end;
    private final Color color;


    public Line(Coordinate begin, Coordinate end, Color color) {
        if (begin.getHeight() != end.getHeight()
                || begin.getWidth() != end.getWidth())
            throw new IllegalArgumentException("Le due coordinate devono avere lo stesso sistema di riferimento");
        this.begin = begin;
        this.end = end;
        this.color = color;
    }

    @Override
    public Coordinate getBegin() {
        return begin;
    }

    @Override
    public Coordinate getEnd() {
        return end;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Line) obj;
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
        return "Line[" +
                "begin=" + begin + ", " +
                "end=" + end + ", " +
                "color=" + color + ']';
    }

}
