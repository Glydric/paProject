package it.unicam.pa.exam.app.Module;

import it.unicam.pa.exam.app.Module.EnvironmentModel;
import it.unicam.pa.exam.app.Module.SquareEnvironmentModel;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EnvironmentModelTest {
    @Test
    void constructorTest(){
        EnvironmentModel e = new SquareEnvironmentModel(50,new Color(255,0,0));
        assertEquals(50,e.getCursor().getPosition().getHeight());
        assertEquals(50,e.getCursor().getPosition().getWidth());
    }
    @Test
    void writeTest(){
        EnvironmentModel e = new SquareEnvironmentModel(50,new Color(255,0,0));
        e.write(10);
    }
}
