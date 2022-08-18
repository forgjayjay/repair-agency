package com.servlet_project.user.craftsman;

import java.util.ArrayList;

import com.servlet_project.dbmanager.dbmanager;

public class CraftsmanDao {
    dbmanager dbm = dbmanager.getInstance();
    public boolean insertOrder(String name){
        return dbm.insertOrder(name);
    }
    public ArrayList<String> showOrders(String name){
        ArrayList<String> arrayString = dbm.showCraftsmanOrder(name);

        return arrayString;
    }
    public boolean updateOrder(String status, int id){
        return dbm.updateOrderStatus(status, id);
    }
}
