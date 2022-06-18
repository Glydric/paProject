package it.unicam.pa.exam.api;

import it.unicam.pa.exam.api.Model.LogoEnvironment;
import it.unicam.pa.exam.api.io.EnvironmentLoader;
import it.unicam.pa.exam.api.io.EnvironmentWriter;

public class Controller {
    EnvironmentWriter<LogoEnvironment> environmentWriter;
    EnvironmentLoader<LogoEnvironment> environmentLoader;
}
