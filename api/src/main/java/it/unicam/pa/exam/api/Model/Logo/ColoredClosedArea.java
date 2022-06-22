package it.unicam.pa.exam.api.Model.Logo;

import java.awt.*;

public class ColoredClosedArea extends ClosedArea {
    Color color = Color.black;

    public void setColor(Color color) {
        this.color = color;
    }

    public void setColor(int r, int g, int b) {
        this.color = new Color(r, g, b);
    }

    public Color getColor() {
        return color;
    }
}
