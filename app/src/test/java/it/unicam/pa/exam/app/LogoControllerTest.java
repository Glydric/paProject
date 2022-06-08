package it.unicam.pa.exam.app;

import it.unicam.pa.exam.app.Model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogoControllerTest {
    @Test
    void constructorTest() {
        LogoController l = new LogoController(new EnvironmentModel(50, 60));

        assertEquals(50, l.environment.getCursor().getPosition().getMaxX());
        assertEquals(60, l.environment.getCursor().getPosition().getMaxY());
    }

    @Test
    void executeText(){
        LogoController l = new LogoController(new SquareEnvironmentModel(50));

        l.execute("forward 20");
        assertEquals(20, l.environment.getCursor().getPosition().getY());

        l.execute("backward 10");
        assertEquals(10, l.environment.getCursor().getPosition().getY());

        l.execute("right 45");
        assertEquals(45, l.environment.getCursor().getDirection().getAngle());

        l.execute("left 90");
        assertEquals(315, l.environment.getCursor().getDirection().getAngle());
        //todo add more test when other methods will work
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
