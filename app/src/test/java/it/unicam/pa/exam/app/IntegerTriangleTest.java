package it.unicam.pa.exam.app;

import it.unicam.pa.exam.api.TriangleInterface;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IntegerTriangleTest {
    @Test
    void calc() {
        TriangleInterface<Integer> t = new IntegerTriangle(10, 30);

        assertEquals(20, t.calcHorizontalCatFromDegrees());
        assertEquals(11, t.calcVerticalCatFromDegrees());

        t.setAngle(45);
        assertEquals(45, t.getAngle());

        t.setAngle(145);
        assertEquals(55, t.getAngle());

        assertThrows(IllegalArgumentException.class, () -> t.setAngle(360));
    }
}
