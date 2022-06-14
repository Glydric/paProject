package it.unicam.pa.exam.api;

public interface EnvironmentInterface<E extends CursorInterface<?>> {
    void write(int road);

    E getCursor();

    void setColor(byte r, byte g, byte b);

    void clear();
}
