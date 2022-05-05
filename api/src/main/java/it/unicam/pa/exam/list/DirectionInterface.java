package it.unicam.pa.exam.list;

/**
 * Definisce una direzione tramite un intero che può variare da 0 a 360
 */
public interface DirectionInterface {
    /**
     *
     * @return la direzione
     */
    int getDirection();

    /**
     * modifica la direzione
     * @param direction un intero che definisce la direzione [0,360]
     */
    void setDirection(int direction);
}
