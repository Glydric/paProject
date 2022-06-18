package it.unicam.pa.exam.api;

import it.unicam.pa.exam.api.Model.LogoEnvironment;
import it.unicam.pa.exam.api.Model.Logo.IntegerAngle;
import it.unicam.pa.exam.api.Model.SquareLogoEnvironment;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogoInterpreterTest {
    @Test
    void constructorTest() {
        LogoInterpreter l = new LogoInterpreter(new LogoEnvironment(50, 60));

        assertEquals(50, l.environment.getCursor().getPosition().getHeight());
        assertEquals(60, l.environment.getCursor().getPosition().getWidth());
    }

    @Test
    void executeText() {
        LogoInterpreter l = new LogoInterpreter(new SquareLogoEnvironment(50));

        l.execute("forward 20");
        assertEquals(20, l.environment.getCursor().getPosition().getY());

        l.execute("backward 10");
        assertEquals(10, l.environment.getCursor().getPosition().getY());

        l.execute("left 45");
        assertEquals(45, l.environment.getCursor().getDirection().getAngle());

        l.execute("right 90");
        assertEquals(315, l.environment.getCursor().getDirection().getAngle());

        // height >> 1 sta a indicare uno spostamento a sinistra dei bit e quindi equivale a / 2
        l.execute("home");
        assertEquals(l.environment.getCursor().getPosition().getHeight() >> 1, l.environment.getCursor().getPosition().getHome().getX());
        assertEquals(l.environment.getCursor().getPosition().getWidth() >> 1, l.environment.getCursor().getPosition().getHome().getY());

        //todo add more test

        l.execute("clear");
        assertEquals(l.environment.getCursor().getPosition().getHome().getX(), l.environment.getCursor().getPosition().getX());
        assertEquals(l.environment.getCursor().getPosition().getHome().getY(), l.environment.getCursor().getPosition().getY());

    }

    @Test
    void forwardTest() {
        LogoInterpreter l = new LogoInterpreter(new SquareLogoEnvironment(50));
        l.forward(20);
        assertEquals(20, l.environment.getCursor().getPosition().getY());

        l.forward(20);
        assertEquals(40, l.environment.getCursor().getPosition().getY());

        l.forward(40);
        assertEquals(50, l.environment.getCursor().getPosition().getY());


        l.left(new IntegerAngle(90));
        l.forward(40);
        assertEquals(40, l.environment.getCursor().getPosition().getX());
    }

    @Test
    void leftTest() {
        LogoInterpreter l = new LogoInterpreter(new SquareLogoEnvironment(50));

        l.left(new IntegerAngle(30));
        assertEquals(30, l.environment.getCursor().getDirection().getAngle());
        l.left(new IntegerAngle(-10));
        assertEquals(20, l.environment.getCursor().getDirection().getAngle());
    }

    @Test
    void rightTest() {
        LogoInterpreter l = new LogoInterpreter(new SquareLogoEnvironment(50));

        assertEquals(0, l.environment.getCursor().getDirection().getAngle());

        l.right(new IntegerAngle(20));
        assertEquals(340, l.environment.getCursor().getDirection().getAngle());

        l.right(new IntegerAngle(-10));
        assertEquals(350, l.environment.getCursor().getDirection().getAngle());

        l.right(new IntegerAngle(-10));
        assertEquals(360, l.environment.getCursor().getDirection().getAngle());
    }
}
