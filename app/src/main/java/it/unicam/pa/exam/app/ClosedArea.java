package it.unicam.pa.exam.app;

import it.unicam.pa.exam.api.ClosedAreaInterface;
import it.unicam.pa.exam.api.LineInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ClosedArea implements ClosedAreaInterface {

    private final List<LineInterface> lines = new ArrayList<>();

    public void addLine(LineInterface line) {
        if (!isClosed()) {
            lines.add(line);
        }
    }

    @Override
    public boolean isClosed() {
        /*
            conta tutti i primi elementi delle linee(angoli)
            se il numero degli angoli è uguale al numero delle linee allora la figura è completa
         */

        return lines.stream()
                .map(LineInterface::getBegin)
                .count()
                == lines.size();
        //todo da controllare
    }

}
