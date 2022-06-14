package it.unicam.pa.exam.app.Module;

import it.unicam.pa.exam.api.ClosedAreaInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ClosedArea implements ClosedAreaInterface<ColoredLine> {
    private final List<ColoredLine> coloredLines = new ArrayList<>();

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
}
