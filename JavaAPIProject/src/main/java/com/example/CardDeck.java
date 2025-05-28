package com.example;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

public class CardDeck {
    private HashMap<Card, Integer> fullDeck;

    public CardDeck(){
        HashMap<String, Card> tempCards = CardLoader.getAllCards();
        Collection<Card> values = tempCards.values();
        for (Card value : values){
            if (value.getTags().contains("basic")){
                fullDeck.put(value, fullDeck.getOrDefault(value, 0) + 1);
                if (value.getTags().contains("strike") || value.getTags().contains("defend")){
                    fullDeck.put(value, fullDeck.getOrDefault(value, 0) + 1);
                    fullDeck.put(value, fullDeck.getOrDefault(value, 0) + 1);
                    fullDeck.put(value, fullDeck.getOrDefault(value, 0) + 1);
                }
            }
        }
    }

    public void removeCard(Card removeCard){
        if (fullDeck.get(removeCard) == 1){
            fullDeck.remove(removeCard);
        }else {
            fullDeck.put(removeCard, fullDeck.get(removeCard) - 1);
        }
    }

    public void addCard(Card addCard){
        fullDeck.put(addCard, fullDeck.getOrDefault(addCard, 0) + 1);
    }

    public void upgradeCard(Card upgradeCard){
        Card upgradedCard = new Card(upgradeCard.getCardDefinition());
        upgradedCard.upgradeCard();
        fullDeck.put(upgradedCard, fullDeck.getOrDefault(upgradedCard, 0) + 1);
        removeCard(upgradeCard);
    }
}
