package com.example;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class CardLoader {
    private final ObjectMapper mapper = new ObjectMapper();

    public Map<String, CardDefinition> loadAll() throws Exception {
        Map<String, CardDefiniton> defs = new HashMap<>();
        URL folder = getClass().getResource("cards");
    }
}
