package it.unicam.pa.exam.app.Model;

import java.awt.*;
import java.util.Objects;

/**
 * Classe che definisce una qualsiasi coordinata
 */
public class Coordinate extends Point {
    int xMax;
    int yMax;

    public Coordinate(int x, int y, int xMax, int yMax) {
        super(x, y);
        setMaxX(xMax);
        setMaxY(yMax);
    }

    @Override
    public double getX() {
        return x;
    }

    @Override

    public double getY() {
        return y;
    }

    public int getMaxX() {
        return xMax;
    }

    public int getMaxY() {
        return yMax;
    }

    /**
     * @return la "home" del sistema di riferimento, identificata come x/2,y/2
     */
    public Coordinate getHome() {
        return new Coordinate(x / 2, y / 2, xMax, yMax);
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
                Math.min(x, xMax),
                Math.min(y, yMax)
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
                Math.min(x, xMax),
                Math.min(y, yMax)
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
        this.yMax = max;
    }

    /**
     * @param max massimo valore assumibile in altezza
     */
    private void setMaxX(int max) {
        if (max < 0)
            throw new IllegalArgumentException("Impossibile passare un parametro nullo");
        this.xMax = max;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordinate that)) return false;
        return getX() == that.getX()
                && getY() == that.getY()
                && getMaxX() == that.getMaxX()
                && getMaxY() == that.getMaxY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, xMax, yMax);
    }
}
