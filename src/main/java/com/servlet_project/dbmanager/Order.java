package com.servlet_project.dbmanager;

public class Order {
    private int id;
    private Double cost;
    boolean review;
    public Order(int id, Double cost, boolean review){
        this.id = id;
        this.cost = cost;
        this.review = review;
    }
    public boolean isReviewed(){
        return review;
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
