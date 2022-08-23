package it.unicam.pa.exam.api.Model;

import it.unicam.pa.exam.api.Model.Logo.ClosedArea;
import it.unicam.pa.exam.api.Model.Logo.Cursor;

import java.awt.*;
import java.util.List;

public interface Environment<L extends Shape> {
    void setAreaColor(Color color);

    void setAreaColor(int r, int g, int b);

    int getHeight();

    int getWidth();

    void write(int road);

    Cursor getCursor();

    void setColor(int r, int g, int b);

    void clear();

    List<L> getLines();

    List<? extends ClosedArea<?>> getAreas();
}
