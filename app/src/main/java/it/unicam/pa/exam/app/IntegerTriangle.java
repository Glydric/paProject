package it.unicam.pa.exam.app;

import it.unicam.pa.exam.api.AngleInterface;
import it.unicam.pa.exam.api.TriangleInterface;

/**
 * Definisce un triangolo di tipo intero,
 * ci consente di ottenere un cateto da ipotenusa + angolo
 */
public class IntegerTriangle implements TriangleInterface<Integer> {
    private int horizontalCat;
    private int verticalCat;
    private int ipo;
    IntegerAngle angle;

    /*
     * La somma degli angoli forma 180º
     * quindi 180-90=90 è la somma degli altri angoli
     * se un angolo è di xº l'altro sarà di 90-xº
     *
     */

    public IntegerTriangle(int ipo, int angle) {
        setIpo(ipo);
        setAngle(angle);
    }

    public IntegerTriangle(int ipo, AngleInterface<Integer> angle) {
        this(ipo, angle.getAngle());
    }

    public IntegerTriangle(int c1, int c2, int ipo, int angle) {
        setHorizontalCat(c1);
        setVerticalCat(c2);
        setIpo(ipo);
        setAngle(angle);
    }

    @Override
    public void setHorizontalCat(Integer x) {
        if (x < 0)
            throw new IllegalArgumentException("Il cateto 1 non può essere negativo");
        horizontalCat = x;
    }

    @Override
    public void setVerticalCat(Integer x) {
        if (x < 0)
            throw new IllegalArgumentException("Il cateto 2 non può essere negativo");
        verticalCat = x;
    }

    @Override
    public void setIpo(Integer x) {
        if (x < 0)
            throw new IllegalArgumentException("l'ipotenusa non può essere negativa");
        ipo = x;
    }

    @Override
    public void setAngle(Integer angle) {
        if (angle % 90 == 0)
            throw new IllegalArgumentException("l'angolo di un triangolo non può essere 0 o multiplo di 90");
        this.angle = new IntegerAngle(angle, 90);
    }

    @Override
    public Integer getHorizontalCat() {
        return horizontalCat;
    }

    @Override
    public Integer getVerticalCat() {
        return verticalCat;
    }

    @Override
    public Integer getIpo() {
        return ipo;
    }

    @Override
    public Integer getAngle() {
        return angle.getAngle();
    }


    /**
     * @return il cateto orizzontale calcolato usando ipotenusa ed angolo
     */
    public Integer calcHorizontalCatFromDegrees() {
        if (angle.getDegrees() != 0)
            return (int) (ipo / Math.sin(angle.getDegrees()));
        return 0;
    }

    /**
     * @return il cateto verticale calcolato usando ipotenusa ed angolo
     */
    @Override
    public Integer calcVerticalCatFromDegrees() {
        return (int) (ipo / Math.sin(Math.PI / 2 - angle.getDegrees()));
    }
}
