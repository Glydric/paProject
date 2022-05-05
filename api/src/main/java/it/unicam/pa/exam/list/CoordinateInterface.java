package it.unicam.pa.exam.list;

/**
 * Definisce una coordinata
 */
public interface CoordinateInterface {

    /**
     * Ci ritorna un array delle posizioni
     * @return array di interi
     */
    int[] getPositionAsArray();

    /**
     * Setter per x e y
     */
    void setPosition(int x, int y);

    int getX();

    int getY();
}
