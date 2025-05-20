package com.example;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Hello world!
 *
 */
public class App
{


    public static void main(String[] args){
        JFrame frame = new JFrame();
        JPanel contentPane = new JPanel(new CardLayout());
        JPanel menuScreen = new JPanel();
        JPanel characterSelectionScreen = new JPanel();
        JButton switchToCharacter = new JButton("Go to Characters");
        JButton switchToMenu = new JButton("Go to menu");
        contentPane.add(menuScreen, "Menu");
        contentPane.add(characterSelectionScreen, "Select Character");
        CardLayout cardLayout = (CardLayout) contentPane.getLayout();
        switchToCharacter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(contentPane, "Select Character");
            }
        });
        switchToMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(contentPane, "Menu");
            }
        });
        characterSelectionScreen.add(switchToMenu);
        menuScreen.add(switchToCharacter);
        frame.add(contentPane);
        frame.setSize(1920, 1080);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
