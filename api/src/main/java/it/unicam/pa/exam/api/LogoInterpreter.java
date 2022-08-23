package it.unicam.pa.exam.api;

import it.unicam.pa.exam.api.Model.Environment;
import it.unicam.pa.exam.api.Model.Logo.IntegerAngle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LogoInterpreter<E extends Environment<?, ?>> implements LogoInterpreterInterface<IntegerAngle> {
    /**
     * environmentModel è accessibile pubblicamente ed è possibile modificare i suoi valori
     * in accordo al relativo contratto, NON è sostituibile in quanto final
     */
    public final E environment;

    public LogoInterpreter(E environment) {
        this.environment = environment;
    }

    /**
     * Prende una stringa e la interpreta per eseguire determinati metodi
     *
     * @param rawCommand il comando logo da interpretare
     */
    public void execute(String rawCommand) throws IllegalArgumentException {
        List<String> commands = List.of(rawCommand.split(" "));

        String command = commands.get(0).toUpperCase();

        if (command.equals("REPEAT")) {
            List<String> commandList = Arrays
                    .stream(rawCommand
                            .split("\\[")[1]
                            .split("]")[0]
                            .split(",")
                    )
                    .map(String::strip)
                    .toList();
            repeat(Integer.parseInt(commands.get(1)), commandList);
        } else {
            int[] values = listToIntArray(commands);
            switch (values.length) {
                case 0 -> noValueSwitcher(command);
                case 1 -> singleIntegerSwitcher(command, values[0]);
                default -> threeIntegerSwitcher(command,
                        values[0],
                        values[1],
                        values[2]);
            }
        }

    }

    private void noValueSwitcher(String command) throws IllegalArgumentException {
        switch (command) {
            case "CLEARSCREEN", "CLEAR", "CS" -> clear();
            case "HOME" -> home();
            case "PENUP", "PU" -> penUp();
            case "PENDOWN", "PD" -> penDown();
            default -> throw new IllegalArgumentException("Comando " +command+" non trovato");
        }
    }

    private void singleIntegerSwitcher(String command, int value) throws IllegalArgumentException {
        switch (command) {
            case "FORWARD", "FD" -> forward(value);
            case "BACK", "BACKWARD", "BK" -> backward(value);
            case "SETPENSIZE" -> setPenSize(value);
            case "RIGHT", "RT" -> right(new IntegerAngle(value));
            case "LEFT", "LT" -> left(new IntegerAngle(value));
            default -> throw new IllegalArgumentException("Comando non trovato");
        }
    }

    private void threeIntegerSwitcher(String command, int value, int value1, int value2) throws
            IllegalArgumentException {
        switch (command) {
            case "SETPENCOLOR", "SETPC" -> setPenColor(
                    value,
                    value1,
                    value2);
            case "SETFILLCOLOR", "SETFC" -> setFillColor(
                    value,
                    value1,
                    value2);
            case "SETSCREENCOLOR", "SETSC" -> setScreenColor(
                    value,
                    value1,
                    value2);
            default -> throw new IllegalArgumentException("Comando non trovato");
        }
    }

    /**
     * Skip first element that is the actual command
     *
     * @param values the list
     * @return an array of int
     */
    private int[] listToIntArray(List<String> values) {
        return values
                .stream()
                .skip(1)
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    /**
     * l'equivalente comando LOGO "forward dist"
     *
     * @param dist la distanza da percorrere
     */
    @Override
    public void forward(int dist) {
        environment.write(dist);
    }

    /**
     * l'equivalente comando LOGO "backward dist"
     *
     * @param dist la distanza da percorrere
     */
    @Override
    public void backward(int dist) {
        forward(-dist);
    }

    /**
     * l'equivalente comando LOGO "left angle"
     *
     * @param angle l'angolo di rotazione da applicare
     */
    @Override
    public void left(IntegerAngle angle) {
        environment
                .getCursor()
                .getDirection()
                .addAngle(angle.getAngle());
    }

    /**
     * l'equivalente comando LOGO "right angle"
     *
     * @param angle l'angolo di rotazione da applicare
     */
    @Override
    public void right(IntegerAngle angle) {
        left(new IntegerAngle(
                        -angle.getAngle()
                )
        );
    }

    /**
     * l'equivalente comando LOGO "clean"
     * Pulisce l'area di lavoro
     */
    @Override
    public void clear() {
        environment.clear();
    }

    /**
     * l'equivalente comando LOGO "home"
     * muove il cursore nella posizione home
     */
    @Override
    public void home() {
        environment.getCursor().goToHome();
    }

    /**
     * l'equivalente comando LOGO "penup"
     * stacca la "penna" dall'area di scrittura
     */
    @Override
    public void penUp() {
        environment.getCursor().setPlot(false);
    }

    /**
     * l'equivalente comando LOGO "pendown"
     * attacca la "penna" all'area di scrittura
     */
    @Override
    public void penDown() {
        environment.getCursor().setPlot(true);
    }

    /**
     * l'equivalente comando LOGO "setpencolor byte byte byte"
     * modifica il colore della penna
     */
    @Override
    public void setPenColor(int r, int g, int b) {
        environment.getCursor().setColor(r, g, b);
    }

    /**
     * l'equivalente comando LOGO "setfillcolor byte byte byte"
     * modifica il colore di riempimento di un'area chiusa
     *
     * @param r indica l'intensità del rosso
     * @param g indica l'intensità del verde
     * @param b indica l'intensità del blu
     */
    @Override
    public void setFillColor(int r, int g, int b) {
        environment.setActualAreaColor(r, g, b);
    }

    /**
     * l'equivalente comando LOGO "setscreencolor byte byte byte"
     * modifica il colore di sfondo
     *
     * @param r indica l'intensità del rosso
     * @param g indica l'intensità del verde
     * @param b indica l'intensità del blu
     */
    @Override
    public void setScreenColor(int r, int g, int b) {
        environment.setColor(r, g, b);
    }

    /**
     * l'equivalente comando LOGO "setpensize size"
     * modifica la dimensione della penna
     *
     * @param size indica la dimensione della penna
     */
    @Override
    public void setPenSize(int size) {
        environment.getCursor().setSize(size);
    }

    /**
     * l'equivalente comando LOGO "repeat num [commands]"
     * modifica il colore di riempimento di un'area chiusa
     *
     * @param num  indica il numero di esecuzioni
     * @param list una lista di comandi da eseguire in ordine
     *             La lista di comandi è una lista di stringhe
     *             da passare successivamente ad execute()
     */
    @Override
    public void repeat(int num, List<String> list) {
        for (int i = 0; i < num; i++)
            list.forEach(this::execute);
    }
}
