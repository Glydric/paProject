package it.unicam.pa.exam.app.Module;

import it.unicam.pa.exam.api.AngleInterface;

/**
 * La seguente classe definisce la direzione (angolo) di un elemento
 */
public class IntegerAngle implements AngleInterface<Integer> {
    int angle;
    int max;

    public IntegerAngle(int angle, int max) {
        setMax(max);
        setAngle(angle);
    }

    public IntegerAngle(int angle) {
        this(angle, 360);
    }

    public IntegerAngle() {
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
        this.angle = angle < 0
                ? max + angle % max
                : angle % max;

        if (this.angle == 0 && angle != 0)
            this.angle = max;
    }

    public void addAngle(Integer angle) {
        setAngle(this.angle + angle);
    }

    public IntegerAngle getInvertedAngle() {
        return new IntegerAngle(angle + max / 2, max);
    }

    @Override
    public double getDegrees() {
        return Math.PI * angle / 180;
    }
}
