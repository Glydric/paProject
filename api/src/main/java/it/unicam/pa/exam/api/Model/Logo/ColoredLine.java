package it.unicam.pa.exam.api.Model.Logo;

import java.awt.*;
import java.util.Objects;

public class ColoredLine extends Line {
    private final Color color;


    public ColoredLine(Point begin, Point end, Color color, int size) {
        super(begin,end,size);
        this.color = color;
    }

    public ColoredLine(Point begin, Point end, Color color) {
        this(begin,end,color,1);
    }

    /**
     * Definisce il costruttore per una linea colorata con colore di default "nero"
     *
     * @param begin il punto di inizio
     * @param end   il punto di fine
     */
    public ColoredLine(Point begin, Point end) {
        super(begin,end);
        this.color = Color.black;
    }

    public Color getColor() {
        return color;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (ColoredLine) obj;
        return super.equals(that) &&
                Objects.equals(this.color, that.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), color);
    }

    @Override
    public String toString() {
        return super.toString()
                + " [" + color.getRed()
                + ',' + color.getGreen()
                + ',' + color.getBlue()
                + "] \n";
    }
}
