package com.example;

import java.io.IOException;
import java.util.ArrayList;

// used to represent your total deck for a current run
public class Deck {
    private ArrayList<Card> deck;

    // run before the game starts to get the starter deck
    public Deck() throws IOException {
        deck = new ArrayList<>();
        CardLoader loader = new CardLoader();
        loader.loadAll();
        int counter = 0;
        while (counter < 4){
            deck.add(loader.getId("strike"));
            deck.add(loader.getId("defend"));
            counter++;
        }
    }

    public ArrayList<Card> getDeck(){
        return deck;
    }

    public void addCard(Card card){
        deck.add(card);
    }

    public void removeCard(Card card){
        deck.remove(card);
    }

    // used to upgrade card permanently in deck
    public void upgradeCard(Card card){
        card.upgradeCard();
    }
}
