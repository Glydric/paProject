package it.unicam.pa.exam.app.Model;

import java.awt.*;

public class SquareEnvironmentModel extends EnvironmentModel {
    public SquareEnvironmentModel(int size, Color bgColor) {
        super(size,size, bgColor);
    }
    public SquareEnvironmentModel(int size) {
        super(size,size);
    }
}