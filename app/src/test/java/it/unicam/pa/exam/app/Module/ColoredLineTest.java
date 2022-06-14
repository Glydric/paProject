package it.unicam.pa.exam.app.Module;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ColoredLineTest {
    @Test
    void LineConstructorTest() {
        ColoredLine l = new ColoredLine(
                new SquareLimitedPoint(10, 20, 50),
                new SquareLimitedPoint(15, 25, 50),
                new Color(0, 255, 0));
        assertEquals(10, l.getP1().getX());
        assertEquals(20, l.getP1().getY());

        assertEquals(15, l.getP2().getX());
        assertEquals(25, l.getP2().getY());
    }

    @Test
    void ThrowErrConstructor() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new ColoredLine(
                        new SquareLimitedPoint(10, 20, 60),
                        new SquareLimitedPoint(15, 25, 50),
                        new Color(0, 255, 0))
        );
        assertThrows(
                IllegalArgumentException.class,
                () -> new ColoredLine(
                        new SquareLimitedPoint(10, 20, 50),
                        new SquareLimitedPoint(15, 25, 60),
                        new Color(0, 255, 0))
        );
    }
}
