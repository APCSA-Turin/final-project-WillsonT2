package com.example;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Hello world!
 *
 */
public class Game
{

    public static void main(String[] args) throws IOException {
        CardLoader.loadAll();
        JFrame frame = new JFrame();
        // CardLayout used to switch between different screens
        JPanel contentPane = new JPanel(new CardLayout());

        JPanel menuScreen = new JPanel();
        menuScreen.setLayout(new BoxLayout(menuScreen, BoxLayout.Y_AXIS));
        JPanel startGameScreen = new JPanel();
        JPanel cardCollectionScreen = new JPanel();
        JPanel cardsCollectionDisplay = new JPanel(new GridLayout(0, 5));
        JPanel leaveCardCollection = new JPanel(new FlowLayout(FlowLayout.LEFT));
        cardCollectionScreen.setLayout(new BorderLayout());
        cardsCollectionDisplay.add(GUIUtil.createCardDisplay(CardLoader.getCard("strike")));
        cardsCollectionDisplay.add(GUIUtil.createCardDisplay(CardLoader.getCard("defend")));

        JButton switchToGame = new JButton("Play");
        switchToGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton switchToCardCollection = new JButton("Cards");
        switchToCardCollection.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton switchToMenu = new JButton("Back");
        leaveCardCollection.add(switchToMenu);
        JButton exitGame = new JButton("Exit");
        exitGame.setAlignmentX(Component.CENTER_ALIGNMENT);

        // change switchToGame JButton to transparent
//        switchToGame.setOpaque(false);
//        switchToGame.setContentAreaFilled(false);
//        switchToGame.setBorderPainted(false);
//        switchToGame.setFocusPainted(false);

        contentPane.add(menuScreen, "Menu");
        contentPane.add(startGameScreen, "Back");
        contentPane.add(cardCollectionScreen, "Cards");

        // to add custom images, use imageicon with file and pass it to a JLabel
        ImageIcon imageIcon = new ImageIcon("src/main/resources/background.jpg");
        JLabel background = new JLabel(imageIcon);
        background.setVisible(true);
        frame.add(background);

        CardLayout cardLayout = (CardLayout) contentPane.getLayout();
        switchToGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(contentPane, "Back");
            }
        });
        switchToMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(contentPane, "Menu");
            }
        });
        switchToCardCollection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(contentPane, "Cards");
            }
        });
        exitGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // adding everything to their panels and frame
        menuScreen.add(Box.createVerticalGlue());
        menuScreen.add(switchToGame);
        menuScreen.add(Box.createVerticalGlue());
        menuScreen.add(switchToCardCollection);
        menuScreen.add(Box.createVerticalGlue());
        menuScreen.add(exitGame);
        menuScreen.add(Box.createVerticalGlue());
        cardCollectionScreen.add(leaveCardCollection, BorderLayout.SOUTH);
        cardCollectionScreen.add(cardsCollectionDisplay);
        frame.add(contentPane);


        frame.setSize(1920, 1080);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
