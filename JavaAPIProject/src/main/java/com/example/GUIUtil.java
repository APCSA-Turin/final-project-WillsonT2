package com.example;

import javax.swing.*;

public class GUIUtil {
    public static JPanel createCardDisplay(Card card){
        JPanel cardPanel = new JPanel();
        JTextArea name = new JTextArea(card.getName() + "\n" + card.getDescription());
        cardPanel.add(name);
        return cardPanel;
    }

}
