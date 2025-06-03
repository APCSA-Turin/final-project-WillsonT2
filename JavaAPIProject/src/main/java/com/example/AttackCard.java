package com.example;

import java.awt.image.BufferedImage;

public class AttackCard extends Card {
    private int damage;
    public AttackCard(String name, String description, int cost, String rarity, String targets, int damage, BufferedImage image) {
        super(name, description, cost, rarity, targets, "attack", image);
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }
}
