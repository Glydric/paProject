package it.unicam.pa.exam.api.io;

import it.unicam.pa.exam.api.Model.Environment;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@FunctionalInterface
public interface EnvironmentWriter<E extends Environment<?>> {

    /**
     * Trasforma l'environment in una stringa che lo rappresenta
     * @param env l'environment
     * @return una stringa che lo rappresenta
     */
    String toString(E env);

    /**
     * scrive un'environment in un file
     * @param path il percorso del file in cui scrivere
     * @param env l'environment
     * @throws IOException se vi è un'errore nella scrittura
     */
    default void writeTo(Path path, E env) throws IOException {
        Files.write(path, toString(env).getBytes());
    }

    /**
     * scrive un'environment in un file
     * @param file il file da cui estrarre il percorso in cui scrivere
     * @param env l'environment
     * @throws IOException se vi è un'errore nella scrittura
     */
    default void writeTo(File file, E env) throws IOException {
        writeTo(file.toPath(), env);
    }
}
