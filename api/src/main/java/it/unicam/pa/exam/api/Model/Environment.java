package it.unicam.pa.exam.api.Model;

import it.unicam.pa.exam.api.Model.Logo.CursorInterface;

import java.awt.*;

public interface Environment<E extends CursorInterface<?>> {
    void setActualAreaColor(Color color);

    void setActualAreaColor(byte r, byte g, byte b);

    int getHeight();

    int getWidth();

    void write(int road);

    E getCursor();

    void setColor(byte r, byte g, byte b);

    void clear();

}
