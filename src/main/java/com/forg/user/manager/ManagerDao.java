package com.forg.user.manager;

import java.util.HashMap;

import com.forg.dbmanager.DBManager;
import com.forg.dbmanager.Order;

public class ManagerDao {
    DBManager dbm = DBManager.getInstance();
    public boolean insertOrder(String name){
        return dbm.insertOrder(name);
    }
    public HashMap<Order, String> showAllOrdersAsc(){
        return dbm.showManagerOrders("ASC");
    }
    public HashMap<Order, String> showAllOrdersDesc(){
        return dbm.showManagerOrders("DESC");
    }
    public boolean updateCraftsman(String login, String name){
        return dbm.insertCraftsman(login, "", name);
    }
    public boolean appointCraftsman(int craftsmanID, int orderID){
        return dbm.appointCraftsman(craftsmanID, orderID);
    }
    public boolean priceOrder(Double cost, int orderID){
        return dbm.priceOrder(cost, orderID);
    }
}
