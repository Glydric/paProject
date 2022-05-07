package it.unicam.pa.exam.app;

import it.unicam.pa.exam.api.CoordinateInterface;
import it.unicam.pa.exam.api.LineInterface;

import java.awt.*;
import java.util.Objects;

public final class Line implements LineInterface {
    private final CoordinateInterface begin;
    private final CoordinateInterface end;
    private final Color color;


    public Line(CoordinateInterface begin, CoordinateInterface end, Color color) {
        if (begin.getMaxHeight() != end.getMaxHeight()
                || begin.getMaxWidth() != end.getMaxWidth())
            throw new IllegalArgumentException("Le due coordinate devono avere lo stesso sistema di riferimento");
        this.begin = begin;
        this.end = end;
        this.color = color;
    }

    @Override
    public CoordinateInterface getBegin() {
        return begin;
    }

    @Override
    public CoordinateInterface getEnd() {
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
