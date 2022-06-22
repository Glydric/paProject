package it.unicam.pa.exam.api.io;

import it.unicam.pa.exam.api.Model.Environment;
import it.unicam.pa.exam.api.Model.Logo.CursorInterface;

import java.awt.geom.Line2D;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@FunctionalInterface
public interface EnvironmentLoader<E extends Environment<? extends CursorInterface<?>, ? extends Line2D>> {

    /**
     * Trasforma una stringa in un environment
     *
     * @param content la stringa contenente le informazioni per ricostruire l'environment
     * @return l'environment estratto
     * @throws IOException se abbiamo un'errore nel parsing
     */
    E parse(String content) throws IOException;

    /**
     * @param path il file da cui estrarre l'environment
     * @return l'environment estratto
     * @throws IOException se vi è un'errore nell'apertura del file o nel parsing
     */
    default E parse(Path path) throws IOException {
        return parse(Files.readString(path));
    }

    /**
     * @param file il file da cui estrarre il path e successivamente l'environment
     * @return l'environment estratto
     * @throws IOException se vi è un'errore nell'apertura del file o nel parsing
     */
    default E parse(File file) throws IOException {
        return parse(file.toPath());
    }
}
