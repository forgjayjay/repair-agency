package com.servlet_project.dbmanager;

public class Order {
    private int id;
    private Double cost;
    public Order(int id, Double cost){
        this.id = id;
        this.cost = cost;

    }

    public Double getCost() {
        return cost;
    }
    public int getId() {
        return id;
    }
    @Override
    public String toString() {
        return Integer.toString(id);
    }
}
