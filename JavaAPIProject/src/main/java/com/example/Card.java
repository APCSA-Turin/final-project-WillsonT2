package com.example;

public class Card {
    private int cost;
    private String type;
    private String name;

    public Card(String name, int cost, String type){
        this.cost = cost;
        this.type = type;
        this.name = name;
    }

    public int getCost(){
        return cost;
    }

    public void setCost(int newCost){
        cost = newCost;
    }

    public String getType(){
        return type;
    }

    public String getName(){
        return name;
    }
}
