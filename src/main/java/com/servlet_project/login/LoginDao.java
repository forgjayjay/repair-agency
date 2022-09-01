package com.servlet_project.login;

import com.servlet_project.dbmanager.DBManager;

/*
 * A class for working with Login page and handling 
 *      all the operations unbeknown to user
 */

public class LoginDao { 

    DBManager dbm = DBManager.getInstance();

    public boolean validate(String name,String pass){ 
        name = name.trim();
        pass = pass.trim();
        return dbm.validate(name, pass);
    } 

    public String userType(String name){
        return dbm.userType(name);
    }
}  
