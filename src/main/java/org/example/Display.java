package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Display extends JPanel implements ActionListener {
    // Game display constants
    private static final int CELL_SIZE = 60;        // Size of each game grid cell in pixels
    private static final int GRID_GAP = 5;          // Gap between cells in pixels
    
    // Color scheme configuration
    private static final Color GREEN = new Color(106, 170, 100);   // Correct letter, correct position
    private static final Color YELLOW = new Color(201, 180, 88);   // Correct letter, wrong position
    private static final Color GRAY = new Color(120, 124, 126);    // Letter not in word
    
    // Core game components
    private JButton exitButton;                     // Application exit control
    private JButton newGameButton;                  // Game reset trigger
    private JTextField inputField;                  // User input handler
    private WordleGame game;                        // Game logic controller

    // Game state tracking
    private GameHistory gameHistory;                // Persistent game records
    private JButton viewHistoryButton;              // History display trigger
    private char[][] letters = new char[6][5];      // Current game grid state
    private Color[][] cellColors = new Color[6][5]; // Visual feedback

    public Display() {
        // Set up the panel
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        
        // Create the game
        try {
            game = new WordleGame();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // In constructor, add:
        gameHistory = new GameHistory();
        
        // Create the bottom panel with buttons
        createControlPanel();
        
        // Set the size of the game window
        setPreferredSize(new Dimension(400, 600));
    }

    private void createControlPanel() {
        // Create the panel for buttons and input
        JPanel controlPanel = new JPanel();
        controlPanel.setBackground(Color.WHITE);
        
        // Create text field for entering words
        inputField = new JTextField(10);
        inputField.addActionListener(this);
        inputField.setFont(new Font("Arial", Font.PLAIN, 16));
        
        // Create buttons
        newGameButton = new JButton("New Game");
        newGameButton.addActionListener(this);
        
        exitButton = new JButton("Exit");
        exitButton.addActionListener(this);

        // In createControlPanel method, add:
        viewHistoryButton = new JButton("View History");
        viewHistoryButton.addActionListener(this);
        controlPanel.add(viewHistoryButton);
        
        // Add components to control panel
        controlPanel.add(inputField);
        controlPanel.add(newGameButton);
        controlPanel.add(exitButton);
        
        // Add control panel to the main panel
        add(controlPanel, BorderLayout.SOUTH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Draw the title
        g.setFont(new Font("Arial", Font.BOLD, 36));
        g.setColor(Color.BLACK);
        String title = "WORDLE";
        int titleWidth = g.getFontMetrics().stringWidth(title);
        g.drawString(title, (getWidth() - titleWidth) / 2, 40);
        
        // Calculate where to start drawing the grid
        int startX = (getWidth() - (5 * CELL_SIZE + 4 * GRID_GAP)) / 2;
        int startY = 80;
        
        // Draw the grid
        g.setFont(new Font("Arial", Font.BOLD, 32));
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 5; col++) {
                // Calculate position for each cell
                int x = startX + col * (CELL_SIZE + GRID_GAP);
                int y = startY + row * (CELL_SIZE + GRID_GAP);
                
                // Fill cell with color
                if (cellColors[row][col] != null) {
                    g.setColor(cellColors[row][col]);
                } else {
                    g.setColor(Color.WHITE);
                }
                g.fillRect(x, y, CELL_SIZE, CELL_SIZE);
                
                // Draw cell border
                g.setColor(Color.GRAY);
                g.drawRect(x, y, CELL_SIZE, CELL_SIZE);
                
                // Draw letter if it exists
                if (letters[row][col] != '\0') {
                    if (cellColors[row][col] != null) {
                        g.setColor(Color.WHITE);
                    } else {
                        g.setColor(Color.BLACK);
                    }
                    String letter = String.valueOf(letters[row][col]);
                    int letterWidth = CELL_SIZE / 2;
                    int letterHeight = CELL_SIZE / 2;
                    g.drawString(letter, 
                               x + (CELL_SIZE - letterWidth) / 2, 
                               y + (CELL_SIZE + letterHeight) / 2);
                }
            }
        }
        
        // Draw game status message
        if (game.isGameOver()) {
            g.setFont(new Font("Arial", Font.BOLD, 16));
            g.setColor(Color.BLACK);
            String message;
            if (game.isGameWon()) {
                message = "Congratulations! You won!";
            } else {
                message = "Game Over! The word was: " + game.getCurrentWord();
            }
            int messageWidth = g.getFontMetrics().stringWidth(message);
            g.drawString(message, (getWidth() - messageWidth) / 2, getHeight() - 70);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle button clicks
        if (e.getSource() == exitButton) {
            System.exit(0);
        } 
        // Handle new game button
        else if (e.getSource() == newGameButton) {
            game.newGame();
            inputField.setEnabled(true);
            inputField.setText("");
            letters = new char[6][5];
            cellColors = new Color[6][5];
            repaint();
        }
        // In actionPerformed method, add:
        else if (e.getSource() == viewHistoryButton) {
            showGameHistory();
        }
        // Handle word input
        else if (e.getSource() == inputField && !game.isGameOver()) {
            handleGuess();
        }
        inputField.requestFocus();
    }
    
    private void showGameHistory() {
        ArrayList<GameRecord> history = gameHistory.getHistory();
        if (history.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No games played yet!", 
                "Game History", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Create the statistics section
        GameStats stats = gameHistory.getStatistics();
        String statsText = stats.toString() + "\n\n" + "Game History:\n" + "-".repeat(50) + "\n";

        // Add the history records
        String historyText = "";
        for (GameRecord record : history) {
            historyText = historyText + record.toString() + "\n";
        }

        JTextArea textArea = new JTextArea(statsText + historyText);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(500, 300));

        JOptionPane.showMessageDialog(this, scrollPane, 
            "Game History", JOptionPane.PLAIN_MESSAGE);
    }
    
    private void handleGuess() {
        String guess = inputField.getText().trim().toUpperCase();
        if (guess.length() == 5 && game.isValidWord(guess)) {
            ArrayList<String> result = game.checkGuess(guess);
            if (result != null) {
                updateGrid(guess, result);
                inputField.setText("");
                if (game.isGameOver()) {
                    inputField.setEnabled(false);
                    GameRecord record = new GameRecord(
                        game.getCurrentWord(),
                        game.getGuesses(),
                        game.isGameWon(),
                        game.getAttempts()
                    );
                    gameHistory.addGame(record);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this,
                "Please enter a valid 5-letter word!",
                "Invalid Input",
                JOptionPane.WARNING_MESSAGE);
        }
    }

    private void updateGrid(String guess, ArrayList<String> results) {
        int row = game.getAttempts() - 1;
        
        // Add letters to grid
        for (int i = 0; i < 5; i++) {
            letters[row][i] = guess.charAt(i);
            switch (results.get(i)) {
                case "GREEN":
                    cellColors[row][i] = GREEN;
                    break;
                case "YELLOW":
                    cellColors[row][i] = YELLOW;
                    break;
                case "GRAY":
                    cellColors[row][i] = GRAY;
                    break;
            }
        }
        repaint();
    }
}