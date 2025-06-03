package com.example;

import java.util.ArrayList;

public class Player {
    private Deck deck;
    private Deck drawPile;
    private Deck discardPile;
    private Deck myHand;
    private int maxHealth;
    private int currentHealth;
    private int maxSpirituality;
    private int currentSpirituality;

    public Player() {
        deck = new Deck();
        drawPile = new Deck();
        discardPile = new Deck();
        myHand = new Deck();
        maxHealth = 100;
        currentHealth = maxHealth;
        maxSpirituality = 3;
        currentSpirituality = maxSpirituality;
    }

    public void addCard(Card card) {
        deck.addCard(card);
    }

    public void playCard(String playedCard, Enemy target) {
        Card card = deck.getCard(playedCard);
        if (card.getCost() <= currentHealth) {
            if (card instanceof AttackCard){
                target.takeDamage(((AttackCard) card).getDamage());
            }
            if (card.getNumTargets().equals("all")){
                return;
            }
        }else {
            System.out.println("Not enough spirituality");
        }
     }

     public void takeDamage(int damage) {
        currentHealth -= damage;
     }

     public void heal(int health) {
        int healedHealth = currentHealth + health;
        if (healedHealth > maxHealth) {
            currentHealth = maxHealth;
        }else {
            currentHealth = healedHealth;
        }
     }

     public void resetSpirituality() {
        currentSpirituality = maxSpirituality;
     }

     public void incrementSpirituality() {
        currentSpirituality++;
     }
}
