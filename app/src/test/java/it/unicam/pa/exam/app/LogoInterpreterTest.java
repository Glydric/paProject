package it.unicam.pa.exam.app;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogoInterpreterTest {
    @Test
    void constructorTest() {
        LogoInterpreter l = new LogoInterpreter(new Environment(50, 60));
        /*
        assertEquals(1,l.getIntArrayFromString("[1,2,3]")[0]);
        assertEquals(2,l.getIntArrayFromString("[1,2,3]")[1]);
        assertEquals(3,l.getIntArrayFromString("[1,2,3]")[2]);
        */
        assertEquals(50, l.getEnvironment().getCursor().getPosition().getMaxHeight());
        assertEquals(60, l.getEnvironment().getCursor().getPosition().getMaxWidth());
    }

    @Test
    void forwardTest() {
        LogoInterpreter l = new LogoInterpreter(new SquareEnvironment(50));
        l.forward(20);
        assertEquals(20, l.getEnvironment().getCursor().getPosition().getWidth());

        l.forward(20);
        assertEquals(40, l.getEnvironment().getCursor().getPosition().getWidth());

        l.forward(40);
        assertEquals(50, l.getEnvironment().getCursor().getPosition().getWidth());

        //l.right(new IntegerAngle(20));
        //assertEquals(50, l.getEnvironment().getCursor().getPosition().getWidth());
    }

    @Test
    void leftAndRightTest() {
        LogoInterpreter l = new LogoInterpreter(new SquareEnvironment(50));

        l.left(new IntegerAngle(10));
        assertEquals(10, l.getEnvironment().getCursor().getDirection().getAngle());
        l.left(new IntegerAngle(30));
        assertEquals(40, l.getEnvironment().getCursor().getDirection().getAngle());
        l.right(new IntegerAngle(20));
        assertEquals(20, l.getEnvironment().getCursor().getDirection().getAngle());
    }
}
