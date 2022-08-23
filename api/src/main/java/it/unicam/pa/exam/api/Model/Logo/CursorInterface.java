package it.unicam.pa.exam.api.Model.Logo;

import java.awt.*;

public interface CursorInterface<E extends Angle<? extends Number>> {

    void setSize(int size);

    void setPlot(boolean p);
    Boolean getPlot();

    /**
     * Imposta il cursore nella posizione della propria home
     */
    void goHome();

    E getDirection();

    Point getPosition();

    void setDirection(E newDirection);

    void moveCursor(int road);

    /**
     * Prende in input i parametri del colore e imposta il colore della linea
     *
     * @param r il colore rosso rappresentato come byte
     * @param g il colore verde rappresentato come byte
     * @param b il colore blu rappresentato come byte
     */
    void setColor(int r, int g, int b);
}
