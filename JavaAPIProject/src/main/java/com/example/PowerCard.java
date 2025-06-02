package com.example;

import java.awt.image.BufferedImage;

public class PowerCard extends Card {
    public PowerCard(String name, String description, int cost, String rarity, String targets, BufferedImage image) {
        super(name, description, cost, rarity, targets, "power", image);
    }
}
