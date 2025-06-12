package org.example;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

public class GameHistory {
    private static final String HISTORY_FILE = "wordle_history.txt";
    private ArrayList<GameRecord> gameRecords;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public GameHistory() {
        gameRecords = new ArrayList<>();
        loadHistory();
    }

    public void addGame(GameRecord record) {
        gameRecords.add(record);
        saveHistory();
    }

    public ArrayList<GameRecord> getHistory() {
        return new ArrayList<>(gameRecords);
    }

    private void loadHistory() {
        File file = new File(HISTORY_FILE);
        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 5) {
                    LocalDateTime timestamp = LocalDateTime.parse(parts[0], formatter);
                    String targetWord = parts[1];
                    boolean won = Boolean.parseBoolean(parts[2]);
                    int attempts = Integer.parseInt(parts[3]);
                    ArrayList<String> guesses = new ArrayList<>(Arrays.asList(parts[4].split(";")));
                    
                    gameRecords.add(new GameRecord(timestamp, targetWord, guesses, won, attempts));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveHistory() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(HISTORY_FILE))) {
            for (GameRecord record : gameRecords) {
                writer.write(record.toFileString());
                writer.newLine();  // adds a line separator
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public GameStats getStatistics() {
        if (gameRecords.isEmpty()) {
            return new GameStats(0, 0, 0.0);
        }

        int totalGames = gameRecords.size();
        int wins = 0;
        int totalGuessesForWins = 0;

        for (GameRecord record : gameRecords) {
            if (record.isWon()) {
                wins++;
                totalGuessesForWins += record.getAttempts();
            }
        }

        double avgGuesses = wins > 0 ? (double) totalGuessesForWins / wins : 0.0;
        return new GameStats(totalGames, wins, avgGuesses);
    }
}