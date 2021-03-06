package es.iesfranciscodelosrios.model;


import es.iesfranciscodelosrios.utils.SQL;
import es.iesfranciscodelosrios.utils.Utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class UsuarioDAO extends DAOConnection {

    private static UsuarioDAO instance;
    private UsuarioDAO(){
        super();
    }

    public static UsuarioDAO getInstance(){
        if( instance == null){
            instance = new UsuarioDAO();
            users=instance;
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

            String query = "INSERT INTO Usuario (nickname, password, sexo, nombre,fecha_nacimiento) VALUES (?,?,?,?,?)";
             res = SQL.execUpdate(query,l,true);
        }
        return res != -1;
    }

    public Usuario find (String user){
        String query = "SELECT id, nickname, password, sexo, nombre, moneda, puntuacion, fecha_nacimiento, fecha_registro FROM Usuario WHERE nickname = ?";
        List<Object> l = new ArrayList<>();
        l.add(user);
        ResultSet rs = SQL.execQuery(query,l);
        try{
            while(rs.next()){
                Usuario usuario = new Usuario(rs.getString("nombre"),rs.getString("nickname"),
                        rs.getString("password"),rs.getDate("fecha_nacimiento"),rs.getBoolean("sexo"),
                        rs.getInt("id"),rs.getTimestamp("fecha_registro").toLocalDateTime(),rs.getDouble("puntuacion"),
                        rs.getDouble("moneda"));
                List<Pregunta> pr = preguntas.getAll(usuario.getId());
                pr.forEach(p ->p.setOwner(usuario));
                usuario.setPreguntas(pr);
                return usuario;
            }
        }catch (SQLException e){
            Log.log(Level.SEVERE, Utils.exceptionInfo(e));
        }
        return null;
    }

    public Usuario find (Integer id){
        String query = "SELECT ID, NICKNAME, PASSWORD, SEXO, NOMBRE, MONEDA, PUNTUACION, FECHA_NACIMIENTO, FECHA_REGISTRO FROM Usuario WHERE ID = ?";
        List<Object> l = new ArrayList<>();
        l.add(id);
        ResultSet rs = SQL.execQuery(query,l);
        try{
            while(rs.next()){
                Usuario user = new Usuario(rs.getString("nombre"),rs.getString("nickname"),
                        rs.getString("password"),rs.getDate("fecha_nacimiento"),rs.getBoolean("sexo"),
                        rs.getInt("id"),rs.getTimestamp("fecha_registro").toLocalDateTime(),rs.getDouble("puntuacion"),
                        rs.getDouble("moneda"));
                List<Pregunta> pr = preguntas.getAll(user.getId());
                pr.forEach(p ->p.setOwner(user));
                user.setPreguntas(pr);
                return user;
            }
        }catch (SQLException e){
            Log.log(Level.SEVERE, Utils.exceptionInfo(e));
        }
        return null;
    }
    public Usuario identify(String user, String password){
        String query = "SELECT id, nickname, password, sexo, nombre, moneda, puntuacion, fecha_nacimiento, fecha_registro " +
                "FROM Usuario WHERE nickname = ? AND password = ?";
        List<Object> l = new ArrayList<>();
        l.add(user);
        l.add(password);
        ResultSet rs = SQL.execQuery(query,l);
         try{
             while(rs.next()){
                 Usuario usuario = new Usuario(rs.getString("nombre"),rs.getString("nickname"),
                         rs.getString("password"),rs.getDate("fecha_nacimiento"),rs.getBoolean("sexo"),
                         rs.getInt("id"),rs.getTimestamp("fecha_registro").toLocalDateTime(),rs.getDouble("puntuacion"),
                         rs.getDouble("moneda"));
                 List<Pregunta> pr = preguntas.getAll(usuario.getId());
                 pr.forEach(p ->p.setOwner(usuario));
                 usuario.setPreguntas(pr);
                 return usuario;
             }
         }catch (SQLException e){
             Log.log(Level.SEVERE, Utils.exceptionInfo(e));
         }
        return null;
    }

}
