package org.example;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) throws Exception {
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Wordle Game");
        frame.setSize(400, 600);
        frame.setLocationRelativeTo(null);
        Display display = new Display();
        frame.add(display);
        frame.setVisible(true);
    }
}