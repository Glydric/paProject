package it.unicam.pa.exam.app;

import it.unicam.pa.exam.api.LineInterface;
import it.unicam.pa.exam.app.Model.Line;
import it.unicam.pa.exam.app.Model.SquareCoordinate;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LineTest {
    @Test
    void LineConstructorTest() {
        Line l = new Line(
                new SquareCoordinate(10, 20, 50),
                new SquareCoordinate(15, 25, 50),
                new Color(0, 255, 0));
        assertEquals(10, l.getBegin().getX());
        assertEquals(20, l.getBegin().getY());
        assertEquals(50, l.getBegin().getMaxX());
        assertEquals(50, l.getBegin().getMaxY());

        assertEquals(15, l.getEnd().getX());
        assertEquals(25, l.getEnd().getY());
        assertEquals(50, l.getEnd().getMaxX());
        assertEquals(50, l.getEnd().getMaxY());

    }

    @Test
    void ThrowErrConstructor() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Line(
                        new SquareCoordinate(10, 20, 60),
                        new SquareCoordinate(15, 25, 50),
                        new Color(0, 255, 0))
        );
        assertThrows(
                IllegalArgumentException.class,
                () -> new Line(
                        new SquareCoordinate(10, 20, 50),
                        new SquareCoordinate(15, 25, 60),
                        new Color(0, 255, 0))
        );
    }
}
