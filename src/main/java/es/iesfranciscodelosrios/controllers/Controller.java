package es.iesfranciscodelosrios.controllers;

import es.iesfranciscodelosrios.model.UsuarioDAO;
import es.iesfranciscodelosrios.utils.Utils;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public abstract class Controller {
    protected static List<Object> objects;
    protected static UsuarioDAO users;
    protected static boolean instanciated;
    protected static final Logger Log = Utils.getLogger();

    public Controller(){
        if(!instanciated){
            objects = new ArrayList<>();
            users = UsuarioDAO.getInstance();
            instanciated = true;
        }
    }
}
