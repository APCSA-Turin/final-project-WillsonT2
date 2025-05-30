package com.example;

import javax.swing.*;

public class GUIUtil {
    public static JPanel createCardDisplay(Card card){
        JPanel cardPanel = new JPanel();
        JTextArea name = new JTextArea(card.getName() + "\n" + card.getDescription());
        cardPanel.add(name);
        ImageIcon spiritImage = new ImageIcon("JavaAPIProject/src/main/resources/spirit.png");
        JLabel spirtImageHolder = new JLabel(spiritImage);
        ImageIcon image = new ImageIcon("JavaAPIProject/src/main/resources/" + card.getId() + ".jpg");
        JLabel imageHolder = new JLabel(image);
        cardPanel.add(imageHolder);
        cardPanel.add(spirtImageHolder);
        return cardPanel;
    }

}
