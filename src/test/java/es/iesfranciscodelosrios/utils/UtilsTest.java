package es.iesfranciscodelosrios.utils;

import es.iesfranciscodelosrios.model.UsuarioDAO;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    @Test
    void getFileAsLines() {
        assertDoesNotThrow(()->{
            List<String> l = Utils.getFileAsLines("src/main/resources/es/iesfranciscodelosrios/others/h2.sql");
            assertNotNull(l);
            assertEquals("create schema if not exists BrainChad;",l.get(0));
        });
    }

    @Test
    void getFileAsLinesWithScanner(){
        assertDoesNotThrow(()->{
            UsuarioDAO users = UsuarioDAO.getInstance();
            UsuarioDAO.close();
        });
    }
    @Test
    void testSHA256(){
        assertDoesNotThrow(()->{
            assertEquals(Utils.encryptSHA256("terces"),"04d3368f72736ed54c3cb63454eef23c2ecfb1deed27e2a4aa8e442e898fdbf5");
        });
    }

}