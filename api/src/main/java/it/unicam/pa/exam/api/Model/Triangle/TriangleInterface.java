package it.unicam.pa.exam.api.Model.Triangle;

public interface TriangleInterface<E extends Number> {
    void setHorizontalCat(E x);
    void setVerticalCat(E x);
    void setIpo(E x);
    void setAngle(int x);

    E getIpo();
    int getAngle();

    E getHorizontalCat();
    E getVerticalCat();

    E calcHorizontal();
    E calcVertical();
}
