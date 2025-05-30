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
public class GameRunner
{

    public static void main(String[] args) throws IOException {
        CardLoader.loadAll();
        JFrame frame = new JFrame();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        // CardLayout used to switch between different screens
        JPanel contentPane = new JPanel(new CardLayout());

        JPanel menuScreen = new JPanel();
        menuScreen.setLayout(new BoxLayout(menuScreen, BoxLayout.Y_AXIS));
        JPanel startGameScreen = new JPanel();
        JPanel cardCollectionScreen = new JPanel();
        JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        cardCollectionScreen.setLayout(new BorderLayout());
        JPanel cardsCollectionDisplay = new JPanel(new GridLayout(0, 5));
        cardsCollectionDisplay.add(GUIUtil.createCardDisplay(CardLoader.getCard("strike")));
        cardsCollectionDisplay.add(GUIUtil.createCardDisplay(CardLoader.getCard("defend")));

        JButton switchToGame = new JButton("Play");
        switchToGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton switchToCardCollection = new JButton("Cards");
        switchToCardCollection.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton backToMenu = new JButton("Back");
        JButton exitGame = new JButton("Exit");
        exitGame.setAlignmentX(Component.CENTER_ALIGNMENT);

        // change switchToGame JButton to transparent
//        switchToGame.setOpaque(false);
//        switchToGame.setContentAreaFilled(false);
//        switchToGame.setBorderPainted(false);
//        switchToGame.setFocusPainted(false);

        contentPane.add(menuScreen, "Menu");
        contentPane.add(startGameScreen, "StartGame");
        contentPane.add(cardCollectionScreen, "Cards");

        // to add custom images, use imageicon with file and pass it to a JLabel
        ImageIcon imageIcon = new ImageIcon("JavaAPIProject/src/main/resources/dog.jpg");
        Image scaledImage = imageIcon.getImage();
        scaledImage.getScaledInstance(1920, 1080, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(scaledImage);
        JLabel background = new JLabel(imageIcon);
        startGameScreen.add(background);

        CardLayout cardLayout = (CardLayout) contentPane.getLayout();

        switchToGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(contentPane, "StartGame");
            }
        });
        backToMenu.addActionListener(new ActionListener() {
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
        menuScreen.add(Box.createVerticalGlue()); // verticalGlue creates spacing effect between buttons
        menuScreen.add(switchToGame);
        menuScreen.add(Box.createVerticalGlue());
        menuScreen.add(switchToCardCollection);
        menuScreen.add(Box.createVerticalGlue());
        menuScreen.add(exitGame);
        menuScreen.add(Box.createVerticalGlue());

        backButtonPanel.add(backToMenu);
        cardCollectionScreen.add(cardsCollectionDisplay);
        cardCollectionScreen.add(backButtonPanel, BorderLayout.SOUTH);
        frame.add(contentPane);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
