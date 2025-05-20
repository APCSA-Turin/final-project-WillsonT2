package com.example;

import java.util.ArrayList;

public class Character1 {
    private String character1Name = "Seer";
    private DeckCollection character1Collection = new DeckCollection(new ArrayList<>(), new ArrayList<>());
    private ArrayList<Artifacts> artifacts1Collection;

    public String getCharacter1Name(){return character1Name;}
    public DeckCollection getCharacter1Collection(){return character1Collection;}
    public ArrayList<Artifacts> getArtifacts1Collection(){return artifacts1Collection;}


}
