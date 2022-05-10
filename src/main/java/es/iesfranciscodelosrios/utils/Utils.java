package es.iesfranciscodelosrios.utils;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

    public static List<String> getFileAsLines(String url){
        File f = new File(url);
        try {
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
        } catch (FileNotFoundException e) {

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<String> getFileAsLinesWithScanner(String url){
        try {
            Scanner sc = new Scanner(new File(url));
            sc.useDelimiter(";");
            List<String> l = new ArrayList<>();
            while (sc.hasNext()){
                l.add(sc.next());
            }
            return l;
        } catch (FileNotFoundException e) {

        }
        return null;
    }
}
