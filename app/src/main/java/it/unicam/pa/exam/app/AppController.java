package it.unicam.pa.exam.app;

import it.unicam.pa.exam.api.Controller;
import it.unicam.pa.exam.api.Model.LogoEnvironment;
import it.unicam.pa.exam.api.Model.Logo.*;
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
import java.awt.geom.Line2D;
import java.io.File;
import java.io.IOException;

/**
 * Gestisce l'integrazione tra Model e View
 */
public class AppController {
    final Controller<LogoEnvironment> controller = Controller.getStandardLogoController(HEIGHT, WIDTH);

    public static final int WIDTH = 500;
    public static final int HEIGHT = 500;
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
        refreshView();
    }

    void fileCheck(ThrowableInterface<File, IOException> c, File toSave) {
        if (toSave != null) {
            try {
                c.execute(toSave);
            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error with file " + e);
                alert.setHeaderText(e.getMessage());
                alert.show();
            }
        }

    }

    /**
     * metodo chiamato quando viene premuto il tasto save
     *
     * @param event l'evento
     */
    @FXML
    private void onSaveButton(Event event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Insert a filename");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Logo Files", ".*logo"));
        File selectedFile = fileChooser.showSaveDialog(((Node) event.getSource()).getScene().getWindow());
        fileCheck(controller::save, selectedFile);
    }


    /**
     * metodo chiamato quando viene premuto il tasto load
     *
     * @param actionEvent l'evento
     */
    @FXML
    private void onLoadButton(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Insert a filename");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Logo command", "*.logoCommand"));
        File selectedFile = fileChooser.showOpenDialog(((Node) actionEvent.getSource()).getScene().getWindow());
        fileCheck(controller::load, selectedFile);
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

    public void onClearButton(ActionEvent actionEvent) {
        controller.clear();
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
    private void drawArea(ClosedArea<ColoredLine> area) {
        GraphicsContext graphics = drawArea.getGraphicsContext2D();

        if (area.isClosed()) {
            if (area instanceof ColoredClosedArea)
                graphics.setFill(getPaintFrom(((ColoredClosedArea<ColoredLine>) area).getColor()));
            graphics.fillPolygon(area.getAllX(), area.getAllY(), area.getPoints().size());
        }

        area.getLines().forEach(this::drawLine);
    }

    private void drawLine(Line2D line) {
        GraphicsContext graphics = drawArea.getGraphicsContext2D();

        if (line instanceof ColoredLine coloredLine) {
            graphics.setStroke(getPaintFrom(coloredLine.getColor()));
            graphics.setLineWidth(coloredLine.getSize());
        }
        graphics.strokeLine(line.getX1(), line.getY1(), line.getX2(), line.getY2());
    }

    private Paint getPaintFrom(Color color) {
        return javafx.scene.paint.Color.rgb(color.getRed(), color.getGreen(), color.getBlue());
    }


    /**
     * Il metodo che aggiorna tutta la vista
     */
    private void refreshView() {
        GraphicsContext graphics = drawArea.getGraphicsContext2D();

        graphics.setFill(getPaintFrom(controller.getEnvironment().getBackgroundColor()));
        graphics.fillRect(0, 0, WIDTH, HEIGHT);

        controller.getEnvironment().getAreas().forEach(this::drawArea);
    }
}