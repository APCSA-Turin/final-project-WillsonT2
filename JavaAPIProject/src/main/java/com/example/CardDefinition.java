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

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getType(){
        return type;
    }

    public void setType(String type){
        this.type = type;
    }

    public int getCost(){
        return cost;
    }

    public void setCost(int cost){
        this.cost = cost;
    }

    public String getRarity(){
        return rarity;
    }

    public void setRarity(String rarity){
        this.rarity = rarity;
    }

    public String getTarget(){
        return target;
    }

    public void setTarget(String target){
        this.target = target;
    }

    public String getImage(){
        return image;
    }

    public void setImage(String image){
        this.image = image;
    }

    public List<String> getTags(){
        return tags;
    }

    public void setTags(List<String> tags){
        this.tags = tags;
    }

    public List<EffectDef> getEffects(){
        return effects;
    }

    public void setEffects(List<EffectDef> effects){
        this.effects = effects;
    }

    public UpgradeDef getUpgrade(){
        return upgrade;
    }

    public void setUpgrade(UpgradeDef upgrade){
        this.upgrade = upgrade;
    }
}
