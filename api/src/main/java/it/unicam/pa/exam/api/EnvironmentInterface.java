package it.unicam.pa.exam.api;

public interface EnvironmentInterface<E extends CursorInterface<?>> {
    void write(int road);

    E getCursor();
}
