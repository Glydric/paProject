package it.unicam.pa.exam.app.Controller;

import it.unicam.pa.exam.app.Module.EnvironmentModel;
import it.unicam.pa.exam.app.Module.IntegerAngle;
import it.unicam.pa.exam.app.Module.SquareEnvironmentModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogoControllerTest {
    @Test
    void constructorTest() {
        LogoController l = new LogoController(new EnvironmentModel(50, 60));

        assertEquals(50, l.environment.getCursor().getPosition().getHeight());
        assertEquals(60, l.environment.getCursor().getPosition().getWidth());
    }

    @Test
    void executeText() {
        LogoController l = new LogoController(new SquareEnvironmentModel(50));

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

        //todo add more test when other methods will work

        l.execute("clear");
        assertEquals(l.environment.getCursor().getPosition().getHome().getX(), l.environment.getCursor().getPosition().getX());
        assertEquals(l.environment.getCursor().getPosition().getHome().getY(), l.environment.getCursor().getPosition().getY());

    }

    @Test
    void forwardTest() {
        LogoController l = new LogoController(new SquareEnvironmentModel(50));
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
        LogoController l = new LogoController(new SquareEnvironmentModel(50));

        l.left(new IntegerAngle(30));
        assertEquals(30, l.environment.getCursor().getDirection().getAngle());
        l.left(new IntegerAngle(-10));
        assertEquals(20, l.environment.getCursor().getDirection().getAngle());
    }

    @Test
    void rightTest() {
        LogoController l = new LogoController(new SquareEnvironmentModel(50));

        assertEquals(0, l.environment.getCursor().getDirection().getAngle());

        l.right(new IntegerAngle(20));
        assertEquals(340, l.environment.getCursor().getDirection().getAngle());

        l.right(new IntegerAngle(-10));
        assertEquals(350, l.environment.getCursor().getDirection().getAngle());

        l.right(new IntegerAngle(-10));
        assertEquals(360, l.environment.getCursor().getDirection().getAngle());
    }
}
