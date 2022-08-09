package com.servlet_project.dbmanager;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import org.apache.log4j.Logger;

/*
 * A class for working with Database
*/

public class dbmanager {

    private static dbmanager instance;
    private final Logger logger = Logger.getLogger(
        this.getClass()
    );
    

	private dbmanager() {
        
	}

	public static synchronized dbmanager getInstance() {

		if (instance == null) {
            
			instance = new dbmanager();
		}
		return instance;
	}

    public boolean insertUser(String name,String pass){  
        logger.debug("Run insert user into database method");
        boolean status=false; 
        int i = 0;

        try(
            Connection con = DriverManager.getConnection(getUrlToDB());
        ){  
            logger.debug("Looking if user already exists");
            PreparedStatement ps = con.prepareStatement(constants.FIND_USER);
            ps.setString(1,name);
            ResultSet rs = ps.executeQuery();  
            if( status=rs.next()){
                return false;
            }
            logger.debug("Inserting new user into system");
            ps = con.prepareStatement(
                constants.INSERT_USER
            );  
            ps.setString(1,name);  
            ps.setString(2,pass);  
        
            i += ps.executeUpdate();  
            status = i > 0;
            }catch(Exception e){ logger.error("Error on insertUser"); } 
        return status;  
    }
    public boolean insertManager(String name,String pass){  
        logger.debug("Run insert manager into database method");

        boolean status=false; 
        int i = 0;

        try(
            Connection con = DriverManager.getConnection(getUrlToDB());
        ){  
            logger.debug("Looking if manager already exists");

            PreparedStatement ps = con.prepareStatement(constants.FIND_USER);
            ps.setString(1,name);
            ResultSet rs = ps.executeQuery();  
            if( status=rs.next()){
                logger.debug("Updating the existing user to be manager");
                ps = con.prepareStatement(constants.UPDATE_USER);
                ps.setString(1, "manager"); 
                ps.setString(2,name); 
                ps.executeUpdate(); 
                i += ps.executeUpdate();  
                status = i > 0;
                ps.close();
                return status;
            } else if(pass.equals("")){
                logger.debug("No user found and no password specified");
                return false;
            }
            ps.close();
            logger.debug("Inserting manager into database");
            ps = con.prepareStatement(
                constants.INSERT_MNGR
            );  
            ps.setString(1,name);  
            ps.setString(2,pass);  
        
            i += ps.executeUpdate();  
            status = i > 0;
            }catch(Exception e){ logger.error("Error on insertManager"); } 
        return status;  
    }
    public boolean insertCraftsman(String login,String pass, String name){  
        logger.debug("Run insert manager into database method");

        boolean status=false; 
        int i = 0;
        int newID = 0;
        try(
            Connection con = DriverManager.getConnection(getUrlToDB());
        ){  
            logger.debug("Looking if user already exists");
            PreparedStatement ps = con.prepareStatement(constants.FIND_USER);
            ps.setString(1,login);

            ResultSet rs = ps.executeQuery();  
            if( status=rs.next()){
                logger.debug("Updating the existing user to be craftsman");
                newID = rs.getInt(1);
                ps = con.prepareStatement(constants.UPDATE_USER);
                ps.setString(1, "craftsman"); 
                ps.setString(2,login); 
                ps.executeUpdate(); 
                logger.debug("Adding the existing user to craftsman list");
                ps = con.prepareStatement(constants.INSERT_INTO_CRFTSMN);
                ps.setInt(1, newID);
                ps.setString(2, name);
                i += ps.executeUpdate();  
                status = i > 0;
                ps.close();

                return status;
            } else if(pass.equals("")){
                return false;
            }

            ps.close();

            logger.debug("Inserting craftsman into database");
            ps = con.prepareStatement(
                constants.INSERT_CRFTSMN
            );  
            ps.setString(1,login);  
            ps.setString(2,pass);  

            i += ps.executeUpdate(); 
           
            logger.debug("Adding new user to craftsman list");
            ps = con.prepareStatement(constants.FIND_USER);
            ps.setString(1,login);
            rs = ps.executeQuery(); 

            while(rs.next()){
                newID = rs.getInt(1);
            }

            ps = con.prepareStatement(constants.INSERT_INTO_CRFTSMN);
            ps.setInt(1, newID);
            ps.setString(2, name);
            i += ps.executeUpdate();
            status = i > 0;

            }catch(Exception e){ 
                logger.error("Error on insertCraftsman");
                status=false;
             } 
        return status;  
    }
    public boolean insertOrder(String name){  
        logger.debug("Run insert manager into database method");

        boolean status=false; 
        int i = 0;

        try(
            Connection con = DriverManager.getConnection(getUrlToDB());
        ){  
            logger.debug("Looking for user id to add to order");
            int userID = 0;
            PreparedStatement ps = con.prepareStatement(constants.FIND_USER);
            ps.setString(1,name);
            ResultSet rs = ps.executeQuery();  
            while(rs.next()){
                userID=rs.getInt(1);
            }
            ps.close();
            logger.debug("Inserting order into database");
            ps = con.prepareStatement(
                constants.INSERT_NEW_ORDER
            );  
            ps.setInt(1,userID);
            ps.setString(2, constants.ORDER_STATUS_ACCEPT);  
            ps.setString(3,constants.UNPAID_STATUS);  
        
            i += ps.executeUpdate();  
            status = i > 0;
            }catch(Exception e){ 
                logger.error("Error on insertOrder"); 
            } 
        return status;  
    }
    public String showOrder(String name){  
        logger.debug("Run show order");
        ResultSet rs = null;
        int userID = 0;
        String str = "";
        String craftsman_unassigned = "unassinged";
        try(
            Connection con = DriverManager.getConnection(getUrlToDB());
        ){  
            logger.debug("Looking for order");
            PreparedStatement ps = con.prepareStatement(constants.FIND_USER);
            ps.setString(1,name);
             rs = ps.executeQuery();  
            if( rs.next()){
                userID = rs.getInt(1);
            } else return "\n";
            ps.close();
            ps = con.prepareStatement(
                constants.SHOW_ORDER
            );  
            ps.setInt(1, userID);
            
            rs = ps.executeQuery(); 
            while(rs.next()){
                str += "Order number: " + Integer.toString(rs.getInt("id")) + "\n";
                
                str += "User: " + Integer.toString(rs.getInt("user_id"))+"\n";
                if(rs.getInt("craftsman_id") == 1) str += " Craftsman: " + craftsman_unassigned+"\n";
                else str += " Craftsman: " + Integer.toString(rs.getInt("craftsman_id"))+"\n";
                
                str += " Order status: " + rs.getString("order_status")+"\n";
                
                
                str += " Payment status: " + rs.getString("payment_status") + "\n\n\n";
            }
            rs.close();
            }catch(Exception e){ logger.error("Error on insertCraftsman"); } 
        return str;  
    }
    public String userType(String name){
        logger.debug("Run userType method");
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
