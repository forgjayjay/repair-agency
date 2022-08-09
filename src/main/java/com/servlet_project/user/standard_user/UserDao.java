package com.servlet_project.user.standard_user;

import com.servlet_project.dbmanager.dbmanager;

public class UserDao {
    dbmanager dbm = dbmanager.getInstance();
    public boolean insertOrder(String name){
        return dbm.insertOrder(name);
    }
    public String showOrders(String name){
        String str = dbm.showOrder(name);

        return str;
    }
}
