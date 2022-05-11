package it.unicam.pa.exam.app;

import java.awt.*;

public class SquareEnvironment extends Environment {
    SquareEnvironment(int size, Color bgColor) {
        super(size,size, bgColor);
    }
    SquareEnvironment(int size) {
        super(size,size);
    }
}
