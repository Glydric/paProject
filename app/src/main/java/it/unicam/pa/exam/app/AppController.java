package it.unicam.pa.exam.app;

import com.sun.javafx.sg.prism.NGRectangle;
import it.unicam.pa.exam.api.Controller;
import it.unicam.pa.exam.api.Model.Logo.*;
import it.unicam.pa.exam.api.Model.Logo.Cursor;
import it.unicam.pa.exam.api.Model.LogoEnvironment;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;

import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Gestisce l'integrazione tra Model e View
 */
public class AppController {
    Controller<LogoEnvironment, Cursor, IntegerAngle> controller = Controller.getLogoController(
            (int) (HEIGHT * 0.8),
            (int) (WIDTH * 0.8)
    );

    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;
    public AnchorPane fieldArea;
    public Canvas drawArea;

    @FXML
    private TextField textField;

    @FXML
    public Button homeButton;

    @FXML
    private Button executeButton;

    @FXML
    private Button saveButton;

    @FXML
    private Button loadButton;

    /**
     * Il metodo inizializza l'area di disegno
     */
    public void initialize() {
        GraphicsContext graphics = drawArea.getGraphicsContext2D();

        /*drawArea.getElements().add(
                new MoveTo(
                        controller.getEnvironment().getCursor().getPosition().getX(),
                        controller.getEnvironment().getCursor().getPosition().getY()
                )
        );*/
    }

    /**
     * metodo chiamato quando viene premuto il tasto save
     *
     * @param event l'evento
     */
    @FXML
    private void onSaveButton(Event event) {//todo can you reduce method size?
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Insert a filename");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Logo Files", ".*logo")
        );
        File selectedFile = fileChooser.showSaveDialog(((Node) event.getSource()).getScene().getWindow());//todo check use
        if (selectedFile != null) {
            try {
                controller.save(selectedFile);
            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error save file " + e);
                alert.setHeaderText(e.getMessage());
                alert.show();
            }
        }
    }

    /**
     * metodo chiamato quando viene premuto il tasto load
     *
     * @param actionEvent l'evento
     */
    @FXML
    private void onLoadButton(ActionEvent actionEvent) {//todo can you reduce method size?
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Insert a filename");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Logo command", "*.logoCommand")
        );
        File selectedFile = fileChooser.showOpenDialog(((Node) actionEvent.getSource()).getScene().getWindow());
        if (selectedFile != null) {
            try {
                controller.load(selectedFile);
            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error loading file " + e);
                alert.setHeaderText(e.getMessage());
                alert.show();
            }
        }
        refreshView();
    }


    /**
     * metodo chiamato quando viene premuto il tasto execute
     * al termine aggiorna la vista e resetta il campo field
     *
     * @param actionEvent l'evento
     */
    @FXML
    private void onExecuteButton(ActionEvent actionEvent) {
        try {
            controller.execute(textField.getText());
        } catch (IllegalArgumentException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Command not available");
            alert.setHeaderText(e.getMessage());
            alert.show();
        }
        textField.setText("");
        refreshView();
    }


    private void setDrawAreaFill(ColoredClosedArea a) {
        GraphicsContext graphics = drawArea.getGraphicsContext2D();

        graphics.setFill(getPaint(a.getColor()));
        graphics.fillPolygon(a.getAllX(), a.getAllY(), a.getPoints().size());
    }

    private Paint getPaint(Color color) {
        return javafx.scene.paint.Color.rgb(
                color.getRed(),
                color.getGreen(),
                color.getBlue()
        );
    }

    /**
     * Il metodo che aggiorna la vista
     */
    private void refreshView() {
        GraphicsContext graphics = drawArea.getGraphicsContext2D();

        controller.getAllAreas()
                .forEach(a -> {
                            if (a instanceof ColoredClosedArea && a.isClosed())
                                setDrawAreaFill(((ColoredClosedArea) a));
                            a.getLines().forEach((l) -> {
                                        graphics.setStroke(getPaint(l.getColor()));
                                        graphics.setFill(getPaint(l.getColor()));
                                        graphics.strokeLine(l.getX1(), l.getY1(), l.getX2(), l.getY2());
                                    }
                            );
                        }
                );

    }

    public void onHomeButton(ActionEvent actionEvent) {
        controller.getEnvironment().getCursor().goToHome();
        refreshView();
    }
}