package com.example;

import java.util.List;

public class UpgradeDef {
    private String description;
    private List<EffectDef> effects;

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public List<EffectDef> getEffects(){
        return  effects;
    }

    public void setEffects(List<EffectDef> effects){
        this.effects = effects;
    }
}

