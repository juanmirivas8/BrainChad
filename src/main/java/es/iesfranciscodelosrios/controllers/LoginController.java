package es.iesfranciscodelosrios.controllers;

import es.iesfranciscodelosrios.model.Usuario;
import es.iesfranciscodelosrios.utils.Utils;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController extends Controller{
    public LoginController(){
        super();
    }
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
                    Utils.showPopUp("Autenticacion correcta","Credenciales validas",
                            user.toString(),Alert.AlertType.CONFIRMATION);
                }
            }
        });

        link_register_here.setOnAction(actionEvent -> {
            App.loadScene(new Stage(),"signUp","Registra una nueva cuenta",false,false);
            App.closeScene((Stage) link_register_here.getScene().getWindow());
        });

    }

}
