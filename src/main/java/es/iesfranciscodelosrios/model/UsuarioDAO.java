package es.iesfranciscodelosrios.model;

import es.iesfranciscodelosrios.utils.SQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO extends DAOConnection {

    private static UsuarioDAO instance;

    private UsuarioDAO(){
        super();
    }

    public static UsuarioDAO getInstance(){
        if( instance == null){
            instance = new UsuarioDAO();
        }
        return instance;
    }

    public Boolean insertNewUser(Usuario object) {
        long res = -1;
        if(find(object.getNickname()) == null){
            List<Object> l = new ArrayList<>();
            l.add(object.getNickname());
            l.add(object.getPassword());
            l.add(object.getSexo());
            l.add(object.getNombre());
            l.add(object.getBornDate());

            String query = "INSERT INTO USUARIO (NICKNAME, PASSWORD, SEXO, NOMBRE,FECHA_NACIMIENTO) VALUES (?,?,?,?,?)";
             res = SQL.execUpdate(query,l,true);
        }
        return res != -1;
    }

    public Usuario find (String user){
        String query = "SELECT ID, NICKNAME, PASSWORD, SEXO, NOMBRE, MONEDA, PUNTUACION, FECHA_NACIMIENTO, FECHA_REGISTRO FROM USUARIO WHERE NICKNAME = ?";
        List<Object> l = new ArrayList<>();
        l.add(user);
        ResultSet rs = SQL.execQuery(query,l);
        try{
            while(rs.next()){
                return new Usuario(rs.getString("nombre"),rs.getString("nickname"),
                        rs.getString("password"),rs.getDate("fecha_nacimiento"),rs.getBoolean("sexo"),
                        rs.getInt("id"),rs.getDate("fecha_registro"),rs.getDouble("puntuacion"),
                        rs.getDouble("moneda"));
            }
        }catch (SQLException e){

        }
        return null;
    }

    public Usuario find (Integer id){
        String query = "SELECT ID, NICKNAME, PASSWORD, SEXO, NOMBRE, MONEDA, PUNTUACION, FECHA_NACIMIENTO, FECHA_REGISTRO FROM USUARIO WHERE ID = ?";
        List<Object> l = new ArrayList<>();
        l.add(id);
        ResultSet rs = SQL.execQuery(query,l);
        try{
            while(rs.next()){
                return new Usuario(rs.getString("nombre"),rs.getString("nickname"),
                        rs.getString("password"),rs.getDate("fecha_nacimiento"),rs.getBoolean("sexo"),
                        rs.getInt("id"),rs.getDate("fecha_registro"),rs.getDouble("puntuacion"),
                        rs.getDouble("moneda"));
            }
        }catch (SQLException e){

        }
        return null;
    }
    public Usuario identify(String user, String password){
        String query = "SELECT ID, NICKNAME, PASSWORD, SEXO, NOMBRE, MONEDA, PUNTUACION, FECHA_NACIMIENTO, FECHA_REGISTRO " +
                "FROM USUARIO WHERE NICKNAME = ? AND PASSWORD = ?";
        List<Object> l = new ArrayList<>();
        l.add(user);
        l.add(password);
        ResultSet rs = SQL.execQuery(query,l);
         try{
             while(rs.next()){
                 return new Usuario(rs.getString("nombre"),rs.getString("nickname"),
                         rs.getString("password"),rs.getDate("fecha_nacimiento"),rs.getBoolean("sexo"),
                         rs.getInt("id"),rs.getDate("fecha_registro"),rs.getDouble("puntuacion"),
                         rs.getDouble("moneda"));
             }
         }catch (SQLException e){

         }
        return null;
    }

}
