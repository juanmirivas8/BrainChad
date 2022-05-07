import es.iesfranciscodelosrios.utils.ConnectionData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConnectionDataTest {

    @Test
    public void testLoadSQL(){
        assertDoesNotThrow(()->{
            ConnectionData aux = new ConnectionData("src/main/resources/es/iesfranciscodelosrios/others/sql.xml");
        });
    }
    @Test
    public void testAttributesSQL(){
        ConnectionData aux = new ConnectionData("src/main/resources/es/iesfranciscodelosrios/others/sql.xml");
        assertNotEquals(null,aux.getDatabase());
        assertNotEquals(null,aux.getServer());
        assertNotEquals(null,aux.getPassword());
        assertNotEquals(null,aux.getUser());
        assertNotEquals("",aux.getDatabase());
        assertNotEquals("",aux.getServer());
        assertNotEquals("",aux.getPassword());
        assertNotEquals("",aux.getUser());
    }

    @Test
    public void testLoadH2(){
        assertDoesNotThrow(()->{
            ConnectionData aux = new ConnectionData("src/main/resources/es/iesfranciscodelosrios/others/h2.xml");
        });
    }
    @Test
    public void testAttributesH2(){
        ConnectionData aux = new ConnectionData("src/main/resources/es/iesfranciscodelosrios/others/h2.xml");
        assertNotEquals(null,aux.getDatabase());
        assertNotEquals(null,aux.getServer());
        assertNotEquals(null,aux.getPassword());
        assertNotEquals(null,aux.getUser());
        assertNotEquals("",aux.getDatabase());
        assertNotEquals("",aux.getServer());
        assertNotEquals("",aux.getPassword());
        assertNotEquals("",aux.getUser());
    }
}