package it.unicam.pa.exam.app.Module;

/**
 * Classe che definisce una coordinata per un sistema di riferimento quadrato
 */
public class SquareLimitedPoint extends LimitedPoint {
    public SquareLimitedPoint(int x, int y, int max) {
        super(x, y, max, max);
    }

    @Override
    public LimitedPoint getHome() {
        return new SquareLimitedPoint(x / 2, y / 2, getHeight());
    }
}
