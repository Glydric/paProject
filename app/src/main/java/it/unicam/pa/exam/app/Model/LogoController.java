package it.unicam.pa.exam.app.Model;

import it.unicam.pa.exam.api.LogoInterpreterInterface;

import java.util.ArrayList;
import java.util.List;

public class LogoController implements LogoInterpreterInterface<IntegerAngle> {
    /**
     * environmentModel è accessibile da chiunque ed è possibile modificare i suoi valori
     * in accordo al relativo contratto, NON è sostituibile in quanto final
     */
    public final EnvironmentModel environment;

    public LogoController(EnvironmentModel environment) {
        this.environment = environment;
    }

    public LogoController(Coordinate c) {
        this(c.x, c.y);//todo check if using x or height and y or width
    }

    public LogoController(int height, int width) {
        this.environment = new EnvironmentModel(height, width);
    }

    /**
     * Prende una stringa e la interpreta per eseguire determinati metodi
     *
     * @param rawCommand il comando logo da interpretare
     */
    public void execute(String rawCommand) {
        ArrayList<String> commands = new ArrayList<>(
                List.of(
                        rawCommand.split(" ")
                )
        );
        int[] values = getIntArrayFromList(commands);
        switch (values.length) {
            case 0 -> noValueSwitcher(commands.get(0).toUpperCase());
            case 1 -> singleIntegerSwitcher(commands.get(0).toUpperCase(), values[0]);
            default -> threeByteSwitcher(commands.get(0).toUpperCase(),
                    (byte) values[0],
                    (byte) values[1],
                    (byte) values[2]);
        }
    }

    private void noValueSwitcher(String command) {
        switch (command) {
            case "CLEARSCREEN", "CS" -> clear();
            case "HOME" -> home();
            case "PENUP", "PU" -> penUp();
            case "PENDOWN", "PD" -> penDown();
        }
    }

    private void singleIntegerSwitcher(String command, int value) {
        switch (command) {
            case "FORWARD", "FD" -> forward(value);
            case "BACK", "BACKWARD", "BK" -> backward(value);
            case "SETPENSIZE" -> setPenSize(value);
            case "RIGHT", "RT" -> right(new IntegerAngle(value));
            case "LEFT", "LT" -> left(new IntegerAngle(value));
        }
    }

    private void threeByteSwitcher(String command, byte value, byte value1, byte value2) {
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
        }
    }


    /**
     * Skip first element that is the actual command
     *
     * @param values the list
     * @return an array of int
     */
    private int[] getIntArrayFromList(List<String> values) {
        return values
                .stream()
                .skip(1)
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    /**
     * l'equivalente comando LOGO "forward dist"
     * @param dist la distanza da percorrere
     */
    @Override
    public void forward(int dist) {
        environment.getCursor().moveCursor(dist);
    }

    /**
     * l'equivalente comando LOGO "backward dist"
     * @param dist la distanza da percorrere
     */
    @Override
    public void backward(int dist) {
        forward(-dist);
    }

    /**
     * l'equivalente comando LOGO "left angle"
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

    }

    /**
     * l'equivalente comando LOGO "penup"
     * stacca la "penna" dall'area di scrittura
     */
    @Override
    public void penUp() {

    }

    /**
     * l'equivalente comando LOGO "pendown"
     * attacca la "penna" all'area di scrittura
     */
    @Override
    public void penDown() {

    }

    /**
     * l'equivalente comando LOGO "setpencolor byte byte byte"
     * modifica il colore della penna
     */
    @Override
    public void setPenColor(byte r, byte g, byte b) {

    }

    /**
     * l'equivalente comando LOGO "setfillcolor byte byte byte"
     * modifica il colore di riempimento di un'area chiusa
     * @param r indica l'intensità del rosso
     * @param g indica l'intensità del verde
     * @param b indica l'intensità del blu
     */
    @Override
    public void setFillColor(byte r, byte g, byte b) {

    }

    /**
     * l'equivalente comando LOGO "setscreencolor byte byte byte"
     * modifica il colore di sfondo
     * @param r indica l'intensità del rosso
     * @param g indica l'intensità del verde
     * @param b indica l'intensità del blu
     */
    @Override
    public void setScreenColor(byte r, byte g, byte b) {

    }

    /**
     * l'equivalente comando LOGO "setpensize size"
     * modifica la dimensione della penna
     * @param size indica la dimensione della penna
     */
    @Override
    public void setPenSize(int size) {

    }

    /**
     * l'equivalente comando LOGO "repeat num [commands]"
     * modifica il colore di riempimento di un'area chiusa
     * @param num  indica il numero di esecuzioni
     * @param list una lista di comandi da eseguire in ordine
     * La lista di comandi è una lista di stringhe
     * da passare successivamente ad execute()
     */
    @Override
    public void repeat(int num, List<String> list) {

    }
}
