package it.unicam.pa.exam.app;

import it.unicam.pa.exam.api.CoordinateInterface;
import it.unicam.pa.exam.api.CursorInterface;
import it.unicam.pa.exam.api.AngleInterface;
import it.unicam.pa.exam.api.TriangleInterface;

public class Cursor implements CursorInterface {
    private CoordinateInterface position;
    private AngleInterface<Integer> direction;

    /**
     * Costruttore per un cursore che definisce la direzione iniziale
     *
     * @param position la posizione iniziale
     */
    Cursor(CoordinateInterface position, IntegerAngle direction) {
        setPosition(position);
        setDirection(direction);
    }

    /**
     * Costruttore per un cursore che definisce la direzione iniziale
     *
     * @param position la posizione iniziale
     */
    Cursor(CoordinateInterface position, int direction) {
        setPosition(position);
        setDirection(new IntegerAngle(direction));
    }

    /**
     * Costruttore per un cursore che NON definisce la direzione iniziale
     *
     * @param position la posizione iniziale
     */
    Cursor(CoordinateInterface position) {
        this(position, new IntegerAngle());
    }

    @Override
    public AngleInterface<Integer> getDirection() {
        return direction;
    }

    /**
     * @return la posizione del cursore
     */
    @Override
    public CoordinateInterface getPosition() {
        return position;
    }

    @Override
    public void moveCursor(int road) {
        if (direction.getAngle() % 90 != 0)
            trianglePosition(road);
        else
            position.setNewPosition(
                    position.getHeight() + (direction.getAngle() == 90
                            ? road
                            : 0),
                    position.getWidth() + (direction.getAngle() == 0
                            ? road
                            : 0)
            );
    }

    private void trianglePosition(int road) {
        TriangleInterface<Integer> t = new IntegerTriangle(road, direction);
        position.setNewPosition(
                position.getHeight() + t.calcHorizontalCatFromDegrees(),
                position.getWidth() + t.calcVerticalCatFromDegrees()
        );
    }

    @Override
    public void setDirection(AngleInterface<Integer> newDirection) {
        this.direction = newDirection;
    }

    public void setDirection(int direction) {
        this.direction.setAngle(direction);
    }

    private void setPosition(CoordinateInterface position) {
        this.position = position;
    }
}
