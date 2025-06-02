package com.example;

import java.awt.image.BufferedImage;

public class SkillCard extends Card {
    public SkillCard(String name, String description, int cost, String rarity, String targets, BufferedImage image) {
        super(name, description, cost, rarity, targets, "skill", image);
    }

}
