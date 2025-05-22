package com.example;

import java.io.IOException;

public class Simulation {
    public static void main(String[] args) throws IOException {
            // Load cards
            CardLoader loader = new CardLoader();
            loader.loadAll();

            // Grab your two cards
            Card strike = loader.getId("strike");
            Card defend = loader.getId("defend");

            // Create entities
            Entity player = new Entity("Player", 50);
            Entity goblin = new Entity("Goblin", 30);

            // Starting stats
            System.out.println("=== Simulation Start ===");
            System.out.println(player.getName() + " → HP: " + player.getHp() + ", BLOCK: " + player.getBlock());
            System.out.println(goblin.getName() + " → HP: " + goblin.getHp() + ", BLOCK: " + goblin.getBlock());

            // 1) Strike: self = player, target = goblin
            System.out.println("\nPlayer plays: " + strike.getName());
            strike.play(player, goblin);
            System.out.println("After Strike → " +
                    goblin.getName() + " HP: " + goblin.getHp() + ", BLOCK: " + goblin.getBlock());

            // 2) Defend: self = player, target = player (you’re blocking yourself)
            System.out.println("\nPlayer plays: " + defend.getName());
            defend.play(player, player);
            System.out.println("After Defend → " +
                    player.getName() + " HP: " + player.getHp() + ", BLOCK: " + player.getBlock());

            // Final stats
            System.out.println("\n=== Final State ===");
            System.out.println(player.getName() + " → HP: " + player.getHp() + ", BLOCK: " + player.getBlock());
            System.out.println(goblin.getName() + " → HP: " + goblin.getHp() + ", BLOCK: " + goblin.getBlock());


    }
}