package it.unicam.pa.exam.app;

import it.unicam.pa.exam.list.DirectionInterface;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DirectionTest {
    @Test
    void directionConstructorTest() {
        DirectionInterface d = new Direction(100);
        assertEquals(100, d.getDirection());

        DirectionInterface di = new Direction();
        assertEquals(0,di.getDirection());
    }

    @Test
    void setDirectionTest() {
        DirectionInterface d = new Direction(100);
        d.setDirection(360);
        assertEquals(360, d.getDirection());
        d.setDirection(0);
        assertEquals(0, d.getDirection());

        assertThrows(IllegalArgumentException.class, () -> d.setDirection(370));
        assertThrows(IllegalArgumentException.class, () -> d.setDirection(-10));
    }
}
