package it.unicam.pa.exam.app;

import it.unicam.pa.exam.api.TriangleInterface;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IntegerTriangleTest {
    @Test
    void calc(){
        TriangleInterface<Integer> t = new IntegerTriangle(10,30);
        assertEquals(20,t.calcHorizontalCatFromDegrees());
        assertEquals(11,t.calcVerticalCatFromDegrees());

        // todo add cases
    }
}
