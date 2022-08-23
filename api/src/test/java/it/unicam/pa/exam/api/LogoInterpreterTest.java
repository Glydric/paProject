package it.unicam.pa.exam.api;

import it.unicam.pa.exam.api.Model.LogoEnvironment;
import it.unicam.pa.exam.api.Model.Logo.IntegerAngle;
import it.unicam.pa.exam.api.Model.SquareLogoEnvironment;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogoInterpreterTest {
    @Test
    void constructorTest() {
        LogoInterpreter<LogoEnvironment> l = new LogoInterpreter<>(new LogoEnvironment(50, 60));

        assertEquals(50, l.environment.getHeight());
        assertEquals(60, l.environment.getWidth());
    }

    @Test
    void executeText() {
        LogoInterpreter<LogoEnvironment> l = new LogoInterpreter<>(new SquareLogoEnvironment(100));

        l.execute("forward 20");
        assertEquals(70, l.environment.getCursor().getPosition().getX());

        l.execute("backward 10");
        assertEquals(60, l.environment.getCursor().getPosition().getX());

        l.execute("left 45");
        assertEquals(45, l.environment.getCursor().getDirection().getAngle());

        l.execute("right 90");
        assertEquals(315, l.environment.getCursor().getDirection().getAngle());

        // height >> 1 sta a indicare uno spostamento a sinistra dei bit e quindi equivale a / 2
        l.execute("home");
        assertEquals(l.environment.getCursor().getPosition().getHeight() >> 1, l.environment.getCursor().getPosition().getHome().getX());
        assertEquals(l.environment.getCursor().getPosition().getWidth() >> 1, l.environment.getCursor().getPosition().getHome().getY());

        l.execute("setpencolor 255 255 0");
        assertEquals(255, l.environment.getCursor().color.getRed());
        assertEquals(255, l.environment.getCursor().color.getGreen());
        assertEquals(0, l.environment.getCursor().color.getBlue());

        l.execute("clear");
        assertEquals(l.environment.getCursor().getPosition().getHome().getX(), l.environment.getCursor().getPosition().getX());
        assertEquals(l.environment.getCursor().getPosition().getHome().getY(), l.environment.getCursor().getPosition().getY());

        l.execute("repeat 4 [right 90, forward 10]");
        assertEquals(4, l.environment.getLines().size());
        l.clear();

        l.execute("setfillcolor 0 0 255");
        l.execute("setpensize 5");
        l.execute("repeat 4 [forward 100,left 90]");
        l.execute("right 90");
        l.execute("penup");
        l.execute("forward 100");
        l.execute("pendown");
        l.execute("setpensize 10");
        l.execute("repeat 4 [forward 100,left 90]");
    }

    @Test
    void forwardTest() {
        LogoInterpreter<LogoEnvironment> l = new LogoInterpreter<>(new SquareLogoEnvironment(50));
        l.forward(20);
        assertEquals(45, l.environment.getCursor().getPosition().getX());

        l.forward(20);
        assertEquals(50, l.environment.getCursor().getPosition().getX());

        l.left(new IntegerAngle(90));
        l.forward(40);
        assertEquals(50, l.environment.getCursor().getPosition().getY());
    }

    @Test
    void leftTest() {
        LogoInterpreter<LogoEnvironment> l = new LogoInterpreter<>(new SquareLogoEnvironment(50));

        l.left(new IntegerAngle(30));
        assertEquals(30, l.environment.getCursor().getDirection().getAngle());
        l.left(new IntegerAngle(-10));
        assertEquals(20, l.environment.getCursor().getDirection().getAngle());
    }

    @Test
    void rightTest() {
        LogoInterpreter<LogoEnvironment> l = new LogoInterpreter<>(new SquareLogoEnvironment(50));

        assertEquals(0, l.environment.getCursor().getDirection().getAngle());

        l.right(new IntegerAngle(20));
        assertEquals(340, l.environment.getCursor().getDirection().getAngle());

        l.right(new IntegerAngle(-10));
        assertEquals(350, l.environment.getCursor().getDirection().getAngle());

        l.right(new IntegerAngle(-10));
        assertEquals(360, l.environment.getCursor().getDirection().getAngle());
    }
}
