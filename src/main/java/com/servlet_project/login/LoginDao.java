package com.servlet_project.login;

import com.servlet_project.dbmanager.dbmanager;

/*
 * A class for working with Login page and handling 
 *      all the operations unbeknown to user
 */

public class LoginDao { 

    dbmanager dbm = dbmanager.getInstance();

    public boolean validate(String name,String pass){  
        return dbm.validate(name, pass);
    } 

    public String userType(String name){
        return dbm.userType(name);
    }
}  
