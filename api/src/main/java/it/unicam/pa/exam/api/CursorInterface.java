package it.unicam.pa.exam.api;

public interface CursorInterface {
    boolean plot = false;

    CoordinateInterface getPosition();

    void setDirection(AngleInterface<Integer> newDirection);

    void moveCursor(int road);
}
