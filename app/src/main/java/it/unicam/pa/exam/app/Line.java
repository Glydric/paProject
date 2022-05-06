package it.unicam.pa.exam.app;

import it.unicam.pa.exam.api.CoordinateInterface;
import it.unicam.pa.exam.api.LineInterface;

import java.awt.*;

public record Line(CoordinateInterface begin,
                   CoordinateInterface end,
                   Color color)
        implements LineInterface {


    @Override
    public CoordinateInterface getBegin() {
        return begin;
    }

    @Override
    public CoordinateInterface getEnd() {
        return end;
    }
    public Color getColor(){
        return color;
    }
}
