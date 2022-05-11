package it.unicam.pa.exam.api;

public interface CursorInterface {
    boolean plot = false;

    AngleInterface<Integer> getDirection();

    CoordinateInterface getPosition();

    void setDirection(AngleInterface<Integer> newDirection);

    void moveCursor(int road);
}
