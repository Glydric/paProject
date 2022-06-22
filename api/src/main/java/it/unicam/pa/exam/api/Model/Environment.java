package it.unicam.pa.exam.api.Model;

import it.unicam.pa.exam.api.Model.Logo.ClosedArea;
import it.unicam.pa.exam.api.Model.Logo.CursorInterface;

import java.awt.*;
import java.awt.geom.Line2D;
import java.util.List;

public interface Environment<E extends CursorInterface<?>, L extends Line2D> {
    void setActualAreaColor(Color color);

    void setActualAreaColor(int r, int g, int b);

    int getHeight();

    int getWidth();

    void write(int road);

    E getCursor();

    void setColor(int r, int g, int b);

    void clear();

    List<L> getLines();

    List<? extends ClosedArea> getClosedAreas();
}
