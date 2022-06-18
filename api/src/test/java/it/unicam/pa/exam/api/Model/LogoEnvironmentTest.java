package it.unicam.pa.exam.api.Model;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogoEnvironmentTest {
    @Test
    void constructorTest(){
        LogoEnvironment e = new SquareLogoEnvironment(50,new Color(255,0,0));
        assertEquals(50,e.getCursor().getPosition().getHeight());
        assertEquals(50,e.getCursor().getPosition().getWidth());
    }
    @Test
    void writeTest(){
        LogoEnvironment e = new SquareLogoEnvironment(50,new Color(255,0,0));
        e.write(10);
    }
    @Test
    void toStringTest(){
        LogoEnvironment e = new SquareLogoEnvironment(50,new Color(255,0,0));

        System.out.print(e);
    }
}
