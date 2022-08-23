package it.unicam.pa.exam.app;

@FunctionalInterface
public interface ThrowableInterface<T, E extends Exception> {
    void execute(T value) throws E;
}
