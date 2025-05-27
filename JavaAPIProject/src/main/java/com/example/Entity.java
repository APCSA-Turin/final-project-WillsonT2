package com.example;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents any actor in the game (player, enemy) with HP, Block, and statuses.
 */
public class Entity {
    private final String name;
    private int hp;
    private int block;
    private final Map<String, Integer> statusCounters;

    /**
     * @param name Display name of the entity
     * @param hp   Starting hit points
     */
    public Entity(String name, int hp) {
        this.name = name;
        this.hp = hp;
        this.block = 0;
        this.statusCounters = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getBlock() {
        return block;
    }

    public boolean isAlive() {
        return hp > 0;
    }

    /**
     * Apply damage to this entity, factoring in current block.
     * Prints the result to the console.
     * @param amount Raw incoming damage
     */
    public void takeDamage(int amount) {
        int mitigated = Math.max(amount - block, 0);
        int blockedAmt = amount - mitigated;
        block = Math.max(block - amount, 0);
        hp -= mitigated;
        System.out.println(name + " takes " + mitigated +
                " damage (blocked " + blockedAmt + ") → HP=" + hp);
    }

    /**
     * Gain block (temporary damage buffer).
     * @param amount Amount of block to add
     */
    public void addBlock(int amount) {
        block += amount;
        System.out.println(name + " gains " + amount +
                " block → BLOCK=" + block);
    }

    /**
     * Check if the entity has a status (e.g. "Vulnerable").
     */
    public boolean hasStatus(String status) {
        return statusCounters.getOrDefault(status, 0) > 0;
    }

    /**
     * Add (or refresh) a status effect for a given number of turns.
     */
    public void addStatus(String status, int turns) {
        statusCounters.put(status, statusCounters.getOrDefault(status, 0) + turns);
    }

    /**
     * Call at end of turn to decrement and remove expired statuses.
     */
    public void endTurn() {
        statusCounters.replaceAll((s, count) -> Math.max(count - 1, 0));
        statusCounters.entrySet().removeIf(e -> e.getValue() == 0);
    }
}
