package com.servlet_project.user.admin;

import com.servlet_project.dbmanager.dbmanager;

public class AdminDao {
    
    public boolean insertManager(String name,String pass){
        dbmanager dbm = dbmanager.getInstance();
        return dbm.insertManager(name, pass);
    }
    public boolean insertManager(String name){
        dbmanager dbm = dbmanager.getInstance();
        return dbm.insertManager(name, "");
    }
}
