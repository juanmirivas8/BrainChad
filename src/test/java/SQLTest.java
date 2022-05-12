import es.iesfranciscodelosrios.utils.SQL;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class SQLTest {

    @Test
    public void testConnectionSQL(){
        assertDoesNotThrow(()->{
            Connection cn = SQL.getConnection("src/main/resources/es/iesfranciscodelosrios/others/sql.xml",null);
            assertNotNull(cn);
        });
        SQL.disconnect();
    }

    @Test
    public void testInaccesibleConnectionSQL(){
        assertDoesNotThrow(()->{
            Connection cn = SQL.getConnection("src/main/resources/es/iesfranciscodelosrios/others/sql.xml",null);
            assertNull(cn);
            assertNull(SQL.cn);
            assertNull(SQL.info);
            assertNull(SQL.instance);
        });
        SQL.disconnect();
    }

    @Test
    public void testConnectionH2(){
        assertDoesNotThrow(()->{
            Connection cn = SQL.getConnection("src/main/resources/es/iesfranciscodelosrios/others/h2.xml",null);
            assertNotNull(cn);
        });
        SQL.disconnect();
    }



}