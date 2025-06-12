package org.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class GameRecord {
    private LocalDateTime timestamp;
    private String targetWord;
    private ArrayList<String> guesses;
    private boolean won;
    private int attempts;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public GameRecord(String targetWord, ArrayList<String> guesses, boolean won, int attempts) {
        this.timestamp = LocalDateTime.now();
        this.targetWord = targetWord;
        this.guesses = new ArrayList<>(guesses);
        this.won = won;
        this.attempts = attempts;
    }

    public GameRecord(LocalDateTime timestamp, String targetWord, ArrayList<String> guesses, boolean won, int attempts) {
        this.timestamp = timestamp;
        this.targetWord = targetWord;
        this.guesses = guesses;
        this.won = won;
        this.attempts = attempts;
    }

    @Override
    public String toString() {
        return timestamp.format(formatter) + " | Word: " + targetWord + " | Won: " + won + " | Attempts: " + attempts + " | Guesses: " + String.join(", ", guesses);
    }

    public String toFileString() {
        return timestamp.format(formatter) + "," + targetWord + "," + won + "," + attempts + "," + String.join(";", guesses);
    }

    public boolean isWon() {
        return won;
    }

    public int getAttempts() {
        return attempts;
    }
}