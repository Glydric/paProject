package it.unicam.pa.exam.api.Model.Logo;

import java.awt.*;
import java.awt.geom.Line2D;
import java.util.stream.Collectors;

public class ColoredClosedArea<L extends Line2D> extends ClosedArea<L> {
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

    public void addLine(L line) {
        super.addLine(line);
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
                        .map(Object::toString)
                        .collect(Collectors.joining()) +
                "<SHAPE>\n";
    }
}
