package es.iesfranciscodelosrios.model;

import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;
class UsuarioDAOTest {

    @Test
    void testConstructorOfflineConnection(){
        assertDoesNotThrow(()->{
            UsuarioDAO users = UsuarioDAO.getInstance();
            assertNotNull(UsuarioDAO.online);
            assertNotNull(UsuarioDAO.cn);
            assertFalse(UsuarioDAO.online);
        });
    }

    @Test
    void testConstructorOnlineConnection(){
        assertDoesNotThrow(()->{
            UsuarioDAO users = UsuarioDAO.getInstance();
            assertNotNull(UsuarioDAO.online);
            assertNotNull(UsuarioDAO.cn);
            assertTrue(UsuarioDAO.online);
        });
    }

    @Test
    void testFindByID(){
        assertDoesNotThrow(()->{
            UsuarioDAO users = UsuarioDAO.getInstance();
            assertNotNull(users.find(0));
            assertEquals(users.find(0).getNickname(),"admin");
        });
    }

    @Test
    void testFindByName(){
        assertDoesNotThrow(()->{
            UsuarioDAO users = UsuarioDAO.getInstance();
            assertNotNull(users.find("admin"));
            assertEquals(users.find(0).getNickname(),"admin");
        });
    }

    @Test
    void testInsertNewUser(){
        Date date = Date.valueOf("1997-10-02");
        Usuario user = new Usuario("Juan Miguel Rivas Velasco","juanmi_rivas","04d3368f72736ed54c3cb63454eef23c2ecfb1deed27e2a4aa8e442e898fdbf5"
                ,date,true,null,null,null,null);
        UsuarioDAO users = UsuarioDAO.getInstance();
        users.insertNewUser(user);
        assertNotNull(users.find("juanmi_rivas"));
    }
}