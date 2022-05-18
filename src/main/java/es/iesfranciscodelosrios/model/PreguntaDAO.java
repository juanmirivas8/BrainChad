package es.iesfranciscodelosrios.model;

import es.iesfranciscodelosrios.utils.SQL;
import es.iesfranciscodelosrios.utils.Utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class PreguntaDAO extends DAOConnection{

    private PreguntaDAO(){
        super();
    }

    private static PreguntaDAO instance;

    public static PreguntaDAO getInstance(){
        if(instance == null){
            instance = new PreguntaDAO();
            preguntas = instance;
        }
        return instance;
    }

    public Pregunta get(Integer id){

        String query ="SELECT id, userID, titulo, respCorrecta, respInc1, respInc2, respInc3, imagen, categoria, fecha_Creacion FROM Pregunta WHERE Pregunta.id = ?";

        return null;

    }

    public Boolean insert (Pregunta p){
        String query = "INSERT INTO Pregunta(userID, titulo, respCorrecta,respInc1, respInc2, respInc3, categoria) VALUES (?,?,?,?,?,?,?)";
        List<Object> l = new ArrayList<>();
        l.add(p.getUserId());
        l.add(p.getTitulo());
        l.add(p.getrCorrecta());
        l.add(p.getrIn_1());
        l.add(p.getrIn_2());
        l.add(p.getrIn_3());
        l.add(p.getCategoria());

        return SQL.execUpdate(query,l,true) != -1;
    }

    public List<Pregunta> getAll(Integer userId){
        List<Object> l = new ArrayList<>();
        l.add(userId);
        List<Pregunta> ret = new ArrayList<>();

        String query = "SELECT id, userID, titulo, respCorrecta, respInc1, respInc2, respInc3,imagen, categoria, fecha_Creacion FROM Pregunta WHERE Pregunta.userID = ?";

        ResultSet rs = SQL.execQuery(query,l);

        try{
            while(rs.next()){
                Pregunta p = new Pregunta();
                p.setId(rs.getInt(1));
                p.setUserId(rs.getInt(2));
                p.setTitulo(rs.getString(3));
                p.setrCorrecta(rs.getString(4));
                p.setrIn_1(rs.getString(5));
                p.setrIn_2(rs.getString(6));
                p.setrIn_3(rs.getString(7));
                p.setCategoria(rs.getString(9));
                p.setFecha_creacion(rs.getTimestamp(10).toLocalDateTime());
                ret.add(p);
            }
        }catch (SQLException e){
            Log.log(Level.SEVERE, Utils.exceptionInfo(e));
        }

        return ret;
    }

    public Boolean remove(Integer id){
        String query ="DELETE FROM Pregunta WHERE id =?";
        List<Object> l = new ArrayList<>();
        l.add(id);
        return SQL.execUpdate(query,l,false)!= 0;
    }

    public Boolean update(Pregunta p){
        String query = "UPDATE Pregunta SET Pregunta.userID = ? , Pregunta.categoria = ?, Pregunta.titulo = ?,Pregunta.respCorrecta = ?,Pregunta.respInc1 = ?, Pregunta.respInc2 = ?, Pregunta.respInc3 = ?, Pregunta.fecha_Creacion = ? WHERE Pregunta.id = ?";
        List<Object> l = new ArrayList<>();
        l.add(p.getUserId());
        l.add(p.getCategoria());
        l.add(p.getTitulo());
        l.add(p.getrCorrecta());
        l.add(p.getrIn_1());
        l.add(p.getrIn_2());
        l.add(p.getrIn_3());
        l.add(p.getFecha_creacion());
        l.add(p.getId());
        return SQL.execUpdate(query,l,false) != 0;
    }

}
