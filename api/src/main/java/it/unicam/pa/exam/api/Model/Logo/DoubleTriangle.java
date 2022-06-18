package it.unicam.pa.exam.api.Model.Logo;

/**
 * Definisce un triangolo di tipo intero,
 * ci consente di ottenere un cateto da ipotenusa + angolo
 */
public class DoubleTriangle implements TriangleInterface<Double> {
    private double horizontalCat;
    private double verticalCat;
    private double ipo;
    IntegerAngle angle;

    /*
     * La somma degli angoli forma 180º
     * quindi 180-90=90 è la somma degli altri angoli
     * se un angolo è di xº l'altro sarà di 90-xº
     *
     */

    public DoubleTriangle(double ipo, int angle) {
        setIpo(ipo);
        setAngle(angle);
    }

    public DoubleTriangle(double ipo, IntegerAngle angle) {
        this(ipo, angle.getNormalizedAngle());
    }

    public DoubleTriangle(double c1, double c2, double ipo, int angle) {
        setHorizontalCat(c1);
        setVerticalCat(c2);
        setIpo(ipo);
        setAngle(angle);
    }

    @Override
    public void setHorizontalCat(Double x) {
        if (x < 0)
            throw new IllegalArgumentException("Il cateto 1 non può essere negativo");
        horizontalCat = x;
    }

    @Override
    public void setVerticalCat(Double x) {
        if (x < 0)
            throw new IllegalArgumentException("Il cateto 2 non può essere negativo");
        verticalCat = x;
    }

    @Override
    public void setIpo(Double x) {
        if (x < 0)
            throw new IllegalArgumentException("l'ipotenusa non può essere negativa");
        ipo = x;
    }

    /**
     * Imposta l'angolo impedendo però di usare angoli di 90º, 180º, 270º, 360º, genererebbero triangoli infiniti
     *
     * @param angle l'angolo da applicare
     */
    @Override
    public void setAngle(int angle) {
        if (angle % 90 == 0)
            throw new IllegalArgumentException("l'angolo di un triangolo non può essere 0 o multiplo di 90");
        this.angle = new IntegerAngle(angle, 90);
    }

    @Override
    public Double getHorizontalCat() {
        return horizontalCat;
    }

    @Override
    public Double getVerticalCat() {
        return verticalCat;
    }

    @Override
    public Double getIpo() {
        return ipo;
    }

    @Override
    public int getAngle() {
        return angle.getAngle();
    }

    /**
     * Sovrascrive il vecchio valore di vertical
     *
     * @return il cateto orizzontale calcolato usando ipotenusa ed angolo
     */
    public Double calcSaveVertical() {
        verticalCat = calcVertical();
        return verticalCat;
    }

    /**
     * Sovrascrive il vecchio valore di horizontal
     *
     * @return il cateto orizzontale calcolato usando ipotenusa ed angolo
     */
    public Double calcSaveHorizontal() {
        horizontalCat = calcHorizontal();
        return horizontalCat;
    }

    /**
     * @return il cateto verticale calcolato usando ipotenusa ed angolo
     */
    @Override
    public Double calcVertical() {
        return ipo * Math.sin(angle.getRadians()) / Math.sin(Math.PI / 2);
    }

    /**
     * @return il cateto orizzontale calcolato usando ipotenusa ed angolo
     */
    @Override
    public Double calcHorizontal() {
        return ipo * Math.sin(Math.PI / 2 - angle.getRadians()) / Math.sin(Math.PI / 2);
    }


}
