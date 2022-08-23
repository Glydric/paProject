package it.unicam.pa.exam.api.Model;

import it.unicam.pa.exam.api.Model.Logo.ColoredLine;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class ColoredLineTest {
    @Test
    void equalsTest(){
        ColoredLine c1 = new ColoredLine(new Point(0,0),new Point(0,0));
        ColoredLine c2 = new ColoredLine(new Point(0,0),new Point(0,0));
        assertEquals(c1, c2);
        c1 = new ColoredLine(new Point(15,0),new Point(0,0));
        c2 = new ColoredLine(new Point(15,0),new Point(0,0));
        assertEquals(c1, c2);
        c1 = new ColoredLine(new Point(0,15),new Point(0,0));
        c2 = new ColoredLine(new Point(15,0),new Point(0,0));
        assertNotEquals(c1, c2);
        c1 = new ColoredLine(new Point(0,0),new Point(15,0));
        c2 = new ColoredLine(new Point(15,0),new Point(0,0));
        assertNotEquals(c1, c2);
    }
}
