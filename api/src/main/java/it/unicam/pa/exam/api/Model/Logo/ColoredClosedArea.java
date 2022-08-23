package it.unicam.pa.exam.api.Model.Logo;

import java.awt.*;
import java.util.stream.Collectors;

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


    @Override
    public String toString() {
        return getLines().isEmpty()
                ? ""
                : "<SHAPE> "
                + color.getRed() + " "
                + color.getBlue() + " "
                + color.getGreen()
                + "\n" +
                getLines()
                        .stream()
                        .map(ColoredLine::toString)
                        .collect(Collectors.joining()) +
                "<SHAPE>\n";
    }
}
