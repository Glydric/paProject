package it.unicam.pa.exam.api.Model.Logo;

public interface TriangleInterface<E extends Number> {
    void setHorizontalCat(E x);
    void setVerticalCat(E x);
    void setIpo(E x);
    void setAngle(int x);

    E getHorizontalCat();
    E getVerticalCat();
    E getIpo();
    int getAngle();


    E calcHorizontal();
    E calcVertical();
}
