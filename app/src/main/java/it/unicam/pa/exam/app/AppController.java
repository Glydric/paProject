package it.unicam.pa.exam.app;

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
     * inizializza l'area di disegno con il colore di sfondo selezionato
     */
    public void initialize() {
        GraphicsContext graphics = drawArea.getGraphicsContext2D();

        graphics.setFill(getPaint(controller.getEnvironment().getBackgroundColor()));
        graphics.fillRect(0, 0, WIDTH, HEIGHT);

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

    /**
     * Disegna un'area se è chiusa
     * Se l'area ha un colore lo applica
     * In fine disegna anche le linee che la compongono
     *
     * @param area l'area di riferimento
     */
    private void drawArea(ClosedArea area) {
        GraphicsContext graphics = drawArea.getGraphicsContext2D();

        if (area.isClosed()) {
            if (area instanceof ColoredClosedArea)
                graphics.setFill(getPaint(((ColoredClosedArea) area).getColor()));
            graphics.fillPolygon(area.getAllX(), area.getAllY(), area.getPoints().size());
        }

        area.getLines().forEach(this::drawLine);
    }

    private void drawLine(ColoredLine l) {
        GraphicsContext graphics = drawArea.getGraphicsContext2D();

        graphics.setStroke(getPaint(l.getColor()));
        graphics.strokeLine(l.getX1(), l.getY1(), l.getX2(), l.getY2());
    }

    private Paint getPaint(Color color) {
        return javafx.scene.paint.Color.rgb(
                color.getRed(),
                color.getGreen(),
                color.getBlue()
        );
    }

    /**
     * Il metodo che aggiorna tutta la vista
     */
    private void refreshView() {
        GraphicsContext graphics = drawArea.getGraphicsContext2D();

        initialize();
        controller.getAllAreas().forEach(this::drawArea);
        //todo add pen size
    }

    public void onHomeButton(ActionEvent actionEvent) {
        controller.getEnvironment().getCursor().goToHome();
        refreshView();
    }
}