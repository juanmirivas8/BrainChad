package es.iesfranciscodelosrios.model;

import java.util.ArrayList;
import java.util.List;

public class PreguntaDAO extends DAOConnection{

    private PreguntaDAO(){
        super();
    }

    private static PreguntaDAO instance;

    public static PreguntaDAO getInstance(){
        if(instance == null){
            instance = new PreguntaDAO();
        }
        return instance;
    }

    public List<Pregunta> getAll(Integer userID){
        List<Pregunta> l = new ArrayList<>();

        return l;

    }
}
