package com.example;

import java.util.ArrayList;

public class Deck {
    private ArrayList<Card> deck;

    public Deck() {
        deck = new ArrayList<>();
    }

    public void addCard(Card card) {
        deck.add(card);
    }

    public void removeCard(Card card) {
        deck.remove(card);
    }

    public Card getCard(String name) {
        for (int i = 0; i < deck.size(); i++) {
            if (deck.get(i).getName().equals(name)) {
                return deck.get(i);
            }
        }
        return null;
    }

    public String displayCards() {
        for (int i = 0; i < deck.size(); i++) {
            return deck.get(i).getName();
        }
        return null;
    }
}
