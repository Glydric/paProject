package it.unicam.pa.exam.api;

import org.junit.jupiter.api.Test;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class lineTest {
    @Test
    void lineOrMyLine(){
        Line2D line = new Line2D.Float(10,20,30,40);
        Point2D p = line.getP1();

    }
}
