package it.unicam.pa.exam.api.io;

import it.unicam.pa.exam.api.Model.LogoEnvironment;

public class LogoEnvironmentWriter implements EnvironmentWriter<LogoEnvironment>{
    @Override
    public String toString(LogoEnvironment env) {
        return env.toString();
    }
}
