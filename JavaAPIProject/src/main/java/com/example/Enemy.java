package com.example;

import java.awt.image.BufferedImage;

public class Enemy {
    private BufferedImage sprite;
    private int health;
    private Card move1;
    private Card move2;
    private Card move3;

    public Enemy(int health, Card move1, Card move2, Card move3) {
        health = health;
    }

    public int getHealth() {
        return health;
    }

    public void takeDamage(int damage){
        health -= damage;
    }
}
