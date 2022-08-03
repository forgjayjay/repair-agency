package com.servlet_project.login;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
public class LoginDao {  
    public boolean validate(String name,String pass){  
        boolean status=false;  
        try(Connection con=DriverManager.getConnection(getUrlToDB())  ){  

        PreparedStatement ps=con.prepareStatement(
            "select * from users where login = ? and passwrd = ?"
        );  
        ps.setString(1,name);  
        ps.setString(2,pass);  
            
        ResultSet rs=ps.executeQuery();  
        status=rs.next();  
                
        }catch(Exception e){System.out.println(e);}  
        return status;  
    } 

    public String userType(String name){
        String type = "";
        try(Connection con=DriverManager.getConnection(getUrlToDB())  ){  

            PreparedStatement ps=con.prepareStatement(
                "select * from users where login = ?"
            ); 
            
            ps.setString(1,name);  
            ResultSet rs=ps.executeQuery(); 
            while(rs.next()){
                type = rs.getString("acc_type");
            }
        } catch(Exception e){System.out.println(e);}  
        return type;
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
