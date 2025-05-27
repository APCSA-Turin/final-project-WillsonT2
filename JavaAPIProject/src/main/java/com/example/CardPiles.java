package com.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class CardPiles {
    private ArrayList<Card> currentHand;
    private ArrayList<Card> drawPile;
    private ArrayList<Card> discardedPile;
    private ArrayList<Card> fullDeck;
    private ArrayList<Card> fullCollection;


    public CardPiles() throws IOException {
        CardLoader loader = new CardLoader();
        loader.loadAll();
        for (String key: loader.getLoadedCards().keySet()){
            fullCollection.add(loader.getLoadedCards().get(key));
        }
    }

    public void initalizeStartingDeck(){
        for (Card card : fullCollection) {
            if (card.getCardDefinition().getTags().contains("basic")) {
                if (card.getCardDefinition().getId().equals("strike") || card.getCardDefinition().getId().equals("defend")) {
                    fullDeck.add(card);
                    fullDeck.add(card);
                    fullDeck.add(card);
                }
                fullDeck.add(card);
            }
        }
    }

    public void initalizeDrawPile(){
        drawPile = new ArrayList<>(fullCollection);
        Collections.shuffle(drawPile);
    }

    public void drawCard(){

    }
}
