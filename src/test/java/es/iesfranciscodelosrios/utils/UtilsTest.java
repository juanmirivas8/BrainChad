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
}