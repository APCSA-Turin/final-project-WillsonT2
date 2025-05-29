package com.example;

public class EffectDef {
    private String conditionType;
    private int value;
    private ConditionDef condition;


    public EffectDef() { }

    public String getType() {
        return conditionType;
    }

    public void setType(String type) {
        this.conditionType = type;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public ConditionDef getCondition() {
        return condition;
    }

    public void setCondition(ConditionDef condition) {
        this.condition = condition;
    }
}
