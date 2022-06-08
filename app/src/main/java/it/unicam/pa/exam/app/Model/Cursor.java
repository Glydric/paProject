package it.unicam.pa.exam.app.Model;

import it.unicam.pa.exam.api.CursorInterface;

public class Cursor implements CursorInterface<IntegerAngle> {
    private Coordinate position;
    private IntegerAngle direction;

    /**
     * Costruttore per un cursore che definisce la direzione iniziale
     *
     * @param position la posizione iniziale
     */
    Cursor(Coordinate position, IntegerAngle direction) {
        setPosition(position);
        setDirection(direction);
    }

    /**
     * Costruttore per un cursore che definisce la direzione iniziale
     *
     * @param position la posizione iniziale
     */
    public Cursor(Coordinate position, int direction) {
        setPosition(position);
        setDirection(new IntegerAngle(direction));
    }

    /**
     * Costruttore per un cursore che NON definisce la direzione iniziale
     *
     * @param position la posizione iniziale
     */
    public Cursor(Coordinate position) {
        this(position, new IntegerAngle());
    }

    @Override
    public IntegerAngle getDirection() {
        return direction;
    }

    /**
     * @return la posizione del cursore
     */
    @Override
    public Coordinate getPosition() {
        return position;
    }

    @Override
    public void moveCursor(int road) {
        if (direction.getAngle() % 90 != 0)
            trianglePosition(road);
        else
            position.setLocation(
                    position.getX() + (direction.getAngle() == 90
                            ? road
                            : 0),
                    position.getY() + (direction.getAngle() == 0
                            ? road
                            : 0)
            );
    }

    private void trianglePosition(int road) {
        IntegerTriangle t = new IntegerTriangle(road, direction);
        position.setLocation(
                position.getX() + t.calcHorizontalCatFromDegrees(),
                position.getY() + t.calcVerticalCatFromDegrees()
        );
    }

    @Override
    public void setDirection(IntegerAngle newDirection) {
        this.direction = newDirection;
    }

    public void setDirection(int direction) {
        this.direction.setAngle(direction);
    }

    private void setPosition(Coordinate position) {
        this.position = position;
    }
}
