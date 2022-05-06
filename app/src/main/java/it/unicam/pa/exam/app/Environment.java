package it.unicam.pa.exam.app;

import it.unicam.pa.exam.api.*;

import java.awt.*;
import java.util.ArrayList;

public class Environment implements EnvironmentInterface {
    public Color backgroundColor;
    public CursorInterface cursor;
    private final ArrayList<ClosedAreaInterface> areas = new ArrayList<>();

    Environment(int height, int width, Color bgColor) {
        backgroundColor = bgColor;
        setCursor(height, width);
    }

    /**
     * @return la posizione del cursore
     */
    @Override
    public CoordinateInterface getCursorCoordinate() {
        return cursor.getPosition();
    }

    @Override
    public int getHeight() {
        return getCursorCoordinate().getMaxHeight();
    }

    @Override
    public int getWidth() {
        return getCursorCoordinate().getMaxWidth();
    }

    @Override
    public void write(int road) {
        cursor.moveCursor(road);
        if (cursor.plot)
            createArea();

    }

    private void createArea() {
        if(areas.isEmpty()
                || areas.stream()
                        .noneMatch(ClosedAreaInterface::isClosed))
            areas.add(new ClosedArea());
    }

    /**
     * Crea un cursore sulla base dei parametri passati
     *
     * @param height parametro che definisce l'altezza
     * @param width  parametro che definisce la larghezza
     */
    private void setCursor(int height, int width) {
        if (height < 0 || width < 0)
            throw new IllegalArgumentException("x o y non possono essere negativi");
        this.cursor = new Cursor(new Coordinate(0, 0, height, width));
    }
}
