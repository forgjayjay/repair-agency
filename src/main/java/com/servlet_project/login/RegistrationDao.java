package com.servlet_project.login;

import com.servlet_project.dbmanager.dbmanager;

/*
 * A class for working with Registration page and handling 
 *      all the operations unbeknown to user
 */

public class RegistrationDao {  
public boolean insert(String name,String pass){  
        dbmanager dbm = dbmanager.getInstance();
        return dbm.insert(name, pass);
    }
}  
