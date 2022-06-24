package it.unicam.pa.exam.api.Model.Logo;

/**
 * La seguente classe definisce la direzione (angolo) di un elemento
 */
public class IntegerAngle implements Angle<Integer> {
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

    @Override
    public void addAngle(Integer angle) {
        setAngle(this.angle + angle);
    }

    public IntegerAngle getInvertedAngle() {
        return new IntegerAngle(angle + max / 2, max);
    }

    @Override
    public double getRadians() {
        return Math.PI * angle / 180;
    }

    @Override
    public void clear() {
        angle = 0;
    }

    /**
     * @return un angolo normalizzato a partire dall'angolo che abbiamo.
     * Per normalizzato si intende che è calcolabile da un triangolo, cioè è <90
     */
    public int getNormalizedAngle() {
        switch (getActualQuadrant()) {
            case 1 -> {
                return angle;
            }
            case 3 -> {
                return angle % 90;
            }
            default -> {
                return 90 - angle % 90;
            }
        }
    }

    /**
     * 2 | 1<br>
     * -----<br>
     * 3 | 4
     *
     * @return il quadrante in cui si trova il rispettivo angolo (immaginando un piano cartesiano)
     */
    public short getActualQuadrant() {
        if (angle < 90)
            return 1;
        if (90 < angle && angle < 180)
            return 2;
        if (180 < angle && angle < 270)
            return 3;
        return 4;
    }
}
