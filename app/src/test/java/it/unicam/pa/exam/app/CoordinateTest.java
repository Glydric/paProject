/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package it.unicam.pa.exam.app;

import it.unicam.pa.exam.api.CoordinateInterface;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CoordinateTest {
    @Test
    void testConstructor() {
        CoordinateInterface c = new SquareCoordinate(10, 20, 300);
        assertEquals(10, c.getHeight());
        assertEquals(20, c.getWidth());
        CoordinateInterface co = new Coordinate(10, 20, 300, 200);
        assertEquals(10, c.getHeight());
        assertEquals(20, c.getWidth());
    }

    @Test
    void testSet() {
        CoordinateInterface c = new Coordinate(10, 20, 50, 60);
        c.setNewPosition(30, 40);

        assertEquals(30, c.getHeight());
        assertEquals(40, c.getWidth());

        c.setNewPosition(100, 40);
        assertEquals(c.getMaxHeight(),c.getHeight());

        c.setNewPosition(10, 1280);
        assertEquals(c.getMaxWidth(),c.getWidth());
    }

    @Test
    void testGetArray() {
        CoordinateInterface c = new Coordinate(2, 4, 10, 20);

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 20; j++) {
                c.setNewPosition(i, j);
                assertEquals(c.getPositionAsArray()[0], c.getHeight());
                assertEquals(i, c.getHeight());

                assertEquals(c.getPositionAsArray()[1], c.getWidth());
                assertEquals(j, c.getWidth());
            }
        }
    }
    @Test
    void testGetMax(){
        CoordinateInterface c = new SquareCoordinate(0,0,250);

    }
}
