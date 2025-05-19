package com.example;

import java.util.ArrayList;

public class DeckCollection {
    private ArrayList<Card> collection;
    private final ArrayList<Card> startingDeck;

    public DeckCollection(ArrayList<Card> collection, ArrayList<Card> startingDeck){
        this.collection = collection;
        this.startingDeck = startingDeck;
    }

    public ArrayList<Card> getStartingDeck(){
        return startingDeck;
    }
}
