package com.forg.dbmanager;

public class Order {
    private int id;
    private Double cost;
    private boolean review;
    private boolean completed;
    public Order(int id, Double cost, boolean review, boolean completed){
        this.id = id;
        this.cost = cost;
        this.review = review;
        this.completed = completed;
    }
    public boolean getCompleted() {
        return completed;
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
