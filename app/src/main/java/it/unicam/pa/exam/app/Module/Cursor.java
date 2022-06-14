package it.unicam.pa.exam.app.Module;

import it.unicam.pa.exam.api.CursorInterface;

import java.awt.*;

public class Cursor implements CursorInterface<IntegerAngle> {
    public Color color = Color.black;
    private final LimitedPoint position;
    private final IntegerAngle direction;
    private int size;
    public boolean plot = true;

    /**
     * Costruttore per un cursore che definisce la direzione iniziale
     *
     * @param position la posizione iniziale
     */
    public Cursor(LimitedPoint position, IntegerAngle direction, int size) {
        this.position = position;
        this.direction = direction;
        setSize(size);
    }

    /**
     * Costruttore per un cursore che definisce la direzione iniziale
     *
     * @param position la posizione iniziale
     */
    public Cursor(LimitedPoint position, int direction, int size) {
        this(position, new IntegerAngle(direction), size);
    }

    /**
     * Costruttore per un cursore che NON definisce la direzione iniziale
     *
     * @param position la posizione iniziale
     */
    public Cursor(LimitedPoint position, int size) {
        this(position, new IntegerAngle(), size);
    }

    /**
     * Costruttore per un cursore che NON definisce ne la direzione iniziale ne la dimensione di "scrittura"
     *
     * @param position la posizione iniziale
     */
    public Cursor(LimitedPoint position) {
        this(position, new IntegerAngle(), 1);
    }

    private void trianglePosition(int road) {
        IntegerTriangle t = new IntegerTriangle(
                Math.abs(road)
                ,
                road > 0
                        ? direction
                        : direction.getInvertedAngle()
        );

        position.setLocation(
                position.getX() + t.calcHorizontalCatFromDegrees(),
                position.getY() + t.calcVerticalCatFromDegrees()
        );
    }

    @Override
    public IntegerAngle getDirection() {
        return direction;
    }

    /**
     * @return la posizione del cursore
     */
    @Override
    public LimitedPoint getPosition() {
        return position;
    }

    /**
     * Consente di muovere il cursore in relazione alla direzione definita dall'angolo
     *
     * @param road la strada da percorrere
     */
    @Override
    public void moveCursor(int road) {
        if (direction.getAngle() % 90 != 0)
            trianglePosition(road);
        else {
            int realRoad = direction.getAngle() == 270 || direction.getAngle() == 180
                    ? -road
                    : road;

            position.setLocation(
                    position.getX() + (
                            direction.getAngle() == 90 || direction.getAngle() == 270
                                    ? realRoad
                                    : 0
                    ),
                    position.getY() + (
                            direction.getAngle() == 0 || direction.getAngle() == 180
                                    ? realRoad
                                    : 0
                    )
            );
        }
    }

    @Override
    public void setDirection(IntegerAngle newDirection) {
        direction.setAngle(newDirection.getAngle());
    }

    public void setDirection(int direction) {
        this.direction.setAngle(direction);
    }

    private void setPosition(Point point) {
        position.setLocation(point);
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        if (size < 1) throw new IllegalArgumentException("size can't be negative or 0");
        this.size = size;
    }

    /**
     * Pulisce il cursore impostandolo alla propria home
     */
    public void clear() {
        this.size = 1;
        goToHome();
    }

    /**
     * Imposta il cursore nella posizione della propria home
     */
    public void goToHome() {
        setPosition(position.getHome());
    }

    /**
     * Prende in input i parametri del colore e imposta il colore della linea
     *
     * @param r il colore rosso rappresentato come byte
     * @param g il colore verde rappresentato come byte
     * @param b il colore blu rappresentato come byte
     */
    public void setColor(byte r, byte g, byte b) {
        color = new Color(r, g, b);
    }

    public Color getColor() {
        return color;
    }
}
