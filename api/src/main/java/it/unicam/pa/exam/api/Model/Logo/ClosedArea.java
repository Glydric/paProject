package it.unicam.pa.exam.api.Model.Logo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ClosedArea implements ClosedAreaInterface<ColoredLine> {
    private final List<ColoredLine> coloredLines = new ArrayList<>();

    /**
     * @return tutte le linee di questa area
     */
    public List<ColoredLine> getLines() {
        return coloredLines;
    }

    @Override
    public void addLine(ColoredLine coloredLine) {
        if (coloredLines.size() == 0 || !isClosed()) {
            coloredLines.add(coloredLine);
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
        return Stream.concat(
                        coloredLines.stream()
                                .map(ColoredLine::getP1)
                        ,
                        coloredLines.stream()
                                .map(ColoredLine::getP2))
                .distinct()
                .count()
                == coloredLines.size();
    }

    @Override
    public String toString() {
        return coloredLines.isEmpty()
                ? ""
                : "<SHAPE>\n" +
                coloredLines
                        .stream()
                        .map(ColoredLine::toString)
                        .collect(Collectors.joining()) +
                "<SHAPE>\n";
    }
}
