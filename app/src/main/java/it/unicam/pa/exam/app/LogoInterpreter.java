package it.unicam.pa.exam.app;

import it.unicam.pa.exam.api.AngleInterface;
import it.unicam.pa.exam.api.LogoInterpreterInterface;

import java.util.Arrays;
import java.util.List;

public class LogoInterpreter implements LogoInterpreterInterface {
    private final Environment environment;

    LogoInterpreter(Environment environment) {
        this.environment = environment;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void execute(String rawCommand) {
        String command = rawCommand.split(" ")[0].toUpperCase();
        int[] values = getIntArrayFromString(command.split(" ")[1]);
        switch (values.length) {
            case 0 -> noValueSwitcher(command);
            case 1 -> singleIntegerSwitcher(command, values[0]);
            default -> threeByteSwitcher(command,
                    (byte) values[0],
                    (byte) values[1],
                    (byte) values[2]);
        }
    }

    private void singleIntegerSwitcher(String command, int value) {
        switch (command) {
            case "FORWARD", "FD" -> forward(value);
            case "BACK", "BK" -> backward(value);
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

    private int[] getIntArrayFromString(String values) {
        return Arrays.stream(values
                        .replace("[", "")
                        .replace("]", "")
                        .replace("{", "")
                        .replace("}", "")
                        .split(","))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    @Override
    public void forward(int dist) {
        environment.getCursor().moveCursor(dist);
    }

    @Override
    public void backward(int dist) {

    }

    @Override
    public void left(AngleInterface<Integer> angle) {
        environment.getCursor().setDirection(new IntegerAngle(
                environment.getCursor().getDirection().getAngle()
                        - angle.getAngle())
        );
    }

    @Override
    public void right(AngleInterface<Integer> angle) {
        environment.getCursor().setDirection(new IntegerAngle(
                environment.getCursor().getDirection().getAngle()
                        + angle.getAngle())
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
