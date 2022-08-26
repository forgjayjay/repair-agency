package com.servlet_project.user.craftsman;

import java.util.HashMap;

import com.servlet_project.dbmanager.Order;
import com.servlet_project.dbmanager.dbmanager;

public class CraftsmanDao {
    dbmanager dbm = dbmanager.getInstance();
    public boolean insertOrder(String name){
        return dbm.insertOrder(name);
    }
    public HashMap<Order, String> showOrders(String name){
        return dbm.showCraftsmanOrder(name);
    }
    public boolean updateOrder(String status, int id, String name){
        return dbm.updateOrderStatus(status, id, name);
    }
}
