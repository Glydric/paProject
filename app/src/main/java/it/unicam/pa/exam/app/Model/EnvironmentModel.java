package it.unicam.pa.exam.app.Model;

import it.unicam.pa.exam.api.*;

import java.awt.*;
import java.util.ArrayList;

public class EnvironmentModel implements EnvironmentInterface<Cursor> {
    public Color backgroundColor = Color.white;
    private Cursor cursor;
    private final ArrayList<ClosedArea> areas = new ArrayList<>();

    public EnvironmentModel(Coordinate c) {
        this(c.x, c.y);
    }

    public EnvironmentModel(int x, int y) {
        setCursor(x, y);
    }

    public EnvironmentModel(int x, int y, Color bgColor) {
        this(x, y);
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
     * @param x parametro che definisce l'altezza
     * @param y parametro che definisce la larghezza
     */
    private void setCursor(int x, int y) {
        if (x < 0 || y < 0)
            throw new IllegalArgumentException("x o y non possono essere negativi");
        this.cursor = new Cursor(new Coordinate(0, 0, x, y));
    }

    @Override
    public Cursor getCursor() {
        return cursor;
    }
}
