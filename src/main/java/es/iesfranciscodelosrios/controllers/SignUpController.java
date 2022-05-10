package es.iesfranciscodelosrios.controllers;

import es.iesfranciscodelosrios.utils.PopUps;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDate;

public class SignUpController extends Controller{
    public SignUpController(){
        super();
    }

    @FXML
    TextField tf_nickname;
    @FXML
    TextField tf_name;
    @FXML
    DatePicker date_born;
    @FXML
    PasswordField psw_original;
    @FXML
    PasswordField psw_repeat;
    @FXML
    RadioButton radio_hombre;
    @FXML
    RadioButton radio_mujer;
    @FXML
    ToggleGroup Sexo;
    @FXML
    Button btn_register;
    @FXML
    Button btn_atras;
    @FXML
    protected void initialize(){

        btn_atras.setOnAction(actionEvent -> {
            App.loadScene(new Stage(),"login","Bienvenido a Brainchad",false,false);
            App.closeScene((Stage) btn_atras.getScene().getWindow());
        });

        btn_register.setOnAction(actionEvent -> {
            String nickname = tf_nickname.getText();
            String name = tf_name.getText();
            String ps_original = psw_original.getText();
            String ps_repeat = psw_repeat.getText();
            LocalDate born_date = date_born.getValue();
            RadioButton selected = ((RadioButton)Sexo.getSelectedToggle());

            String errors = "";

            if(nickname.equals("")){
                errors+="El nickname no puede estar vacío\n\n";
            }
            if(name.equals("")){
                errors+="El nombre no puede estar vacío\n\n";
            }
            if(ps_original.equals("")){
                errors+="La contraseña no puede estar vacía\n\n";
            }
            if(ps_repeat.equals("")||!ps_repeat.equals(ps_original)){
                errors+="La confirmación de contraseña está vacía o no coincide con la introducida\n\n";
            }
            if (selected == null){
                errors+="No ha seleccionado un sexo\n\n";
            }
            if(born_date == null){
                errors+="No ha seleccionado su fecha de nacimiento\n\n";
            }
            if(!errors.equals("")){
                PopUps.showPopUp("Error","Campos inválidos",errors, Alert.AlertType.ERROR);
            }
        });
    }
}
