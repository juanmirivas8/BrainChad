package es.iesfranciscodelosrios.utils;

import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class PopUps {
    public static void showPopUp(String title, String header, String text,Alert.AlertType type){
        Alert alertDialog = new Alert(type);
        alertDialog.setTitle(title);
        alertDialog.setHeaderText(header);
        alertDialog.setContentText(text);
        alertDialog.show();
        Stage s =(Stage)alertDialog.getDialogPane().getScene().getWindow();
        s.toFront();
    }
}
