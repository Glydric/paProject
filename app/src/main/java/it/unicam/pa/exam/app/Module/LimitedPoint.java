package it.unicam.pa.exam.app.Module;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Objects;

/**
 * Classe che definisce una qualsiasi coordinata
 */
public class LimitedPoint extends Point {
    private int height;
    private int width;

    public LimitedPoint(int x, int y, int height, int width) {
        super(x, y);
        setMaxHeight(height);
        setMaxWidth(width);
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
    public Point getHome() {
        return new Point(height / 2, width / 2);
    }

    /**
     * Imposta la coordinata del punto CONVERTENDO IL DOUBLE IN INTEGER
     *
     * @param x coordinata x
     * @param y coordinata y
     */
    @Override
    public void setLocation(double x, double y) {
        setLocation((int) x, (int) y);
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
        setLocation(p.x, p.y);
    }
    public void setLocation(Point2D p) {
        setLocation(p.getX(), p.getY());
    }

    /**
     * @param maxWidth massimo valore assumibile in larghezza
     */
    private void setMaxWidth(int maxWidth) {
        if (maxWidth < 0)
            throw new IllegalArgumentException("Impossibile passare un parametro nullo");
        this.width = maxWidth;
    }

    /**
     * @param maxHeight massimo valore assumibile in altezza
     */
    private void setMaxHeight(int maxHeight) {
        if (maxHeight < 0)
            throw new IllegalArgumentException("Impossibile passare un parametro nullo");
        this.height = maxHeight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LimitedPoint that)) return false;
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
