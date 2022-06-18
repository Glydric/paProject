package it.unicam.pa.exam.api.Model.Logo;

import java.awt.*;
import java.util.List;

public interface ClosedAreaInterface<E extends Shape> {
    /**
     * Aggiunge una linea all'area
     * @param line linea da aggiungere
     */
    void addLine(E line);

    /**
     * @return una lista di linee
     */
    List<E> getLines();

    /**
     * conta tutti gli angoli (inizi e fine delle linee)
     * se il numero degli angoli è uguale al numero delle linee allora la figura è chiusa
     * @return true se è chiuso
     */
    boolean isClosed();
}
