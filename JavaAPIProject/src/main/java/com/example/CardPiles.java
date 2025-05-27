package com.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class CardPiles {
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
        for (int i = 0; i < fullCollection.size(); i++){
            if (fullCollection.get(i).getCardDefinition().getTags().contains("Basic")){
                if (fullCollection.get(i).getCardDefinition().getId().equals("strike") || fullCollection.get(i).getCardDefinition().getId().equals("defend")){
                    fullDeck.add(fullCollection.get(i));
                    fullDeck.add(fullCollection.get(i));
                    fullDeck.add(fullCollection.get(i));
                }
                fullDeck.add(fullCollection.get(i));
            }
        }
    }

    public void initalizeDrawPile(){
        drawPile = new ArrayList<>(fullCollection);
        Collections.shuffle(drawPile);
    }


}
