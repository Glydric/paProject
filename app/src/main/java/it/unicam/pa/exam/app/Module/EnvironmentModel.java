package it.unicam.pa.exam.app.Module;

import it.unicam.pa.exam.api.*;

import java.awt.*;
import java.util.ArrayList;

public class EnvironmentModel implements EnvironmentInterface<Cursor> {
    private Color backgroundColor = Color.white;
    private Cursor cursor;
    private final ArrayList<ColoredClosedArea> areas = new ArrayList<>();

    /**
     * Usa l'altezza massima e la larghezza massima da una coordinata passata
     *
     * @param limit la coordinata da cui prendere i massimi
     */
    public EnvironmentModel(LimitedPoint limit) {
        this(limit.getHeight(), limit.getWidth());
    }

    public EnvironmentModel(int height, int width) {
        setCursor(height, width);
    }

    public EnvironmentModel(int height, int width, Color bgColor) {
        this(height, width);
        backgroundColor = bgColor;
    }


    private void createArea() {
        if (areas.isEmpty()
                || areas
                .stream()
                .noneMatch(ClosedAreaInterface::isClosed))
            areas.add(new ColoredClosedArea());
    }

    public void setActualAreaColor(Color color) {
        areas.get(areas.size() - 1).setColor(color);
    }

    public void setActualAreaColor(byte r, byte g, byte b) {
        areas.get(areas.size() - 1).setColor(r, g, b);
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
        this.cursor = new Cursor(new LimitedPoint(0, 0, height, width));
    }

    @Override
    public void write(int road) {
        cursor.moveCursor(road);
        if (cursor.plot)
            createArea();
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
}
