package es.iesfranciscodelosrios.model;

import es.iesfranciscodelosrios.utils.SQL;
import es.iesfranciscodelosrios.utils.Utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class DAOConnection {
    protected static Connection cn;
    protected static Boolean online;
    protected static final Logger Log = Utils.getLogger();
    protected static UsuarioDAO users;
    protected static PreguntaDAO preguntas;

    public DAOConnection(){
        if(cn == null){
            cn = SQL.getConnection("/es/iesfranciscodelosrios/others/sql.xml",
                    Utils.getFileAsLinesWithScanner("/es/iesfranciscodelosrios/others/sql.sql"));
            online = true;
        }
        if(cn == null){
            cn = SQL.getConnection("/es/iesfranciscodelosrios/others/h2.xml",
                    Utils.getFileAsLinesWithScanner("/es/iesfranciscodelosrios/others/h2.sql"));
            online = false;
        }
        if(cn == null){
            online = null;
            Log.log(Level.SEVERE,"Error Critico - no se cargo ninguna base de datos");
        }
    }

    public static Boolean close(){
       Boolean ret = false;
        try {
            if(cn != null){
                cn.close();
                cn = null;
                online = null;
                ret = true;
            }
        } catch (SQLException e) {
            Log.log(Level.SEVERE,Utils.exceptionInfo(e));
        }
        return ret;
    }

}
