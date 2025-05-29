package com.example;

import java.util.Collection;
import java.util.HashMap;

public class CardDeck {
    private HashMap<String, Integer> fullDeck;

    public CardDeck(){
        HashMap<String, Card> tempCards = CardLoader.getAllCards();
        Collection<Card> values = tempCards.values();
        fullDeck = new HashMap<>();
        for (Card value : values){
            if (value.getTags().contains("basic")){
                int bonus = (value.getTags().contains("strike")||value.getTags().contains("defend")) ? 3 : 0;
                fullDeck.put(value.getId(), fullDeck.getOrDefault(value.getId(),0) + 1 + bonus);
            }
        }
    }

    public void removeCard(String cardId){
        if (fullDeck.get(cardId) == null){
            return;
        }else if (fullDeck.get(cardId) == 1){
            fullDeck.remove(cardId);
        }else {
            fullDeck.put(cardId, fullDeck.get(cardId) - 1);
        }
    }

    public void addCard(Card addCard){
        fullDeck.put(addCard.getId(), fullDeck.getOrDefault(addCard.getId(), 0) + 1);
    }


}
