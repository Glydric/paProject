package it.unicam.pa.exam.api;

public interface EnvironmentInterface {
    CoordinateInterface getCursorCoordinate();
    int getHeight();
    int getWidth();

    void write(int road);
}
