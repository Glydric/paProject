package it.unicam.pa.exam.app;

import it.unicam.pa.exam.list.CoordinateInterface;

/**
 * Classe che definisce una qualsiasi coordinata
 */
public class Coordinate implements CoordinateInterface {
    private int x;
    private int y;
    int xMaxCoordinate;
    int yMaxCoordinate;

    public Coordinate(int x, int y, int xMax, int yMax) {
        setMaxX(xMax);
        setMaxY(yMax);
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

    /**
     * Imposta la coordinata del punto
     *
     * @param x
     * @param y
     */
    @Override
    public void setPosition(int x, int y) {
        setX(x);
        setY(y);
    }

    /**
     * Setter del massimo valore assumibile da y
     *
     * @param max
     */
    private void setMaxY(int max) {
        if (max < 0)
            throw new IllegalArgumentException("Impossibile passare un parametro nullo");
        this.yMaxCoordinate = max;
    }


    /**
     * Setter del massimo valore assumibile da x
     *
     * @param max
     */
    private void setMaxX(int max) {
        if (max < 0)
            throw new IllegalArgumentException("Impossibile passare un parametro nullo");
        this.xMaxCoordinate = max;
    }

    /**
     * Setter della coordinata x
     *
     * @param x
     */
    public void setX(int x) {
        if (x > xMaxCoordinate || x < 0)
            throw new IllegalArgumentException("Parametro superiore al massimo per il sistema di riferimento");
        this.x = x;
    }

    /**
     * Setter della coordinata y
     *
     * @param y
     */
    public void setY(int y) {
        if (y > yMaxCoordinate || y < 0)
            throw new IllegalArgumentException("Parametro superiore al massimo per il sistema di riferimento");
        this.y = y;
    }
}
