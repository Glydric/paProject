package it.unicam.pa.exam.app.Model;

/**
 * Classe che definisce una coordinata per un sistema di riferimento quadrato
 */
public class SquareCoordinate extends Coordinate {
    public SquareCoordinate(int x, int y, int max) {
        super(x, y, max, max);
    }

    @Override
    public Coordinate getHome() {
        return new SquareCoordinate(x / 2, y / 2, height);
    }
}
