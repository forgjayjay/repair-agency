package com.servlet_project.login;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
public class RegistrationDao {  
public boolean insert(String name,String pass){  
    boolean status=false; 
    int i = 0;
    try(Connection con=DriverManager.getConnection(getUrlToDB())  ){  
    
    PreparedStatement ps=con.prepareStatement(
        "select * from users where login = ?"
    ); 
    ps.setString(1,name);
    ResultSet rs = ps.executeQuery();  
    if( status=rs.next()){
        return false;
    }
    ps = con.prepareStatement(
        "insert into users values (0, ? , ? )"
    );  
    ps.setString(1,name);  
    ps.setString(2,pass);  

    i += ps.executeUpdate();  
    if (i > 0) status = true;
    }catch(Exception e){System.out.println(e);}  
        return status;  
    }

    private String getUrlToDB() {
        String url = null;
        try (InputStream in = new FileInputStream("dbconnection.properties")) {
            Properties prop = new Properties();
            prop.load(in);
            url =prop.getProperty("connection.url");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return url;
    }
}  
