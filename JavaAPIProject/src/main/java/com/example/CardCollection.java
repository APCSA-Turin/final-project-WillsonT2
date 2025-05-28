package com.example;

import java.io.IOException;
import java.util.HashMap;

public class CardCollection {
    private HashMap<String, Card> fullCollection;


    public CardCollection(){
        fullCollection = CardLoader.getAllCards();
    }

    public Card getCard(String id){
        return fullCollection.get(id);
    }

}

