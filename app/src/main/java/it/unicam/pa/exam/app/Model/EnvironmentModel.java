package it.unicam.pa.exam.app.Model;

import it.unicam.pa.exam.api.*;

import java.awt.*;
import java.util.ArrayList;

public class EnvironmentModel implements EnvironmentInterface<Cursor> {
    public Color backgroundColor = Color.white;
    private Cursor cursor;
    private final ArrayList<ClosedArea> areas = new ArrayList<>();

    /**
     * Usa l'altezza massima e la larghezza massima da una coordinata passata
     * @param c la coordinata da cui prendere i massimi
     */
    public EnvironmentModel(Coordinate c) {
        this(c.height, c.width);
    }

    public EnvironmentModel(int height, int width) {
        setCursor(height, width);
    }

    public EnvironmentModel(int height, int width, Color bgColor) {
        this(height, width);
        backgroundColor = bgColor;
    }

    @Override
    public void write(int road) {
        cursor.moveCursor(road);
        if (cursor.plot)
            createArea();
    }

    private void createArea() {
        if (areas.isEmpty()
                || areas.stream()
                .noneMatch(ClosedAreaInterface::isClosed))
            areas.add(new ClosedArea());
    }

    /**
     * Crea un cursore sulla base dei parametri passati
     *
     * @param height parametro che definisce l'altezza
     * @param width parametro che definisce la larghezza
     */
    private void setCursor(int height, int width) {
        if (height < 0 || width < 0)
            throw new IllegalArgumentException("x o y non possono essere negativi");
        this.cursor = new Cursor(new Coordinate(0, 0, height, width));
    }

    @Override
    public Cursor getCursor() {
        return cursor;
    }

    public void clear() {
        backgroundColor = Color.white;
        areas.clear();
        cursor.clear();
    }
}
