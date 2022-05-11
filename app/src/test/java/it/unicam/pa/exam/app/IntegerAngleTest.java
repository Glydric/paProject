package it.unicam.pa.exam.app;

import it.unicam.pa.exam.api.AngleInterface;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IntegerAngleTest {
    @Test
    void directionConstructorTest() {
        AngleInterface<Integer> d = new IntegerAngle(100);
        assertEquals(100, d.getAngle());

        AngleInterface<Integer> di = new IntegerAngle();
        assertEquals(0, di.getAngle());
    }

    @Test
    void setDirectionTest() {
        AngleInterface<Integer> d = new IntegerAngle(100);
        d.setAngle(360);
        assertEquals(360, d.getAngle());
        d.setAngle(0);
        assertEquals(0, d.getAngle());
        d.setAngle(370);
        assertEquals(10, d.getAngle());
        assertThrows(IllegalArgumentException.class, () -> d.setAngle(-10));
        d.setAngle(360);
        assertEquals(360, d.getAngle());

        AngleInterface<Integer> di = new IntegerAngle(90,90);
        assertEquals(90, di.getAngle());
    }
}
