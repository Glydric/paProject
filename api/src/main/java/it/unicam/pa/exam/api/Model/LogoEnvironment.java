package it.unicam.pa.exam.api.Model;

import it.unicam.pa.exam.api.Model.Logo.*;
import it.unicam.pa.exam.api.Model.Logo.Cursor;

import java.awt.*;
import java.util.Stack;
import java.util.stream.Collectors;

public class LogoEnvironment implements Environment<Cursor> {
    private Color backgroundColor = Color.white;
    private Cursor cursor;
    private final Stack<ClosedArea> areas = new Stack<>();

    public LogoEnvironment(int height, int width) {
        setCursor(height, width);
        areas.add(new ColoredClosedArea());
    }

    public LogoEnvironment(int height, int width, Color bgColor) {
        this(height, width);
        backgroundColor = bgColor;
    }

    private void addAreaIfNeed() {
        if (areas.peek().isClosed())
            areas.add(new ColoredClosedArea());
    }

    /**
     * Modifica il colore dell'area che stiamo disegnando attualmente
     *
     * @param color il colore
     */
    @Override
    public void setActualAreaColor(Color color) {
        if (!(areas.get(0) instanceof ColoredClosedArea))
            throw new UnsupportedOperationException("L'area attuale non supporta i colori");
        ((ColoredClosedArea) areas.peek()).setColor(color);
    }

    /**
     * Modifica il colore dell'area che stiamo disegnando attualmente
     *
     * @param r il colore rosso
     * @param g il colore verde
     * @param b il colore blu
     */
    @Override
    public void setActualAreaColor(byte r, byte g, byte b) {
        if (!(areas.get(0) instanceof ColoredClosedArea))
            throw new UnsupportedOperationException("L'area attuale non supporta i colori");
        ((ColoredClosedArea) areas.peek()).setColor(r, g, b);
    }

    /**
     * Crea e salva un cursore sulla base dei parametri passati
     *
     * @param height parametro che definisce l'altezza
     * @param width  parametro che definisce la larghezza
     */
    private void setCursor(int height, int width) {
        if (height < 0 || width < 0)
            throw new IllegalArgumentException("x o y non possono essere negativi");
        this.cursor = new Cursor(new LimitedPoint(width, height));
    }

    @Override
    public int getHeight() {
        return cursor.getPosition().getHeight();
    }

    @Override
    public int getWidth() {
        return cursor.getPosition().getWidth();
    }

    @Override
    public void write(int road) {
        Point before = (Point) cursor.getPosition().clone();

        cursor.moveCursor(road);

        if (cursor.getPlot())
            addLine(before, (Point) cursor.getPosition().clone());
    }

    private void addLine(Point p1, Point p2) {
        areas.peek().addLine(new ColoredLine(p1, p2));

        addAreaIfNeed();
    }

    @Override
    public Cursor getCursor() {
        return cursor;
    }

    @Override
    public void setColor(byte r, byte g, byte b) {
        backgroundColor = new Color(r, g, b);
    }

    @Override
    public void clear() {
        backgroundColor = Color.white;
        areas.clear();
        cursor.clear();
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    @Override
    public String toString() {
        return "SIZE " + cursor.getPosition() + " ["
                + backgroundColor.getRed() + ','
                + backgroundColor.getGreen() + ','
                + backgroundColor.getBlue() + "]\n"
                + areas
                    .stream()
                    .map(ClosedArea::toString)
                    .collect(Collectors.joining());
    }
}
