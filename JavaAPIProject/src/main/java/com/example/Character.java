package com.example;

public class Character{
    private String characterName;
    private DeckCollection characterCollection;

    public Character(String characterName, DeckCollection characterCollection){
        this.characterName = characterName;
        this.characterCollection= characterCollection;
    }
}
