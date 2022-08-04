package com.servlet_project.login;

import com.servlet_project.dbmanager.dbmanager;

/*
 * A class for working with Login page and handling 
 *      all the operations unbeknown to user
 */

public class LoginDao { 


    public boolean validate(String name,String pass){  
        dbmanager dbm = dbmanager.getInstance();
        return dbm.validate(name, pass);
    } 

    public String userType(String name){
        dbmanager dbm = dbmanager.getInstance();
        return dbm.userType(name);
    }
}  
