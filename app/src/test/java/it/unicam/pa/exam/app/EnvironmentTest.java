package it.unicam.pa.exam.app;

import it.unicam.pa.exam.api.EnvironmentInterface;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EnvironmentTest {
    @Test
    void constructorTest(){
        EnvironmentInterface e = new SquareEnvironment(50,new Color(255,0,0));
        assertEquals(50,e.getCursor().getPosition().getMaxHeight());
        assertEquals(50,e.getCursor().getPosition().getMaxWidth());
    }
    @Test
    void writeTest(){
        Environment e = new SquareEnvironment(50,new Color(255,0,0));
        e.write(10);
    }
}
