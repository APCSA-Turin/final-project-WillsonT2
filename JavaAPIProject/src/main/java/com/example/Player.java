package com.example;

public class Player {
    private int maxSpirituality = 3;
    private int maxCards = 8;
    private int drawEachTurn = 4;
    private Deck myDeck;

    public Player(Deck myDeck){
        this.myDeck = myDeck;
    }
}
