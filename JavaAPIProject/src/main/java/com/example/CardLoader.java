package com.example;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CardLoader {

    public static Map<String, CardDefinition> loadAll() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, CardDefinition> loadedCards = new HashMap<>();
        File directory = new File("JavaAPIProject/src/main/resources/cards");
        File[] files = directory.listFiles();
        for (File file : files){
            CardDefinition cardDef = mapper.readValue(file, CardDefinition.class);
            loadedCards.put(cardDef.getId(), cardDef);
        }
        return loadedCards;
    }
}
