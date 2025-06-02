package com.example;

import java.awt.image.BufferedImage;

public class AttackCard extends Card {
    public AttackCard(String name, String description, int cost, String rarity, String targets, BufferedImage image) {
        super(name, description, cost, rarity, targets, "attack", image);
    }

    public void attack(Enemy target) {
        if (getNumTargets().equals("all")){

            return;
        }
    }
}
