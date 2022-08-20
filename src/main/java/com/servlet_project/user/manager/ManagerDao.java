package com.servlet_project.user.manager;

import java.util.ArrayList;

import com.servlet_project.dbmanager.dbmanager;

public class ManagerDao {
    dbmanager dbm = dbmanager.getInstance();
    ArrayList<String> arrayString = new ArrayList<>();
    public boolean insertOrder(String name){
        return dbm.insertOrder(name);
    }
    public ArrayList<String> showOrders(String name){
        arrayString = dbm.showOrder(name, "all");

        return arrayString;
    }
    public ArrayList<String> showAllOrdersAsc(){
        arrayString = dbm.showManagerOrders("ASC");

        return arrayString;
    }
    public ArrayList<String> showAllOrdersDesc(){
        arrayString = dbm.showManagerOrders("DESC");

        return arrayString;
    }
}
