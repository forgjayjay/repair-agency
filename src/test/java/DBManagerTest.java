import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.*;
import java.sql.*;
import java.util.Properties;

import org.junit.jupiter.api.Test;

import com.servlet_project.dbmanager.Constants;
import com.servlet_project.dbmanager.DBManager;

public class DBManagerTest{

    DBManager dbm = DBManager.getInstance();
    @Test 
    public void testValidate(){
        assertTrue(dbm.validate("admin", "admin"));
    }
    @Test 
    public void testValidate2(){
        assertFalse(dbm.validate("admin", "123"));
    }
    @Test 
    public void testValidate3(){
        assertTrue(dbm.validate("admin", "AdMiN"));
    }
    @Test 
    public void testInsertUser(){
        assertFalse(dbm.insertUser("admin", "admin"));
    }
    @Test 
    public void testInsertManager(){
        assertFalse(dbm.insertManager("admin", "admin"));
    }
    @Test 
    public void testInsertCraftsman(){
        assertFalse(dbm.insertUser("admin", "AdMiN"));
    }
    
    @Test
    public void testAppointCraftsman(){
        assertFalse(dbm.appointCraftsman(1, -1));
    }
    @Test
    public void testPriceOrder(){
        assertEquals(false, dbm.priceOrder(0.1, -1));        
    }
    @Test
    public void testUserType(){
        assertEquals("admin", dbm.userType("admin"));
    }
    @Test
    public void testInsertOrder() throws IOException, SQLException{
        assertTrue(dbm.insertOrder("admin"));
        String sql = "delete from orders where user_id = 1";
        InputStream in = new FileInputStream(Constants.SETTINGS_FILE);
        Properties prop = new Properties();
        prop.load(in);
        String url =prop.getProperty("connection.url");
        Connection con = DriverManager.getConnection(url);
        PreparedStatement ps=con.prepareStatement(sql);
        ps.executeUpdate();
        ps.close();
        con.close();
        in.close();
    }
}
