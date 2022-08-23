package it.unicam.pa.exam.api;

import it.unicam.pa.exam.api.Model.Environment;
import it.unicam.pa.exam.api.Model.Logo.ClosedArea;
import it.unicam.pa.exam.api.Model.LogoEnvironment;
import it.unicam.pa.exam.api.io.EnvironmentLoader;
import it.unicam.pa.exam.api.io.EnvironmentWriter;
import it.unicam.pa.exam.api.io.LogoEnvironmentLoader;
import it.unicam.pa.exam.api.io.LogoEnvironmentWriter;

import java.awt.geom.Line2D;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class Controller<E extends Environment<? extends Line2D>> {
    private final EnvironmentWriter<E> environmentWriter;
    private final EnvironmentLoader<E> environmentLoader;
    private LogoInterpreter<E> interpreter;

    public static Controller<LogoEnvironment> getStandardLogoController(int height, int width){
        return new Controller<>(
                new LogoEnvironmentLoader(),
                new LogoEnvironmentWriter<>(),
                new LogoInterpreter<>(
                        new LogoEnvironment(height,width)
                )
        );
    }
    public Controller(EnvironmentLoader<E> loader, EnvironmentWriter<E> writer, LogoInterpreter<E> interpreter) {
        environmentLoader = loader;
        environmentWriter = writer;
        this.interpreter = interpreter;
    }

    /**
     * Sfrutta i toString per salvare un environment
     *
     * @param file il file in cui salvare
     */
    public void save(File file) throws IOException {
        environmentWriter.writeTo(file, interpreter.environment);
    }

    /**
     * Sfrutta i toString per salvare un environment
     *
     * @param path il path del file in cui salvare
     */
    public void save(Path path) throws IOException {
        environmentWriter.writeTo(path, interpreter.environment);
    }

    /**
     * legge un programma da eseguire da file
     *
     * @param file il file
     * @throws IOException se il file non esiste
     */
    public void load(File file) throws IOException {
        interpreter = new LogoInterpreter<>(environmentLoader.parse(file));
    }

    /**
     * legge un programma da eseguire da file
     *
     * @param path il file
     * @throws IOException se il file non esiste
     */
    public void load(Path path) throws IOException {
        interpreter = new LogoInterpreter<>(environmentLoader.parse(path));
    }

    public void execute(String command) {
        interpreter.execute(command);
    }


    public E getEnvironment() {
        return interpreter.environment;
    }

    public void clear() {
        interpreter.clear();
    }
}
