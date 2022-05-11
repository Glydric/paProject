package it.unicam.pa.exam.app;

import it.unicam.pa.exam.api.CoordinateInterface;

/**
 * Classe che definisce una coordinata per un sistema di riferimento quadrato
 */
public class SquareCoordinate extends Coordinate {
    public SquareCoordinate(int height, int width, int max) {
        super(height, width, max, max);
    }

    @Override
    public CoordinateInterface getHome() {
        return new SquareCoordinate(getHeight() / 2, getWidth() / 2, maxHeight);
    }
}
