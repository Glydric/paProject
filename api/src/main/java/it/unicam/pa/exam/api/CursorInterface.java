package it.unicam.pa.exam.api;

import java.awt.*;

public interface CursorInterface<E extends AngleInterface<?>> {

    E getDirection();

    Point getPosition();

    void setDirection(E newDirection);

    void moveCursor(int road);
}
