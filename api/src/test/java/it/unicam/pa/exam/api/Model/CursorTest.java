package it.unicam.pa.exam.api.Model;

import it.unicam.pa.exam.api.Model.Logo.Cursor;
import it.unicam.pa.exam.api.Model.Logo.LimitedPoint;
import it.unicam.pa.exam.api.Model.Logo.SquareLimitedPoint;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CursorTest {
    @Test
    void constructorTest() {
        Cursor c = new Cursor(new SquareLimitedPoint(50, 60, 100));
        assertEquals(50, c.getPosition().getX());
        assertEquals(60, c.getPosition().getY());
    }

    @Test
    void moveCursorTest() {
        Cursor c = new Cursor(new SquareLimitedPoint(20, 50, 100), 30);

        c.setDirection(70);
        c.moveCursor(20);

        assertEquals(20 + 6, c.getPosition().getX());
        assertEquals(50 + 18, c.getPosition().getY());

        c.setDirection(250);
        c.moveCursor(20);

        assertEquals(20, c.getPosition().getX());
        assertEquals(50, c.getPosition().getY());

        c.setDirection(130);
        c.moveCursor(20);

        assertEquals(20 - 12, c.getPosition().getX());
        assertEquals(50 + 15, c.getPosition().getY());

        c.setDirection(310);
        c.moveCursor(20);

        assertEquals(20, c.getPosition().getX());
        assertEquals(50, c.getPosition().getY());
    }


    @Test
    void testXDirection() {
        Cursor c = new Cursor(new SquareLimitedPoint(30, 50, 100), 30);

        c.setDirection(180);
        c.moveCursor(20);
        assertEquals(10, c.getPosition().getX());

        c.setDirection(0);
        c.moveCursor(30);
        assertEquals(40, c.getPosition().getX());
    }

    @Test
    void testYDirection() {
        Cursor c = new Cursor(new SquareLimitedPoint(20, 50, 100), 30);

        c.setDirection(270);
        c.moveCursor(20);
        assertEquals(30, c.getPosition().getY());

        c.setDirection(90);
        c.moveCursor(10);
        assertEquals(40, c.getPosition().getY());
    }
}
