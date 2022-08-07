package com.servlet_project.user.admin;

import com.servlet_project.dbmanager.dbmanager;

public class AdminDao {
    dbmanager dbm = dbmanager.getInstance();
    public boolean insertManager(String name,String pass){
        return dbm.insertManager(name, pass);
    }
    public boolean insertManager(String name){
        return dbm.insertManager(name, "");
    }
    public boolean insertCraftsman(String name, String login){
        return dbm.insertCraftsman(login, "", name);
    }
    public boolean insertCraftsman(String name, String login, String pass){
        return dbm.insertCraftsman(login, pass, name);
    }
}
