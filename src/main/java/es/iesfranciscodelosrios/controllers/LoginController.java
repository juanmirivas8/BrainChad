package es.iesfranciscodelosrios.controllers;

import es.iesfranciscodelosrios.model.Usuario;
import es.iesfranciscodelosrios.utils.Utils;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.util.logging.Level;

public class LoginController extends Controller{
    public LoginController(){
        super();
        Log.log(Level.INFO,"LoginController cargado");

    }


    @FXML
    private BorderPane pane;

    @FXML
    private Button btn_login;

    @FXML
    private TextField tf_password;

    @FXML
    private TextField tf_nickname;

    @FXML
    private Hyperlink link_register_here;

    @FXML
    protected void initialize(){
        btn_login.setOnAction(actionEvent -> {
            String nickname = tf_nickname.getText();
            String password = tf_password.getText();
            tf_nickname.setText("");
            tf_password.setText("");

            if(nickname.equals("") || password.equals("")){
                Utils.showPopUp("Inicio de Sesión","Campos vacios",
                        "Por favor introduzca el usuario y/o la contraseña",Alert.AlertType.ERROR);
            }else{
                Usuario user = users.identify(nickname, Utils.encryptSHA256(password));

                if(user == null){
                    Utils.showPopUp("Autenticacion fallida","Credenciales invalidas",
                            "El usuario y/o la contraseña son incorrectas",Alert.AlertType.ERROR);
                }else{
                    objects.add(user);
                    App.loadScene(new Stage(),"main","BrainChad",false,false);
                    App.closeScene((Stage) btn_login.getScene().getWindow());
                }
            }
        });

        link_register_here.setOnAction(actionEvent -> {
            App.loadScene(new Stage(),"signUp","Registra una nueva cuenta",false,false);
            App.closeScene((Stage) link_register_here.getScene().getWindow());
        });

        Platform.runLater(()->{
            Utils.closeRequest((Stage) pane.getScene().getWindow());
        });

        Utils.addTextLimiter(tf_nickname,30);

    }

}
