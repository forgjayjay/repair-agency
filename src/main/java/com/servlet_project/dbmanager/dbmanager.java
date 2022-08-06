package com.servlet_project.dbmanager;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

/*
 * A class for working with Database
*/

public class dbmanager {

    private static dbmanager instance;

	private dbmanager() {
	}

	public static synchronized dbmanager getInstance() {
		if (instance == null) {
			instance = new dbmanager();
		}
		return instance;
	}

    public boolean insertUser(String name,String pass){  
        boolean status=false; 
        int i = 0;

        try(
            Connection con = DriverManager.getConnection(getUrlToDB());
        ){  
            PreparedStatement ps = con.prepareStatement(constants.FIND_USER);
            ps.setString(1,name);
            ResultSet rs = ps.executeQuery();  
            if( status=rs.next()){
                return false;
            }
            ps = con.prepareStatement(
                constants.INSERT_USER
            );  
            ps.setString(1,name);  
            ps.setString(2,pass);  
        
            i += ps.executeUpdate();  
            status = i > 0;
            }catch(Exception e){ System.out.println(e); } 
        return status;  
    }
    public boolean insertManager(String name,String pass){  
        boolean status=false; 
        int i = 0;

        try(
            Connection con = DriverManager.getConnection(getUrlToDB());
        ){  
            PreparedStatement ps = con.prepareStatement(constants.FIND_USER);
            ps.setString(1,name);
            ResultSet rs = ps.executeQuery();  
            if( status=rs.next()){
                ps = con.prepareStatement(constants.UPDATE_USER);
                ps.setString(1, "acc_type = 'manager'"); 
                ps.setString(2,name); 
                ps.executeUpdate(); 
                i += ps.executeUpdate();  
                status = i > 0;
                ps.close();
                return status;
            } else if(pass.equals("")){
                return false;
            }
            ps = con.prepareStatement(
                constants.INSERT_MNGR
            );  
            ps.setString(1,name);  
            ps.setString(2,pass);  
        
            i += ps.executeUpdate();  
            status = i > 0;
            }catch(Exception e){ System.out.println(e); } 
        return status;  
    }

    public String userType(String name){
        String type = "";
        try(
            Connection con=DriverManager.getConnection(getUrlToDB());
            PreparedStatement ps=con.prepareStatement(constants.FIND_USER);
        ){  

            
            
            ps.setString(1,name);  
            ResultSet rs=ps.executeQuery(); 
            while(rs.next()){
                type = rs.getString("acc_type");
            }
        } catch(Exception e){ System.out.println(e); }  
        return type;
    }
    
    public boolean validate(String name,String pass){  
        boolean status=false;  
        try(
            Connection con=DriverManager.getConnection(getUrlToDB());
            PreparedStatement ps=con.prepareStatement(constants.VALIDATE_USER);
        ){  

          
            ps.setString(1,name);  
            ps.setString(2,pass);  
                
            ResultSet rs=ps.executeQuery();  
            status=rs.next();  
                
        }catch(Exception e){System.out.println(e);}  
        return status;  
    } 

    private String getUrlToDB() {
        String url = null;
        try (InputStream in = new FileInputStream(constants.SETTINGS_FILE)) {
            Properties prop = new Properties();
            prop.load(in);
            url =prop.getProperty("connection.url");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return url;
    }
}
