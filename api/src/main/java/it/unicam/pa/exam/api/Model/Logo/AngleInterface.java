package it.unicam.pa.exam.api.Model.Logo;

/**
 * Definisce una direzione tramite un intero che può variare da 0 a 360
 */
public interface AngleInterface<E extends Number> {

    /**
     * modifica la direzione
     * @param angle un intero che definisce la direzione [0,360]
     */
    void setAngle(E angle);

    /**
     * Aggiunge una gradazione all'angolo
     * @param angle l'incremento
     */
    void addAngle(Integer angle);

    /**
     * @return l'angolo in gradi
     */
    double getRadians();


}
