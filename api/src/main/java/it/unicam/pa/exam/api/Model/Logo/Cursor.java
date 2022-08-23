package it.unicam.pa.exam.api.Model.Logo;

import it.unicam.pa.exam.api.Model.Triangle.DoubleTriangle;

import java.awt.*;

public class Cursor implements CursorInterface<IntegerAngle> {
    public Color color = Color.black;
    //todo colore dell'area mancante
    private final LimitedPoint position;
    private final IntegerAngle direction;
    private int size;
    private boolean plot = true;

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
     * @param width la larghezza massima
     * @param height l'altezza massima
     */
    public Cursor(int width, int height) {
        this(new LimitedPoint(width, height));
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

    /**
     * Crea un triangolo per calcolare la nuova posizione del cursore
     *
     * @param road la strada da far seguire al cursore
     */
    private void trianglePosition(int road) {
        DoubleTriangle t = new DoubleTriangle(road, direction);

        position.incrementLocation(
                (int) (direction.getActualQuadrant() == 3 || direction.getActualQuadrant() == 2
                        ? -t.calcHorizontal()
                        : t.calcHorizontal())
                ,
                (int) (direction.getActualQuadrant() == 4 || direction.getActualQuadrant() == 3
                        ? -t.calcVertical()
                        : t.calcVertical())
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

            position.incrementLocation(
                    direction.getAngle() == 0 || direction.getAngle() == 180 || direction.getAngle() == 360
                            ? realRoad
                            : 0
                    ,
                    direction.getAngle() == 90 || direction.getAngle() == 270
                            ? realRoad
                            : 0


            );
        }
    }

    @Override
    public void setDirection(IntegerAngle newDirection) {
        setDirection(newDirection.getAngle());
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

    /**
     * Imposta la dimensione della penna
     *
     * @param size la nuova dimensione
     */
    @Override
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
        direction.clear();
    }

    @Override
    public void setPlot(boolean p) {
        this.plot = p;
    }

    @Override
    public Boolean getPlot() {
        return plot;
    }

    @Override
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
    @Override
    public void setColor(int r, int g, int b) {
        color = new Color(r, g, b);
    }

    public Color getColor() {
        return color;
    }
}
