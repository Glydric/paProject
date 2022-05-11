package it.unicam.pa.exam.api;

/**
 * Definisce una coordinata
 */
public interface CoordinateInterface {

    /**
     * Ci ritorna un array delle posizioni
     *
     * @return array di interi
     */
    int[] getPositionAsArray();

    /**
     * Setter per x e y
     */
    void setNewPosition(int height, int width);

    int getHeight();

    int getWidth();

    int getMaxHeight();

    int getMaxWidth();

    CoordinateInterface getHome();

}
