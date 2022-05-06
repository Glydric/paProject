package it.unicam.pa.exam.api;

public interface TriangleInterface<E extends Number> {
    void setHorizontalCat(E x);
    void setVerticalCat(E x);
    void setIpo(E x);
    void setAngle(E x);

    E getHorizontalCat();
    E getVerticalCat();
    E getIpo();
    E getAngle();


    E calcHorizontalCatFromDegrees();
    E calcVerticalCatFromDegrees();
}
