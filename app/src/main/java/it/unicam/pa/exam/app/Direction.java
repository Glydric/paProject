package it.unicam.pa.exam.app;

import it.unicam.pa.exam.list.DirectionInterface;

public class Direction implements DirectionInterface {
    int direction;

    Direction(int direction) {
        setDirection(direction);
    }
    Direction() {
        this(0);
    }

    /**
     * @return la direzione
     */
    @Override
    public int getDirection() {
        return direction;
    }

    /**
     * @param direction un intero che definisce la direzione [0,360]
     */
    @Override
    public void setDirection(int direction) {
        if (direction > 360 || direction < 0)
            throw new IllegalArgumentException("La direzione deve essere compresa nell'intervallo [0,360]");
        this.direction = direction;
    }
}
