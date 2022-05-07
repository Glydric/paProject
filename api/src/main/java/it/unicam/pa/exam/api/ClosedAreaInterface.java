package it.unicam.pa.exam.api;


import java.util.List;

public interface ClosedAreaInterface {
    void addLine(LineInterface line);
    List<LineInterface> getLines();
    boolean isClosed();
}
