package es.iesfranciscodelosrios.controllers;

import es.iesfranciscodelosrios.model.Usuario;
import es.iesfranciscodelosrios.utils.Utils;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


import java.util.logging.Level;

public class MainController extends Controller{
    public MainController(){
        super();
        Log.log(Level.INFO,"MainController cargado");
    }

    @FXML
    MenuItem menuItem_nuevaPartida;
    @FXML
    MenuItem menuItem_crearPregunta;

    @FXML
    BorderPane pane;
    @FXML
    private void initialize(){
        Platform.runLater(()->{
            Utils.closeRequest((Stage) pane.getScene().getWindow());
        });

        menuItem_nuevaPartida.setOnAction(actionEvent->{
            pane.getChildren().remove(pane.getCenter());
            pane.setCenter(App.loadFXML("partida"));
        });

        menuItem_crearPregunta.setOnAction(actionEvent -> {
            pane.getChildren().remove(pane.getCenter());
            pane.setCenter(App.loadFXML("preguntas"));
        });

    }
}
