package it.unicam.pa.exam.app.Model;

import it.unicam.pa.exam.api.LogoInterpreterInterface;

import java.util.ArrayList;
import java.util.Arrays;
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
        this(c.x, c.y);
    }

    public LogoController(int x, int y) {
        this.environment = new EnvironmentModel(x, y);
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

    private void noValueSwitcher(String command) {
        switch (command) {
            case "CLEARSCREEN", "CS" -> clear();
            case "HOME" -> home();
            case "PENUP", "PU" -> penUp();
            case "PENDOWN", "PD" -> penDown();
        }
    }

    /**
     * Skip first element that is the actual command
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

    @Override
    public void forward(int dist) {
        environment.getCursor().moveCursor(dist);
    }

    @Override
    public void backward(int dist) {
        forward(-dist);
    }

    @Override
    public void left(IntegerAngle angle) {
        environment
                .getCursor()
                .getDirection()
                .addAngle(angle.getAngle());
    }

    @Override
    public void right(IntegerAngle angle) {
        left(new IntegerAngle(
                        -angle.getAngle()
                )
        );
    }

    @Override
    public void clear() {

    }

    @Override
    public void home() {

    }

    @Override
    public void penUp() {

    }

    @Override
    public void penDown() {

    }

    @Override
    public void setPenColor(byte r, byte g, byte b) {

    }

    @Override
    public void setFillColor(byte r, byte g, byte b) {

    }

    @Override
    public void setScreenColor(byte r, byte g, byte b) {

    }

    @Override
    public void setPenSize(int size) {

    }

    @Override
    public void repeat(int num, List<String> list) {

    }
}
