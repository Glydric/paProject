package it.unicam.pa.exam.api.Model;

import it.unicam.pa.exam.api.Model.Logo.ColoredLine;
import it.unicam.pa.exam.api.Model.Logo.ClosedArea;
import it.unicam.pa.exam.api.Model.Logo.SquareLimitedPoint;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class ClosedAreaTest {
    @Test
    void AddLineTest(){
        ClosedArea a = new ClosedArea();
        a.addLine(
                new ColoredLine(
                    new SquareLimitedPoint(10, 25, 50),
                    new SquareLimitedPoint(15, 20, 50),
                    new Color(255, 0, 0)
        ));
        assertEquals(10,a.getLines().get(0).getP1().getX());
        assertEquals(25,a.getLines().get(0).getP1().getY());
        assertEquals(15,a.getLines().get(0).getP2().getX());
        assertEquals(20,a.getLines().get(0).getP2().getY());
    }
    @Test
    void isClosedTest() {
        ClosedArea a = new ClosedArea();
        a.addLine(new ColoredLine(
                new SquareLimitedPoint(10, 20, 50),
                new SquareLimitedPoint(15, 20, 50),
                new Color(255, 0, 0)
        ));
        assertFalse(a.isClosed());
        a.addLine(new ColoredLine(
                new SquareLimitedPoint(15, 20, 50),
                new SquareLimitedPoint(15, 25, 50),
                new Color(255, 0, 0)
        ));
        assertFalse(a.isClosed());
        a.addLine(new ColoredLine(
                new SquareLimitedPoint(15, 25, 50),
                new SquareLimitedPoint(10, 25, 50),
                new Color(255, 0, 0)
        ));
        assertFalse(a.isClosed());
        a.addLine(new ColoredLine(
                new SquareLimitedPoint(10, 25, 50),
                new SquareLimitedPoint(10, 20, 50),
                new Color(255, 0, 0)
        ));
        for (int i = 0; i < 100000; i++) {
            assertTrue(a.isClosed());
        }
    }
}
