package it.unicam.pa.exam.app.Module;

import it.unicam.pa.exam.api.ClosedAreaInterface;
import it.unicam.pa.exam.app.Module.ClosedArea;
import it.unicam.pa.exam.app.Module.Line;
import it.unicam.pa.exam.app.Module.SquareCoordinate;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class ClosedAreaTest {
    @Test
    void AddLineTest(){
        ClosedArea a = new ClosedArea();
        a.addLine(
                new Line(
                    new SquareCoordinate(10, 25, 50),
                    new SquareCoordinate(15, 20, 50),
                    new Color(255, 0, 0)
        ));
        assertEquals(10,a.getLines().get(0).getBegin().getX());
        assertEquals(25,a.getLines().get(0).getBegin().getY());
        assertEquals(15,a.getLines().get(0).getEnd().getX());
        assertEquals(20,a.getLines().get(0).getEnd().getY());
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
