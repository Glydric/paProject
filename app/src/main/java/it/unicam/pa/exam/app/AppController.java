package it.unicam.pa.exam.app;

import it.unicam.pa.exam.api.Controller;
import it.unicam.pa.exam.api.Model.Logo.Cursor;
import it.unicam.pa.exam.api.Model.Logo.IntegerAngle;
import it.unicam.pa.exam.api.Model.LogoEnvironment;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.FileChooser;

import java.awt.geom.Line2D;
import java.io.File;
import java.io.IOException;

/**
 * Gestisce l'integrazione tra Model e View
 */
public class AppController {
    Controller<LogoEnvironment, Cursor, IntegerAngle> controller = Controller.getLogoController(HEIGHT, WIDTH);

    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;
    public AnchorPane fieldArea;
    public Path drawArea;

    @FXML
    private TextField textField;

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
        drawArea.getElements().add(
                new MoveTo(
                        controller.getEnvironment().getCursor().getPosition().getX(),
                        controller.getEnvironment().getCursor().getPosition().getY()
                )
        );
    }

    /**
     * metodo chiamato quando viene premuto il tasto save
     * @param event l'evento
     */
    @FXML
    private void onSaveButton(Event event) {//todo reduce method size
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
     * @param event l'evento
     */
    @FXML
    private void onLoadButton(Event event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Insert a filename");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Logo command", "*.logoCommand")
        );
        File selectedFile = fileChooser.showSaveDialog(((Node) event.getSource()).getScene().getWindow());
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
     * @param event l'evento
     */
    @FXML
    private void onExecuteButton(Event event) {
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


    /**
     * Il metodo che aggiorna la vista
     */
    private void refreshView() {
        drawArea.getElements().addAll(
                controller.getAllLines()
                        .stream()
                        .map((l) -> new LineTo(l.getX2(), l.getY2()))
                        .toList());

    }
}