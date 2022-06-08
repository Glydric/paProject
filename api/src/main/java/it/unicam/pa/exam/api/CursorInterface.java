package it.unicam.pa.exam.api;

import java.awt.*;

public interface CursorInterface<E extends AngleInterface<?>> {
    boolean plot = false;

    E getDirection();

    Point getPosition();

    void setDirection(E newDirection);

    void moveCursor(int road);
}
