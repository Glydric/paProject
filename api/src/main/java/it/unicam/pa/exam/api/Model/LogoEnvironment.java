package it.unicam.pa.exam.api.Model;

import it.unicam.pa.exam.api.Model.Logo.*;
import it.unicam.pa.exam.api.Model.Logo.Cursor;

import java.awt.*;
import java.util.Stack;

public class LogoEnvironment implements Environment<Cursor> {
    private Color backgroundColor = Color.white;
    private Cursor cursor;
    private final Stack<ClosedArea> areas = new Stack<>();
//    private final List<ClosedArea> areas = new ArrayList<>();

    public LogoEnvironment(int height, int width) {
        setCursor(height, width);
        addAreaIfNeed();
    }

    public LogoEnvironment(int height, int width, Color bgColor) {
        this(height, width);
        backgroundColor = bgColor;
    }


    private void addAreaIfNeed() {
        if (areas
                .stream()
                .noneMatch(ClosedAreaInterface::isClosed))
            areas.add(new ColoredClosedArea());
    }

    public void setActualAreaColor(Color color) {
        if (!(areas.get(0) instanceof ColoredClosedArea))
            throw new UnsupportedOperationException("L'area attuale non supporta i colori");
        ((ColoredClosedArea) areas.get(areas.size() - 1)).setColor(color);
    }

    public void setActualAreaColor(byte r, byte g, byte b) {
        if (!(areas.get(0) instanceof ColoredClosedArea))
            throw new UnsupportedOperationException("L'area attuale non supporta i colori");
        ((ColoredClosedArea) areas.get(areas.size() - 1)).setColor(r, g, b);
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
        this.cursor = new Cursor(new LimitedPoint(0, 0, width, height));
    }

    @Override
    public void write(int road) {
        Point before = (Point) cursor.getPosition().clone();

        cursor.moveCursor(road);

        if (cursor.plot)
            addLine(before, cursor.getPosition());
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
        return "SIZE " +
                "backgroundColor=" + backgroundColor +
                ", cursor=" + cursor +
                ", areas=" + areas +
                '}';
    }
}
