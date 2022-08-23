package it.unicam.pa.exam.api.Model.Logo;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Questa classe salva una lista di linee finchè la stessa non sia chiusa
 */
public class ClosedArea<L extends Line2D> implements ClosedAreaInterface<L> {
    private final List<L> lines = new ArrayList<>();

    /**
     * @return tutte le linee di questa area
     */
    public List<L> getLines() {
        return lines;
    }

    @Override
    public void addLine(L line) {
        if (!line.getP1().equals(line.getP2())
                && (lines.size() == 0 || !isClosed())
        ) {
            lines.add(line);
        }
    }

    /**
     * Esegue una somma degli angoli e delle linee e li mette in relazione
     * per determinare se l'area è chiusa
     *
     * @return true se l'area è chiusa, false altrimenti
     */
    @Override
    public boolean isClosed() {
        return getPoints()
                .stream()
                .distinct()
                .count()
                == lines.size();
    }

    public List<Point2D> getPoints() {
        return Stream.concat(
                lines.stream()
                        .map(Line2D::getP1)
                ,
                lines.stream()
                        .map(Line2D::getP2)
        ).toList();
    }

    public double[] getAllX() {
        return getPoints()
                .stream()
                .mapToDouble(Point2D::getX)
                .toArray();
    }

    public double[] getAllY() {
        return getPoints()
                .stream()
                .mapToDouble(Point2D::getY)
                .toArray();
    }

    @Override
    public String toString() {
        return lines.isEmpty()
                ? ""
                : "<SHAPE>\n" +
                lines
                        .stream()
                        .map(Line2D::toString)
                        .collect(Collectors.joining()) +
                "<SHAPE>\n";
    }
}
