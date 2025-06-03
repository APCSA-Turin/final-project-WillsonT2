package com.example;

public class Main {
    public static void main(String[] args) {
        AttackCard strike = new AttackCard("Strike", "Deal 6 damage", 1, "basic", "1", 6, null);
        Enemy testEnemy = new Enemy(6, null, null, null);
        System.out.println(testEnemy.getHealth());
        Player player = new Player();
        player.addCard(strike);
        player.
    }
}
