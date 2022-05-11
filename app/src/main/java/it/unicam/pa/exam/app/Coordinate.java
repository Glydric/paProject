package it.unicam.pa.exam.app;

import it.unicam.pa.exam.api.CoordinateInterface;

import java.util.Objects;

/**
 * Classe che definisce una qualsiasi coordinata
 */
public class Coordinate implements CoordinateInterface {
    private int height;
    private int width;
    int maxHeight;
    int maxWidth;

    public Coordinate(int height, int width, int maxHeight, int maxWidth) {
        setMaxHeight(maxHeight);
        setMaxWidth(maxWidth);
        setHeight(height);
        setWidth(width);
    }

    @Override
    public int[] getPositionAsArray() {
        return new int[]{height, width};
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getWidth() {
        return width;
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
     * @return la "home" del sistema di riferimento, identificata come x/2,y/2
     */
    @Override
    public CoordinateInterface getHome() {
        return new Coordinate(height / 2, width / 2, maxHeight, maxWidth);
    }

    /**
     * Imposta la coordinata del punto
     *
     * @param height coordinata x
     * @param width  coordinata y
     */
    @Override
    public void setNewPosition(int height, int width) {
        setHeight(height);
        setWidth(width);
    }

    /**
     * @param max massimo valore assumibile in larghezza
     */
    private void setMaxWidth(int max) {
        if (max < 0)
            throw new IllegalArgumentException("Impossibile passare un parametro nullo");
        this.maxWidth = max;
    }


    /**
     * @param max massimo valore assumibile in altezza
     */
    private void setMaxHeight(int max) {
        if (max < 0)
            throw new IllegalArgumentException("Impossibile passare un parametro nullo");
        this.maxHeight = max;
    }

    /**
     * @param height coordinata
     */
    public void setHeight(int height) {
        if (height < 0)
            throw new IllegalArgumentException("Parametro height negativo");

        this.height = Math.min(height, maxHeight);
    }

    /**
     * @param width coordinata
     */
    public void setWidth(int width) {
        if (width < 0)
            throw new IllegalArgumentException("Parametro width negativo");
        this.width = Math.min(width, maxWidth);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordinate that)) return false;
        return getHeight() == that.getHeight()
                && getWidth() == that.getWidth()
                && getMaxHeight() == that.getMaxHeight()
                && getMaxWidth() == that.getMaxWidth();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getHeight(), getWidth(), getMaxHeight(), getMaxWidth());
    }
}
