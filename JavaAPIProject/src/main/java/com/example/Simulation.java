package com.example;

public class Simulation {
    public static void main(String[] args) throws Exception {
        // 1) Load your cards
        CardLoader loader = new CardLoader();
        loader.loadAll();
        Card strike = loader.getId("strike");
        Card defend = loader.getId("defend");

        // 2) Create player & enemy
        Entity player = new Entity("You",   30);
        Entity enemy  = new Entity("Goblin",20);

        // 3) Simulate a couple of turns
        System.out.println("\n--- Turn 1: You attack ---");
        strike.play(player, enemy);
        if (!enemy.isAlive()) {
            System.out.println("Enemy defeated!");
            return;
        }

        System.out.println("\n--- Turn 1: Enemy attacks you for 5 damage ---");
        player.takeDamage(5);
        if (!player.isAlive()) {
            System.out.println("You died!");
            return;
        }

        System.out.println("\n--- Turn 2: You defend ---");
        defend.play(player, player);

        // 4) End‐of‐turn status updates
        player.endTurn();
        enemy.endTurn();

        System.out.printf("%nFinal State → You: HP=%d, BLOCK=%d; Enemy: HP=%d, BLOCK=%d%n",
                player.getHp(), player.getBlock(),
                enemy.getHp(),  enemy.getBlock());
    }
}