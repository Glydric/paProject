package it.unicam.pa.exam.app;

import it.unicam.pa.exam.api.ClosedAreaInterface;
import it.unicam.pa.exam.api.LineInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ClosedArea implements ClosedAreaInterface {
    private final List<LineInterface> lines = new ArrayList<>();

    public List<LineInterface> getLines() {
        return lines;
    }

    @Override
    public void addLine(LineInterface line) {
        if (lines.size() == 0 || !isClosed()) {
            lines.add(line);
        }
    }

    @Override
    public boolean isClosed() {
        /*
            conta tutti gli angoli (inizi e fine delle linee)
            se il numero degli angoli è uguale al numero delle linee allora la figura è chiusa
         */

        return Stream.concat(
                        lines.stream()
                                .map(LineInterface::getBegin)
                        ,
                        lines.stream()
                                .map(LineInterface::getEnd))
                .distinct()
                .count()
                == lines.size();
    }

}
