package org.example;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.*;

public class WordleGame {
    private static final String API_URL = "https://api.datamuse.com/words?sp=?????&max=5000";
    private static final int MAX_ATTEMPTS = 6;
    // Fallback words in case API fails
    private static final ArrayList<String> FALLBACK_WORDS = new ArrayList<>(Arrays.asList(
        "HAPPY", "SMILE", "GREAT", "PLANT", "DREAM",
        "HOUSE", "BRAIN", "CLOUD", "LIGHT", "WATER"
    ));
    
    private final ArrayList<String> validWords;
    private String currentWord;
    private int attempts;
    private boolean gameWon;
    private ArrayList<String> guesses = new ArrayList<>();

    public WordleGame() {
        validWords = new ArrayList<>();
        try {
            loadWords();
        } catch (Exception e) {
            validWords.addAll(FALLBACK_WORDS);
        }
        newGame();
    }

    public void newGame() {
        if (validWords.isEmpty()) {
            validWords.addAll(FALLBACK_WORDS);
        }
        currentWord = validWords.get((int) (Math.random() * validWords.size())).toUpperCase();
        attempts = 0;
        gameWon = false;
    }

    // Word loading from API
    private void loadWords() throws Exception {
        String response = API.getData(API_URL);
        JSONArray jsonArray = new JSONArray(response);
        
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject wordObj = jsonArray.getJSONObject(i);
            String word = wordObj.getString("word");
            // Only add words that meet the Wordle format (5 letters, all alphabetic)
            if (isValidWordFormat(word)) {
                validWords.add(word.toUpperCase());
            }
        }
    }

    private boolean isValidWordFormat(String word) {
        if (word == null || word.length() != 5) return false;

        for (char c : word.toCharArray()) {
            if (!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }

    public boolean isValidWord(String word) {
        if (word == null) return false;
        return validWords.contains(word.toUpperCase());
    }

    // Core game logic for checking a guess
    public ArrayList<String> checkGuess(String guess) {
        if (guess == null || guess.length() != 5) {
            return null;
        }

        guess = guess.toUpperCase();
        ArrayList<String> result = new ArrayList<>(5);
        for (int i = 0; i < 5; i++) {
            result.add(null);
        }
        attempts++;

        char[] guessChars = guess.toCharArray();
        char[] wordChars = currentWord.toCharArray();
        boolean[] usedInWord = new boolean[5];  // Tracks which letters were matched
        
        // First pass: Check for exact matches (green)
        // This ensures priority for exact matches over partial matches
        for (int i = 0; i < 5; i++) {
            if (guessChars[i] == wordChars[i]) {
                result.set(i, "GREEN");
                usedInWord[i] = true;  // Mark this position as used
            }
        }

        // Second pass: Check for letters in wrong position (yellow)
        // Only check positions that weren't exact matches
        for (int i = 0; i < 5; i++) {
            if (result.get(i) != null) continue;  // Skip already matched positions
            
            boolean foundMatch = false;
            for (int j = 0; j < 5; j++) {
                // Look for unused matching letters
                if (!usedInWord[j] && guessChars[i] == wordChars[j]) {
                    result.set(i, "YELLOW");
                    usedInWord[j] = true;
                    foundMatch = true;
                    break;
                }
            }
            if (!foundMatch) {
                result.set(i, "GRAY");  // Letter not found in word
            }
        }

        gameWon = guess.equals(currentWord);
        guesses.add(guess);
        return result;
    }

    // Getters
    public boolean isGameOver() {
        return gameWon || attempts >= MAX_ATTEMPTS;
    }
    public boolean isGameWon() {
        return gameWon;
    }
    public String getCurrentWord() {
        return currentWord;
    }
    public int getAttempts() {
        return attempts;
    }
    public int getRemainingAttempts() {
        return MAX_ATTEMPTS - attempts;
    }

    public ArrayList<String> getValidWords() { 
        return new ArrayList<>(validWords);  // Return a copy to prevent modification
    }

    public ArrayList<String> getGuesses() {
        return new ArrayList<>(guesses);  // Return a copy to prevent modification
    }
}