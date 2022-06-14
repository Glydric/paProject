package it.unicam.pa.exam.app.Module;


import java.awt.Color;

public class ColoredClosedArea extends ClosedArea {
    Color color;

    public void setColor(Color color) {
        this.color = color;
    }

    public void setColor(byte r, byte g, byte b) {
        this.color = new Color(r, g, b);
    }
}
