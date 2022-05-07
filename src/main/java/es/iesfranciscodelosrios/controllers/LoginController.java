package es.iesfranciscodelosrios.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
    @FXML
    private Button btn_login;

    @FXML
    private Button btn_signup;

    @FXML
    private TextField tf_password;

    @FXML
    private TextField tf_nickname;

    @FXML
    protected void initialize(){
        btn_login.setOnAction(actionEvent -> {
            String user = tf_nickname.getText();
        });

        btn_signup.setOnAction(actionEvent -> {
            App.loadScene(new Stage(),"signUp","Sign Up",true,false);
            App.closeScene((Stage)btn_login.getScene().getWindow());
        });
    }

}
