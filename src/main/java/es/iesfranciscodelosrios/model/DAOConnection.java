package es.iesfranciscodelosrios.model;

import com.mysql.cj.jdbc.exceptions.CommunicationsException;
import es.iesfranciscodelosrios.utils.SQL;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class DAOConnection {
    protected static Connection cn;
    protected static Boolean online;
    public DAOConnection(){
        if(cn == null){
            cn = SQL.getConnection("src/main/resources/es/iesfranciscodelosrios/others/sql.xml",null);
            online = true;
        }
        if(cn == null){
            cn = SQL.getConnection("src/main/resources/es/iesfranciscodelosrios/others/h2.xml",null);
            online = false;
        }
        if(cn == null){
            online = null;
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

        }
        return ret;
    }

    public static Boolean getType(){
        return online;
    }
    public static Connection getConnection(){return cn;}

}
