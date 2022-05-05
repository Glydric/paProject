package it.unicam.pa.exam.list;

/**
 * Definisce una coordinata
 */
public interface Coordinate {
    int x = 0;
    int y = 0;
    int maxCoordinate = 0;

    /**
     * Ci ritorna un array delle posizioni
     * @return array di interi
     */
    int[] getPosition();

    /**
     * Setter per x e y
     */
    void setPosition(int x, int y);
}
