package it.unicam.pa.exam.api.Model.Logo;

import java.awt.*;
import java.util.Objects;

/**
 * Classe che definisce una qualsiasi coordinata
 */
public class LimitedPoint extends Point {
    private int height;
    private int width;

    /**
     * Definisce un punto con dei limiti definendo ogni componente
     *
     * @param x      la coordinata x
     * @param y      la coordinata y
     * @param height l'altezza massima
     * @param width  la larghezza massima
     */
    public LimitedPoint(int x, int y, int width, int height) {
        setMaxHeight(height);
        setMaxWidth(width);
        setLocation(x, y);
    }

    /**
     * Definisce un punto con dei limiti con il punto di inizio posto alla home
     *
     * @param height l'altezza massima
     * @param width  la larghezza massima
     */
    public LimitedPoint(int width, int height) {
        this(width / 2, height / 2, width, height);
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    /**
     * @return la "home" del sistema di riferimento, identificata come x/2,y/2
     */
    public Point getHome() {
        return new Point(width / 2, height / 2);
    }

    /**
     * Imposta la coordinata del punto CONVERTENDO IL DOUBLE IN INTEGER
     *
     * @param x coordinata x
     * @param y coordinata y
     */
    @Override
    public void setLocation(double x, double y) {
        setLocation((int) x, (int) y);
    }

    /**
     * prende il massimo tra 0 e i valori
     * contemporaneamente prende il minimo tra il value ed il suo max
     * cosi facendo se il valore x o y è minore di 0 prendiamo 0, se è maggiore di max prendiamo max
     *
     * @param value il valore di riferimento
     * @return il risultato dell'operazione
     */
    private int getValueInRangeZeroMax(int value, int max) {
        return Math.max(0, Math.min(value, max));
    }

    /**
     * Imposta la coordinata del punto
     *
     * @param x coordinata x
     * @param y coordinata y
     */
    @Override
    public void setLocation(int x, int y) {
        // prende il massimo tra 0 e i valori, contemporaneamente prende il minimo tra il valore ed il suo max
        // cosi facendo se il valore x o y è minore di 0 prendiamo 0, se è maggiore di max prendiamo max
        super.setLocation(
                getValueInRangeZeroMax(x, width),
                getValueInRangeZeroMax(y, height)
        );
    }

    /**
     * Imposta la coordinata del punto
     *
     * @param p coordinata point
     */
    @Override
    public void setLocation(Point p) {
        setLocation(p.x, p.y);
    }

    /**
     * Prende i seguenti parametri ed incrementa il valore della posizione
     *
     * @param addX parametro x da aggiungere
     * @param addY parametro y da aggiungere
     */
    public void incrementLocation(int addX, int addY) {
        setLocation(
                getX() + addX,
                getY() + addY
        );
    }

    /**
     * @param maxWidth massimo valore assumibile in larghezza
     */
    private void setMaxWidth(int maxWidth) {
        if (maxWidth < 0)
            throw new IllegalArgumentException("Impossibile passare un parametro nullo");
        this.width = maxWidth;
    }

    /**
     * @param maxHeight massimo valore assumibile in altezza
     */
    private void setMaxHeight(int maxHeight) {
        if (maxHeight < 0)
            throw new IllegalArgumentException("Impossibile passare un parametro nullo");
        this.height = maxHeight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LimitedPoint that)) return false;
        return getX() == that.getX()
                && getY() == that.getY()
                && getHeight() == that.getHeight()
                && getWidth() == that.getWidth();
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, height, width);
    }


    @Override
    public String toString() {
        return "(" + x + ";" + y + ")";
    }

    public String maxToString() {
        return "<" + height + "> <" + width + ">";
    }
}
