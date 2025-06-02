package com.example;

import java.awt.image.BufferedImage;

public class Card {
    private String name;
    private String description;
    private int cost;
    private String rarity;
    private String numTargets;
    private String cardType;
    private BufferedImage image;

    public Card(String name, String description, int cost, String rarity, String targets, String cardType, BufferedImage image) {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.rarity = rarity;
        this.numTargets = targets;
        this.cardType = cardType;
//        this.image = image;
    }

    public void playCard(Enemy target){
        if (cardType.equals("attack")){

        }else if (cardType.equals("skill")){

        }else if (cardType.equals("power")){

        }
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getCost() {
        return cost;
    }

    public String getRarity() {
        return rarity;
    }

    public String getNumTargets() {
        return numTargets;
    }

    public BufferedImage getImage() {
        return image;
    }

}
