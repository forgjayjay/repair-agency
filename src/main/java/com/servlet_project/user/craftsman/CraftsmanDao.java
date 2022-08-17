package com.servlet_project.user.craftsman;

import com.servlet_project.dbmanager.dbmanager;

public class CraftsmanDao {
    dbmanager dbm = dbmanager.getInstance();
    public boolean insertOrder(String name){
        return dbm.insertOrder(name);
    }
    public String showOrders(String name){
        String str = dbm.showCraftsmanOrder(name);

        return str;
    }
    public boolean updateOrder(String status, int id){
        return dbm.updateOrderStatus(status, id);
    }
}
