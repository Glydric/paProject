package it.unicam.pa.exam.app;

import it.unicam.pa.exam.app.Model.Coordinate;
import it.unicam.pa.exam.app.Model.Cursor;
import it.unicam.pa.exam.app.Model.SquareCoordinate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CursorTest {
    @Test
    void constructorTest(){
        Cursor c = new Cursor(new Coordinate(50,60,100,100));
        assertEquals(50,c.getPosition().getX());
        assertEquals(60,c.getPosition().getY());
    }
    @Test
    void moveCursorTest(){
        Cursor c = new Cursor(new SquareCoordinate(50,60,100),30);
        c.moveCursor(10);
        assertEquals(70,c.getPosition().getX());
        assertEquals(71,c.getPosition().getY());
    }
}
