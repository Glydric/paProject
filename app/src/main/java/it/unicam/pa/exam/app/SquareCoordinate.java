package it.unicam.pa.exam.app;

import it.unicam.pa.exam.api.CoordinateInterface;

/**
 * Classe che definisce una coordinata per un sistema di riferimento quadrato
 */
public class SquareCoordinate extends Coordinate {
    public SquareCoordinate(int x, int y, int max) {
        super(x, y, max, max);
    }

    @Override
    public CoordinateInterface getHome() {
        return new SquareCoordinate(getX() / 2, getY() / 2, maxHeight);
    }
}
