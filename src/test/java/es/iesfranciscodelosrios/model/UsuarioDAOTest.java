package es.iesfranciscodelosrios.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class UsuarioDAOTest {

    @Test
    void testConstructorOfflineConnection(){
        assertDoesNotThrow(()->{
            UsuarioDAO users = UsuarioDAO.getInstance();
            assertNotNull(UsuarioDAO.getType());
            assertNotNull(UsuarioDAO.getConnection());
            assertFalse(UsuarioDAO.getType());
        });
    }

    @Test
    void testConstructorOnlineConnection(){
        assertDoesNotThrow(()->{
            UsuarioDAO users = UsuarioDAO.getInstance();
            assertNotNull(UsuarioDAO.getType());
            assertNotNull(UsuarioDAO.getConnection());
            assertTrue(UsuarioDAO.getType());
        });
    }
}