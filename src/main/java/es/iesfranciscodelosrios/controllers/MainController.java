package es.iesfranciscodelosrios.controllers;

import es.iesfranciscodelosrios.model.Usuario;
import es.iesfranciscodelosrios.utils.Utils;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


import java.util.logging.Level;

public class MainController extends Controller{
    public MainController(){
        super();
        Log.log(Level.INFO,"MainController cargado");
    }

    @FXML
    Label label_nickname;
    @FXML
    Label label_puntuacion;

    @FXML
    BorderPane pane;
    @FXML
    private void initialize(){
        Usuario user = (Usuario)objects.get(0);
        label_nickname.setText(user.getNickname());
        label_puntuacion.setText(user.getPuntuacion().toString());

        Platform.runLater(()->{
            Utils.closeRequest((Stage) pane.getScene().getWindow());
        });
    }
}
