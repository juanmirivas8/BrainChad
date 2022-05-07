package es.iesfranciscodelosrios.utils;

import java.sql.*;
import java.util.List;

/**
 * Clase que genera una conexión a una base de datos SQL, dado el servidor, un usuario y la contraseña.
 * @author Juan Miguel Rivas Velasco
 */
public class SQL {
    private static SQL instance;
    private static Connection cn;
    private static ConnectionData info;

    /**
     * Constructor por defecto privado ya que la clase no se va a instanciar
     */
    private SQL(){

    }

    /**
     * Constructor parametrizado por defecto privado que recibe la url del xml que almacena
     * la información de la base de datos
     * @param url dirección del archivo xml con la información de la base de datos
     */
    private SQL(String url){
        info = new ConnectionData(url);
    }

    /**
     * Metodo que devuelve la conexión ya realizada a la base de datos
     * @param url dirección del archivo xml con la información de la base de datos
     * @param dbCreation Lista con sentencias SQL para inicializar la base de datos en caso de que su estructura esté vacía
     * @return Conexion a la base de datos
     */
    public static Connection getConnection(String url,List<String> dbCreation) {
        if(instance == null){
            instance = new SQL(url);
        }
        if(cn == null){
            try {
                DriverManager.setLoginTimeout(2);
                cn = DriverManager.getConnection(info.getServer()+info.getDatabase(),info.getUser(), info.getPassword());
                checkStructure(dbCreation);
            } catch (SQLException e) {
                cn = null;
                info = null;
                instance = null;
            }
        }
        return cn;
    }

    /**
     * Comprueba si la estructura de la base de datos está integra y la crea en caso contrario
     * @param dbCreation Lista con las sentencias SQL a ejecutar
     */
    private static void checkStructure(List<String> dbCreation){
        if(dbCreation != null && !dbCreation.isEmpty()){
            dbCreation.forEach(x->execUpdate(x,null,false));
        }
    }

    /**
     * Cierra la conexión a la base de datos
     */
    public static void disconnect() {
        if (cn != null) {
            try {
                cn.close();
                cn = null;
                info = null;
                instance = null;
            } catch (SQLException e) {

            }
        }
    }

    /**
     * Método que prepara una consulta de forma general
     * @author Alvaro Perez Urbano
     * @param q Cadena con la consulta a preparar
     * @param params Lista de Object con los parametros a sustituir en los comodines
     * @return Sentencia Preparada
     */
    private static PreparedStatement prepareQuery(String q, List<Object> params){
        PreparedStatement ps = null;
        if (cn != null){
            try {
                ps = cn.prepareStatement(q, Statement.RETURN_GENERATED_KEYS);
            } catch (SQLException e) {

            }

            if (params != null && ps != null) {
                int i = 1;
                for (Object o : params) {
                    try {
                        ps.setObject(i++, o);
                    } catch (SQLException e) {

                    }
                }
            }
        }

        return ps;
    }

    /**
     * Metodo que realiza consultas select
     * @author Alvaro Perez Urbano
     * @param q Cadena con la consulta SQL
     * @param params Parametros de la consulta
     * @return ResultSet con los resultados de la consulta
     */
    public static ResultSet execQuery(String q, List<Object> params) {
        ResultSet result;
        if (cn != null) {
            PreparedStatement ps = prepareQuery(q, params);
            try {
                result = ps.executeQuery();
            } catch (SQLException e) {
                result = null;
            }
        } else {
            result = null;
        }
        return result;
    }

    /**
     * Metodo que ejecuta consultas distintas a Select
     * @author Alvaro Perez Urbano
     * @param q String con la sentencia a ejecutar
     * @param params Lista de parámetros de la sentencia
     * @param insert Si la sentencia es un insert debe indic
     * @return La id autogenerada si es un insert o el numero de filas afectadas por un update/delete
     */
    public static long execUpdate(String q, List<Object> params, boolean insert) {
        long result;
        if (cn != null) {
            try (PreparedStatement ps = prepareQuery(q, params)) {
                result = ps.executeUpdate();
                if (insert) {
                    try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                        if (generatedKeys.next())
                            result = generatedKeys.getLong(1);
                        else
                            result = -1;
                    } catch (SQLException e) {

                    }
                }
            } catch (SQLException e) {
                result = -1;
            }
        } else {
            result = -1;
        }
        return result;
    }

}
