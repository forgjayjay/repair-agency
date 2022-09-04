package com.servlet_project;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.*;
import java.sql.*;
import java.util.Properties;
import java.util.Random;

import com.servlet_project.dbmanager.Constants;

import org.junit.jupiter.api.*;


import com.servlet_project.dbmanager.DBManager;

public class DBManagerTest{
    Random rand = new Random((long) (Math.random()*100));
    String adrianName = "adrian";
    int adrianManagerId = 0;
    int adrianCraftsmanId = 0;
    int adrianUserId = 0;

    @AfterAll
    public void testClean() throws Exception{
        DBManagerTest dmbTest = new DBManagerTest();
        String sql = "delete from orders where user_id = " + dmbTest.adrianUserId;
        String sql1 = "delete from users where user_id = " + dmbTest.adrianUserId;
        String sql2 = "delete from users where user_id = " + dmbTest.adrianManagerId;
        String sql3 = "delete from users where user_id = " + dmbTest.adrianCraftsmanId;
        String sql5 = "delete from users where passwrd = 'adrian'";
        String[] str = {sql,sql1,sql2,sql3,sql5};
        try (Connection con = DriverManager.getConnection(getUrlToDB())){
            for (String string : str) {
                PreparedStatement ps=con.prepareStatement(string);
                ps.executeUpdate();
                ps.close();
            }
            con.close();
        } catch (Exception e) {
            throw e;
        }
    }

    DBManager dbm = DBManager.getInstance();
    @Test 
    public void testInsertUser(){
        adrianName += rand.nextInt();
        assertTrue(dbm.insertUser(adrianName, "adrian"), adrianName);
        assertFalse(dbm.insertUser("admin", "admin"));
        assertTrue(dbm.validate(adrianName, "adrian"));
        assertEquals("user", dbm.userType(adrianName));
        assertTrue(dbm.insertOrder(adrianName));
        adrianUserId = dbm.userId(adrianName);
    }
    
    @Test 
    public void testInsertManager(){
        adrianName += rand.nextInt();
        assertTrue(dbm.insertManager(adrianName, "adrian"),adrianName);
        assertFalse(dbm.insertManager("admin", "admin"));
        assertTrue(dbm.validate(adrianName, "adrian"));
        assertEquals("manager", dbm.userType(adrianName));
        
        
        adrianManagerId = dbm.userId(adrianName);

    }
    @Test 
    public void testValidate2(){
        
    }
    @Test
    public void testUserType2(){
        
    }
    @Test 
    public void testInsertCraftsman(){
        adrianName += rand.nextInt();
        assertTrue(dbm.insertCraftsman(adrianName, "adrian","adrian"),adrianName);
        assertFalse(dbm.insertCraftsman("admin", "admin","admin"));
        assertTrue(dbm.validate(adrianName, "adrian"));
        assertEquals("craftsman", dbm.userType(adrianName));
        adrianCraftsmanId = dbm.userId(adrianName);
    }

    @Test
    public void testAppointCraftsman(){
        assertFalse(dbm.appointCraftsman(adrianCraftsmanId, -1));        
    }
    @Test
    public void testPriceOrder(){
        assertFalse(dbm.priceOrder(100.99, -1));        
    }
    
    private static String getUrlToDB() {
        String url = null;
        try (InputStream in = new FileInputStream(Constants.SETTINGS_FILE)) {
            Properties prop = new Properties();
            prop.load(in);
            url =prop.getProperty("connection.url");
        } catch (IOException e) {

        }
        return url;
    }
}
