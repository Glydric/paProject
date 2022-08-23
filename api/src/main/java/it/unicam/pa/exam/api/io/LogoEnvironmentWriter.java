package it.unicam.pa.exam.api.io;

import it.unicam.pa.exam.api.Model.Environment;

import java.awt.geom.Line2D;

public class LogoEnvironmentWriter<E extends Environment<?>> implements EnvironmentWriter<E>{
    @Override
    public String toString(E env) {
        return env.toString();
    }
}
