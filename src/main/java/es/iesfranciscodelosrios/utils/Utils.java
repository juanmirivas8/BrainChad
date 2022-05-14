package es.iesfranciscodelosrios.utils;

import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.*;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
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
            File f = new File(url);
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String line;
            List<String> lines = new ArrayList<>();
            while ((line = br.readLine()) != null){
                lines.add(line);
            }
            br.close();
            fr.close();
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
            Scanner sc = new Scanner(new File(url));
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
    public static void showPopUp(String title, String header, String text, Alert.AlertType type){
        Alert alertDialog = new Alert(type);
        alertDialog.setTitle(title);
        alertDialog.setHeaderText(header);
        alertDialog.setContentText(text);
        alertDialog.show();
        Stage s =(Stage)alertDialog.getDialogPane().getScene().getWindow();
        s.toFront();
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
}
