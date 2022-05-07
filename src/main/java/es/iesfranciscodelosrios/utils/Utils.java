package es.iesfranciscodelosrios.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * En esta clase se recopilarán funcionalidades de diversa índole necesarias para el funcionamiento necesario del programa.
 * @author Juan Miguel Rivas Velasco
 */
public class Utils {
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
        } catch (NoSuchAlgorithmException e) {

        }
        return result;
    }
}
