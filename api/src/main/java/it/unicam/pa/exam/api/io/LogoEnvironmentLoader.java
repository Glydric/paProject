package it.unicam.pa.exam.api.io;

import it.unicam.pa.exam.api.LogoInterpreter;
import it.unicam.pa.exam.api.Model.LogoEnvironment;

public class LogoEnvironmentLoader implements EnvironmentLoader<LogoEnvironment> {
    @Override
    public LogoEnvironment parse(String content) {
        LogoInterpreter<LogoEnvironment> interpreter = new LogoInterpreter<>(
                new LogoEnvironment(600, 600));

        for (String s : content.split("\n")) {
            interpreter.execute(s);
        }
        return interpreter.environment;
    }
}
