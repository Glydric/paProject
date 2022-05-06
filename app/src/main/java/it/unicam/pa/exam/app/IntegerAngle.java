package it.unicam.pa.exam.app;

import it.unicam.pa.exam.api.AngleInterface;

/**
 * La seguente classe definisce la direzione (angolo) di un elemento
 */
public class IntegerAngle implements AngleInterface<Integer> {
    int angle;
    int max;

    IntegerAngle(int angle, int max) {
        setMax(max);
        setAngle(angle);
    }

    IntegerAngle(int angle) {
        this(angle, 360);
    }

    IntegerAngle() {
        this(0);
    }

    private void setMax(int max) {
        if (max < 0)
            throw new IllegalArgumentException("Il massimo non può essere negativo");
        this.max = max;
    }

    /**
     * @return la direzione
     */
    @Override
    public Integer getAngle() {
        return angle;
    }

    /**
     * trasforma un intero in un angolo da 0 a 360 a prescindere dalla sua grandezza
     *
     * @param angle un intero che definisce la direzione [0,360]
     */
    @Override
    public void setAngle(Integer angle) {
        this.angle = Math.abs(angle % max);
    }

    /**
     * @return l'angolo in gradi
     */
    public double getDegrees() {
        return Math.PI * angle / 180;
    }
}
