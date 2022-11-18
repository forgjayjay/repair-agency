package com.forg.user.standard_user;

import java.util.HashMap;

import com.forg.dbmanager.DBManager;
import com.forg.dbmanager.Order;

public class UserDao {
    DBManager dbm = DBManager.getInstance();
    HashMap<Order, String> orderMap = new HashMap<>();
    public boolean insertOrder(String name){
        return dbm.insertOrder(name);
    }
    public HashMap<Order, String> showOrders(String name){
        orderMap = dbm.showOrder(name, "all");

        return orderMap;
    }
    public HashMap<Order, String> showUnpaidOrders(String name){
        orderMap = dbm.showOrder(name, "unpaid");

        return orderMap;
    }

    public boolean updateOrderPaymentStatus(int id, double cost){
        return dbm.updateOrderPaymentStatus(id, cost);
    }
    public int getCraftsmanId(int orderID){
        return dbm.getCraftsmanId(orderID);
    }
    public boolean orderReview(int id, int review){
        return dbm.orderReview(id, review);
    }
}
