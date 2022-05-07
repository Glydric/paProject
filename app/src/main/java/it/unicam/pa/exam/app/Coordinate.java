package it.unicam.pa.exam.app;

import it.unicam.pa.exam.api.CoordinateInterface;

import java.util.Objects;

/**
 * Classe che definisce una qualsiasi coordinata
 */
public class Coordinate implements CoordinateInterface {
    private int x;
    private int y;
    int maxHeight;
    int maxWidth;

    public Coordinate(int x, int y, int maxHeight, int maxWidth) {
        setMaxHeight(maxHeight);
        setMaxWidth(maxWidth);
        setX(x);
        setY(y);
    }

    @Override
    public int[] getPositionAsArray() {
        return new int[]{x, y};
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getMaxHeight() {
        return maxHeight;
    }

    @Override
    public int getMaxWidth() {
        return maxWidth;
    }


    /**
     *
     * @return la "home" del sistema di riferimento, identificata come x/2,y/2
     */
    @Override
    public CoordinateInterface getHome() {
        return new Coordinate(x / 2, y / 2, maxHeight, maxWidth);
    }

    /**
     * Imposta la coordinata del punto
     *
     * @param x coordinata x
     * @param y coordinata y
     */
    @Override
    public void setNewPosition(int x, int y) {
        setX(x);
        setY(y);
    }

    /**
     *
     * @param max massimo valore assumibile in larghezza
     */
    private void setMaxWidth(int max) {
        if (max < 0)
            throw new IllegalArgumentException("Impossibile passare un parametro nullo");
        this.maxWidth = max;
    }


    /**
     *
     * @param max massimo valore assumibile in altezza
     */
    private void setMaxHeight(int max) {
        if (max < 0)
            throw new IllegalArgumentException("Impossibile passare un parametro nullo");
        this.maxHeight = max;
    }

    /**
     *
     * @param x coordinata
     */
    public void setX(int x) {
        if (x > maxHeight || x < 0)
            throw new IllegalArgumentException("Parametro superiore al massimo per il sistema di riferimento");
        this.x = x;
    }

    /**
     *
     * @param y coordinata
     */
    public void setY(int y) {
        if (y > maxWidth || y < 0)
            throw new IllegalArgumentException("Parametro superiore al massimo per il sistema di riferimento");
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordinate that)) return false;
        return getX() == that.getX()
                && getY() == that.getY()
                && getMaxHeight() == that.getMaxHeight()
                && getMaxWidth() == that.getMaxWidth();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY(), getMaxHeight(), getMaxWidth());
    }
}
