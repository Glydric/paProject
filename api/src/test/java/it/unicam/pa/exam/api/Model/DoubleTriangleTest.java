package it.unicam.pa.exam.api.Model;

import it.unicam.pa.exam.api.Model.Triangle.DoubleTriangle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DoubleTriangleTest {
    @Test
    void calc() {
        DoubleTriangle t = new DoubleTriangle(10, 30);

        assertEquals(4.999999999999999, t.calcVertical());
        assertEquals(8.660254037844387, t.calcHorizontal());

        t.setAngle(45);
        t.setIpo(10.0);

        double horizontal = t.calcHorizontal();
        double vertical = t.calcVertical();

        assertEquals(t.getIpo(), Math.sqrt(horizontal * horizontal + vertical * vertical));

        t.setIpo(20.0);

        horizontal = t.calcHorizontal();
        vertical = t.calcVertical();

        assertEquals(t.getIpo(), Math.sqrt(horizontal * horizontal + vertical * vertical));

        t.setAngle(20);

        assertEquals(6.840402866513374, t.calcVertical());
        assertEquals(18.793852415718167, t.calcHorizontal());
    }

    @Test
    void setAngle() {
        for (int i = 0; i < 4; i++) {
            int angle = 90 * i;
            assertThrows(IllegalArgumentException.class, () -> new DoubleTriangle(10, angle));
        }
    }
}
