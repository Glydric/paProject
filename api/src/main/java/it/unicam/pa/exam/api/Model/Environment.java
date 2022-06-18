package it.unicam.pa.exam.api.Model;

import it.unicam.pa.exam.api.Model.Logo.CursorInterface;

public interface Environment<E extends CursorInterface<?>> {
    void write(int road);

    E getCursor();

    void setColor(byte r, byte g, byte b);

    void clear();

}
