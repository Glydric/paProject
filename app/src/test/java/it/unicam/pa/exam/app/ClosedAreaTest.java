package it.unicam.pa.exam.app;

import it.unicam.pa.exam.api.ClosedAreaInterface;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class ClosedAreaTest {
    @Test
    void AddLineTest(){
        ClosedAreaInterface a = new ClosedArea();
        a.addLine(
                new Line(
                    new SquareCoordinate(10, 25, 50),
                    new SquareCoordinate(15, 20, 50),
                    new Color(255, 0, 0)
        ));
        assertEquals(10,a.getLines().get(0).getBegin().getHeight());
        assertEquals(25,a.getLines().get(0).getBegin().getWidth());
        assertEquals(15,a.getLines().get(0).getEnd().getHeight());
        assertEquals(20,a.getLines().get(0).getEnd().getWidth());
    }
    @Test
    void isClosedTest() {
        ClosedAreaInterface a = new ClosedArea();
        a.addLine(new Line(
                new SquareCoordinate(10, 20, 50),
                new SquareCoordinate(15, 20, 50),
                new Color(255, 0, 0)
        ));
        assertFalse(a.isClosed());
        a.addLine(new Line(
                new SquareCoordinate(15, 20, 50),
                new SquareCoordinate(15, 25, 50),
                new Color(255, 0, 0)
        ));
        assertFalse(a.isClosed());
        a.addLine(new Line(
                new SquareCoordinate(15, 25, 50),
                new SquareCoordinate(10, 25, 50),
                new Color(255, 0, 0)
        ));
        assertFalse(a.isClosed());
        a.addLine(new Line(
                new SquareCoordinate(10, 25, 50),
                new SquareCoordinate(10, 20, 50),
                new Color(255, 0, 0)
        ));
        for (int i = 0; i < 100000; i++) {
            assertTrue(a.isClosed());
        }
    }
}
