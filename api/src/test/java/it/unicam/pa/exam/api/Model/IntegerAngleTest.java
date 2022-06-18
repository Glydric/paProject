package it.unicam.pa.exam.api.Model;

import it.unicam.pa.exam.api.Model.Logo.IntegerAngle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IntegerAngleTest {
    @Test
    void directionConstructorTest() {
        IntegerAngle d = new IntegerAngle(100);
        assertEquals(100, d.getAngle());

        IntegerAngle di = new IntegerAngle();
        assertEquals(0, di.getAngle());
    }

    @Test
    void setDirectionTest() {
        IntegerAngle d = new IntegerAngle(100);
        d.setAngle(360);
        assertEquals(360, d.getAngle());

        d.setAngle(0);
        assertEquals(0, d.getAngle());

        d.setAngle(370);
        assertEquals(10, d.getAngle());

        d.setAngle(-60);
        assertEquals(300, d.getAngle());

        IntegerAngle di = new IntegerAngle(90,90);
        assertEquals(90, di.getAngle());
    }
    @Test
    void addAngleTest() {
        IntegerAngle d = new IntegerAngle(100);
        d.addAngle(60);
        assertEquals(160, d.getAngle());

        d.addAngle(-20);
        assertEquals(140, d.getAngle());
    }
}
