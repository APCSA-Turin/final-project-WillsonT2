package com.example;
import  com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CardLoader {
    private final Map<String, Card> loadedCards = new HashMap<>();

    public void loadAll() throws IOException {
        try{
            ObjectMapper MAPPER = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            File directory = new File("JavaAPIProject/src/main/resources/cards");
            if (!directory.exists() || !directory.isDirectory()){
                throw new IOException("Invalid directory");
            }
            File[] files = directory.listFiles();
            if (files == null){return;}
            for (File file : files){
                CardDefinition cardDef = MAPPER.readValue(file, CardDefinition.class);
                loadedCards.put(cardDef.getId(), new Card(cardDef));
            }
        } catch (IOException e){
            throw new IOException("Failed to load cards", e);
        }

    }

    public Card getId(String id){
        return loadedCards.get(id);
    }
}