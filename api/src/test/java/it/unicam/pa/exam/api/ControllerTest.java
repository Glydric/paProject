package it.unicam.pa.exam.api;

import it.unicam.pa.exam.api.Model.LogoEnvironment;
import it.unicam.pa.exam.api.io.LogoEnvironmentLoader;
import it.unicam.pa.exam.api.io.LogoEnvironmentWriter;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

public class ControllerTest {
    @Test
    void loadTest() throws IOException {
        Controller<LogoEnvironment> controller = new Controller<>(
                new LogoEnvironmentLoader(),
                new LogoEnvironmentWriter<>(),
                new LogoInterpreter<>(
                        new LogoEnvironment(600, 600)
                )
        );
        controller.load(new File("src/test/resources/LogoEnvironmentCommands"));
        controller.save(new File("src/test/resources/LogoEnvironmentResults"));
    }
}
