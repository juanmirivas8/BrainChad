package es.iesfranciscodelosrios.model;

public class UsuarioDAO extends DAOConnection implements CRUD<Integer, UsuarioDAO>{

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
    @Override
    public Boolean insert(UsuarioDAO object) {
        return null;
    }

    @Override
    public Boolean delete(Integer key) {
        return null;
    }

    @Override
    public Boolean update(UsuarioDAO object) {
        return null;
    }

    @Override
    public Boolean find(Integer key) {
        return null;
    }
}
