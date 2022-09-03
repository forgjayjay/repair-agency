package com.servlet_project.login;

import com.servlet_project.dbmanager.DBManager;

/*
 * A class for working with Registration page and handling 
 *      all the operations unbeknown to user
 */

public class RegistrationDao {  
public boolean insert(String name,String pass, String pass2){  
        DBManager dbm = DBManager.getInstance();
        int count = 0; 
        boolean status = false;
        name = name.trim();
        pass = pass.trim();
        pass2 = pass2.trim();
        if(pass.equals(pass2)) return status;
        if(name.contains(" ") || pass.contains(" ")) return status;
        if( 6 <= pass.length() && pass.length() <= 20  ){
            if( pass.matches(".*\\d.*") ){
                count ++;
            }if( pass.matches(".*[a-z].*") ){
                count ++;
            }if( pass.matches(".*[A-Z].*") ){
                count ++;
            }
            if(count>1) status = dbm.insertUser(name, pass);
            return status;
        }
        return status;
    }
}  
