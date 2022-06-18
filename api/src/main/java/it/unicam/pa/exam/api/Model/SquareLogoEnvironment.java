package it.unicam.pa.exam.api.Model;

import java.awt.*;

public class SquareLogoEnvironment extends LogoEnvironment {
    public SquareLogoEnvironment(int size, Color bgColor) {
        super(size,size, bgColor);
    }
    public SquareLogoEnvironment(int size) {
        super(size,size);
    }
}
