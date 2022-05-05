package it.unicam.pa.exam.app;

/**
 * Classe che definisce una coordinata per un sistema di riferimento quadrato
 */
public class SquareCoordinate extends Coordinate {
    public SquareCoordinate(int x, int y, int max) {
        super(x, y, max, max);
    }
}
