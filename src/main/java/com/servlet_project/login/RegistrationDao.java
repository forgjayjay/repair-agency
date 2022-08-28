package com.servlet_project.login;

import com.servlet_project.dbmanager.DBManager;

/*
 * A class for working with Registration page and handling 
 *      all the operations unbeknown to user
 */

public class RegistrationDao {  
public boolean insert(String name,String pass){  
        DBManager dbm = DBManager.getInstance();
        return dbm.insertUser(name, pass);
    }
}  
