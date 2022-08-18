package com.servlet_project.dbmanager;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
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
            }catch(Exception e){ 
                logger.error("Error on insertUser"); 
                logger.error(e);
            } 
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
            }catch(Exception e){ 
                logger.error("Error on insertManager"); 
                logger.error(e);
            }
        return status;  
    }
    public boolean insertCraftsman(String login, String pass, String name){  
        logger.debug("Run insert craftsman into database method");

        boolean status=false; 
        int i = 0;
        int newID = 0;
        try(
            Connection con = DriverManager.getConnection(getUrlToDB());
        ){  
            logger.debug("Looking if craftsman already exists");
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
           
            logger.debug("Adding new craftsman to craftsman list");
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
                logger.error(e);

                status=false;
             } 
        return status;  
    }
    public boolean insertOrder(String name){  
        logger.debug("Run insert order into database method");

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
            if(!rs.next()){
                return false;
            } else{
                do{
                    userID=rs.getInt(1);
                } while(rs.next());
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
                logger.error(e);
            } 
        return status;  
    }
    public ArrayList<String> showOrder(String name, String type){  

        logger.debug("Run show order");
        ResultSet rs = null;
        int userID = 0;
        String str = "";
        String craftsman_unassigned = "unassinged";
        ArrayList<String> stringArray = new ArrayList<>();
        try(
            Connection con = DriverManager.getConnection(getUrlToDB());
        ){  
            logger.debug("Looking for orders");
            PreparedStatement ps = con.prepareStatement(constants.FIND_USER);
            ps.setString(1,name);
             rs = ps.executeQuery();  
            if( rs.next()){
                userID = rs.getInt(1);
            } else return new ArrayList<String>();
            ps.close();
            String statement = "";
            if(type.equals("all")){
                statement = constants.SHOW_ALL_ORDERS;
            }else {
                statement = constants.SHOW_NOT_PAID_ORDER;
            }
            ps = con.prepareStatement(
                statement
            );  
            ps.setInt(1, userID);
            
            rs = ps.executeQuery(); 
            logger.debug("Displaying orders");
            while(rs.next()){
                str = " <br />Order number: " + Integer.toString(rs.getInt("id")) + "";
                
                str += " <br />User: " + Integer.toString(rs.getInt("user_id"))+"";
                if(rs.getInt("craftsman_id") == 1) str += " \nCraftsman: " + craftsman_unassigned+"\n";
                else str += " <br />Craftsman: " + Integer.toString(rs.getInt("craftsman_id"))+"";
                
                str += " <br />Order status: " + rs.getString("order_status")+"";
                
                
                str += " <br />Payment status: " + rs.getString("payment_status") + "";
                str += "<br /><br /><br />";
                stringArray.add(str);
            }
            rs.close();
            }catch(Exception e){ 
            logger.error("Error on showOrder");
            logger.error(e);
        } 
        return stringArray;  
    }
    public ArrayList<String> showCraftsmanOrder(String name){  
        logger.debug("Run show craftsman order");
        ResultSet rs = null;
        int userID = 0;
        String str = "";
        ArrayList<String> arrayString = new ArrayList<>();
        try(
            Connection con = DriverManager.getConnection(getUrlToDB());
        ){  
            logger.debug("Looking for orders");
            PreparedStatement ps = con.prepareStatement(constants.FIND_USER);
            ps.setString(1,name);
             rs = ps.executeQuery();  
            if( rs.next()){
                userID = rs.getInt(1);
            } else return new ArrayList<>();
            ps.close();
            
            ps = con.prepareStatement(
                constants.SHOW_ORDER_CRFTSMN
            );  
            ps.setInt(1, userID);
            
            rs = ps.executeQuery(); 
            logger.debug("Displaying orders");
            if(!rs.next()){
                str = "No assigned orders are present";
            } else {
                do{
                    str = "<br />Order number: " + Integer.toString(rs.getInt("id")) + "";
                    
                    str += "<br />User: " + Integer.toString(rs.getInt("user_id"))+"";
                    
                    str += "<br />Order status: " + rs.getString("order_status")+"";
                    
                    
                    str += "<br />Payment status: " + rs.getString("payment_status") + "";
                    str+="<br /><br /><br />";
                    arrayString.add(str);
                }while(rs.next());
            }
            
            
            
            rs.close();
            }catch(Exception e){
                logger.error("Error on showCraftsmanOrder"); 
                logger.error(e);
            } 
        return arrayString;  
    }
    public boolean updateOrderStatus(String order_status, int id){  
        logger.debug("Run update order status method");

        boolean status=false; 
        int i = 0;
        
        try(
            Connection con = DriverManager.getConnection(getUrlToDB());
        ){  
            logger.debug("Looking order");
            PreparedStatement ps = con.prepareStatement(constants.UPDATE_ORDER_STATUS);
            ps.setString(1, order_status);
            ps.setInt(2, id);
            i += ps.executeUpdate();  
            status = i > 0;
            }catch(Exception e){ 
                logger.error("Error on insertOrder"); 
            } 
        return status;  
    }
    public String userType(String name){
        logger.debug("Run userType method");
        String type = "";
        try(
            Connection con=DriverManager.getConnection(getUrlToDB());
            PreparedStatement ps=con.prepareStatement(constants.FIND_USER);
        ){  
            logger.debug("Checking account type");
            ps.setString(1,name);  
            ResultSet rs=ps.executeQuery(); 
            while(rs.next()){
                type = rs.getString("acc_type");
            }
        } catch(Exception e){ logger.debug("Error on userType"); }  
        return type;
    }
    public boolean validate(String name,String pass){
        logger.debug("Run validate method");  
        boolean status=false;  
        try(
            Connection con=DriverManager.getConnection(getUrlToDB());
            PreparedStatement ps=con.prepareStatement(constants.VALIDATE_USER);
        ){  

            logger.debug("Checking for user in database");
            ps.setString(1,name);  
            ps.setString(2,pass);  
                
            ResultSet rs=ps.executeQuery();  
            status=rs.next();  
                
        }catch(Exception e){ 
            logger.debug("Error on validation");
            logger.error(e);
        }  
        return status;  
    } 

    private String getUrlToDB() {
        String url = null;
        logger.debug("Acquiring url to database");
        try (InputStream in = new FileInputStream(constants.SETTINGS_FILE)) {
            Properties prop = new Properties();
            prop.load(in);
            url =prop.getProperty("connection.url");
        } catch (IOException e) {
            logger.error("Error on acquisition of url to database");
            logger.error(e);
        }
        return url;
    }
}
