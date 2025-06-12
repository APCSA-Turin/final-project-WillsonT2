package org.example;

public class GameStats {
    private final int totalGames;
    private final int totalWins;
    private final double averageGuesses;

    public GameStats(int totalGames, int totalWins, double averageGuesses) {
        this.totalGames = totalGames;
        this.totalWins = totalWins;
        this.averageGuesses = averageGuesses;
    }

    @Override
    public String toString() {
        double winRate;
        if (totalGames > 0) {
            winRate = totalWins * 100.0 / totalGames;
        } else {
            winRate = 0.0;
        }
        return "Statistics:\n" +
           "Total Games: " + totalGames + "\n" +
           "Wins: " + totalWins + "\n" +
           "Win Rate: " + (Math.round(winRate * 10.0) / 10.0) + "%\n" +
           "Average Guesses to Win: " + (Math.round(averageGuesses * 10.0) / 10.0);
}
}