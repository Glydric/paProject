package it.unicam.pa.exam.app;

import it.unicam.pa.exam.api.CursorInterface;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CursorTest {
    @Test
    void constructorTest(){
        CursorInterface c = new Cursor(new Coordinate(50,60,100,100));
        assertEquals(50,c.getPosition().getX());
        assertEquals(60,c.getPosition().getY());
    }
    @Test
    void moveCursorTest(){
        CursorInterface c = new Cursor(new Coordinate(50,60,100,100),30);
        c.moveCursor(10);
        assertEquals(70,c.getPosition().getX());
        assertEquals(71,c.getPosition().getY());
    }
}
