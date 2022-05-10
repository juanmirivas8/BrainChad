package es.iesfranciscodelosrios.controllers;

import es.iesfranciscodelosrios.model.UsuarioDAO;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public abstract class Controller {
    protected static List<Object> objects;
    protected static UsuarioDAO users;
    protected static boolean instanciated;

    public Controller(){
        if(!instanciated){
            objects = new ArrayList<>();
            users = UsuarioDAO.getInstance();
            instanciated = true;
        }
    }
}
