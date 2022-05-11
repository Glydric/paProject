package it.unicam.pa.exam.api;


import java.util.List;

public interface ClosedAreaInterface {
    /**
     * Aggiunge una linea all'area
     * @param line linea da aggiungere
     */
    void addLine(LineInterface line);

    /**
     * @return una lista di linee
     */
    List<LineInterface> getLines();

    /**
     * conta tutti gli angoli (inizi e fine delle linee)
     * se il numero degli angoli è uguale al numero delle linee allora la figura è chiusa
     * @return true se è chiuso
     */
    boolean isClosed();
}
