package it.unicam.pa.exam.app.Module;

import java.awt.*;
import java.util.Objects;

/**
 * Classe che definisce una qualsiasi coordinata
 */
public class Coordinate extends Point {
    int height;
    int width;

    public Coordinate(int x, int y, int height, int width) {
        super(x, y);
        setMaxX(height);
        setMaxY(width);
    }

    @Override
    public double getX() {
        return x;
    }

    @Override

    public double getY() {
        return y;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    /**
     * @return la "home" del sistema di riferimento, identificata come x/2,y/2
     */
    public Coordinate getHome() {
        return new Coordinate(x / 2, y / 2, height, width);
    }

    /**
     * Imposta la coordinata del punto
     *
     * @param x coordinata x
     * @param y coordinata y
     */
    @Override
    public void setLocation(double x, double y) {
        if (x < 0) throw new IllegalArgumentException("Parametro x negativo");
        if (y < 0) throw new IllegalArgumentException("Parametro y negativo");

        super.setLocation(
                Math.min(x, height),
                Math.min(y, width)
        );
    }

    /**
     * Imposta la coordinata del punto
     *
     * @param x coordinata x
     * @param y coordinata y
     */
    @Override
    public void setLocation(int x, int y) {
        if (x < 0) throw new IllegalArgumentException("Parametro x negativo");
        if (y < 0) throw new IllegalArgumentException("Parametro y negativo");

        super.setLocation(
                Math.min(x, height),
                Math.min(y, width)
        );
    }

    /**
     * Imposta la coordinata del punto
     *
     * @param p coordinata point
     */
    @Override
    public void setLocation(Point p) {
        setLocation(p.x,p.y);
    }

    /**
     * @param max massimo valore assumibile in larghezza
     */
    private void setMaxY(int max) {
        if (max < 0)
            throw new IllegalArgumentException("Impossibile passare un parametro nullo");
        this.width = max;
    }

    /**
     * @param max massimo valore assumibile in altezza
     */
    private void setMaxX(int max) {
        if (max < 0)
            throw new IllegalArgumentException("Impossibile passare un parametro nullo");
        this.height = max;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordinate that)) return false;
        return getX() == that.getX()
                && getY() == that.getY()
                && getHeight() == that.getHeight()
                && getWidth() == that.getWidth();
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, height, width);
    }
}
