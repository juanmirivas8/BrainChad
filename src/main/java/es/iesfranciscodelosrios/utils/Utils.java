package es.iesfranciscodelosrios.utils;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

import java.io.*;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * En esta clase se recopilarán funcionalidades de diversa índole necesarias para el funcionamiento necesario del programa.
 * @author Juan Miguel Rivas Velasco
 */
public class Utils {

    private static final Logger Log = Utils.getLogger();

    /**
     * Método que encripta una cadena mediante SHA256
     * @param s Cadena a encriptar
     * @return Cadena encriptada
     */
    public static String encryptSHA256 (String s){
        String result = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA256");
            md.update(s.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte aByte : md.digest()) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            result = sb.toString();
        }catch(Exception e){
            Log.log(Level.SEVERE,Utils.exceptionInfo(e));
        }
        return result;
    }

    /**
     * Devuelve una lista con cada una de las lineas de un fichero usando BufferedReader
     * @param url Ubicacion del fichero a leer
     * @return Lista con las lineas
     */
    public static List<String> getFileAsLines(String url){
        try {
            InputStreamReader in = new InputStreamReader(Utils.class.getResourceAsStream(url));
            BufferedReader br = new BufferedReader(in);
            String line;
            List<String> lines = new ArrayList<>();
            while ((line = br.readLine()) != null){
                lines.add(line);
            }
            br.close();
            in.close();
            return lines;
        } catch (Exception e) {
            Log.log(Level.SEVERE,Utils.exceptionInfo(e));
        }
        return null;
    }

    /**
     * Metodo que lee sentencias de un fichero usando ; como delimitador
     * @param url Ubicacion del fichero
     * @return Lista con las sentencias
     */
    public static List<String> getFileAsLinesWithScanner(String url){
        try {
            Scanner sc = new Scanner(Utils.class.getResourceAsStream(url));
            sc.useDelimiter(";");
            List<String> l = new ArrayList<>();
            while (sc.hasNext()){
                l.add(sc.next());
            }
            return l;
        }catch (Exception e){
            Log.log(Level.SEVERE,Utils.exceptionInfo(e));
        }
        return null;
    }

    /**
     * Muestra una ventana emergente de alerta
     * @param title Titulo de la ventana
     * @param header Mensaje de cabecera
     * @param text Texto de contexto
     * @param type Tipo de ventana
     */
    public static Alert showPopUp(String title, String header, String text, Alert.AlertType type){
        Alert alertDialog = new Alert(type);
        alertDialog.setTitle(title);
        alertDialog.setHeaderText(header);
        alertDialog.setContentText(text);
        alertDialog.show();
        Stage s =(Stage)alertDialog.getDialogPane().getScene().getWindow();
        s.toFront();
        return alertDialog;
    }

    public static String showDialogString(Stage stage, String title, String header, String description, int max_characters){
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle(title);
        dialog.setHeaderText(header);
        dialog.setContentText(description);
        Stage s =(Stage)dialog.getDialogPane().getScene().getWindow();
        s.initOwner(stage);
        s.toFront();
        Utils.addTextLimiter(dialog.getEditor(),max_characters);
        Optional<String> result = dialog.showAndWait();
        return result.orElse(null);
    }

    /**
     * Inicializa el logger de java util con la configuracion del fichero logging.properties
     * @return Logger inicializado o null si hubo un fallo
     */
    public static Logger getLogger(){
        try{
            Logger l;
            InputStream configFile = Utils.class.getResourceAsStream("/es/iesfranciscodelosrios/others/logging.properties");
            LogManager.getLogManager().readConfiguration(configFile);
            l = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
            return l;
        }catch(Exception s){
            System.out.println("Error al cargar el logger");
        }
        return null;
    }

    public static String exceptionInfo(Throwable e){
        return e.getClass().getName()+" - "+e.getMessage();
    }

    public static void closeRequest(Stage stage){
           stage.setOnCloseRequest(windowEvent -> {
                Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                a.setTitle("Confirmacion de cierre");
                a.setHeaderText("¿Esta seguro de salir del programa?");
                a.setContentText("Cualquier informacion o proceso sin guardar/terminar se perderá");
               Stage s =(Stage)a.getDialogPane().getScene().getWindow();
               s.initOwner(stage);
               s.toFront();
                a.showAndWait().filter(buttonType -> buttonType== ButtonType.OK).ifPresentOrElse(buttonType -> Platform.exit(),windowEvent::consume);
           });
    }

    public static void addTextLimiter(final TextField tf, final int maxLength) {
        tf.textProperty().addListener((ov, oldValue, newValue) -> {
            if (tf.getText().length() > maxLength) {
                String s = tf.getText().substring(0, maxLength);
                tf.setText(s);
            }
        });
    }
}
