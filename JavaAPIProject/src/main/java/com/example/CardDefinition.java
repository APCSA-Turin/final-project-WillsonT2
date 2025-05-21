package com.example;

import java.util.List;

public class CardDefinition {
    private String id;
    private String name;
    private String description;
    private String type;
    private int cost;
    private String rarity;
    private String target;
    private String image;
    private List<String> tags;
    private List<EffectDef> effects;
    private UpgradeDef upgrade;
}
