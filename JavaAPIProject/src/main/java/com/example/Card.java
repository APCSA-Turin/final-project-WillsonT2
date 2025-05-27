package com.example;

import java.util.List;


public class Card {
    private final CardDefinition def;
    private boolean upgraded  = false;
    private boolean exhausted = false;

    /**
     * Constructs a Card instance from a shared definition.
     * @param def the static CardDefinition loaded from JSON
     */
    public Card(CardDefinition def) {
        this.def = def;
    }

    // Only used to help access the static info in cardDef, doesn't change it
    public CardDefinition getCardDefinition(){
        return def;
    }

    /**
     * Marks this card instance as upgraded if upgrade data is available.
     */
    public void upgradeCard() {
        if (def.getUpgrade() != null) {
            this.upgraded = true;
        }
    }

    /**
     * @return the unique ID of the card
     */
    public String getId() {
        return def.getId();
    }

    /**
     * @return the display name of the card
     */
    public String getName() {
        return def.getName();
    }

    /**
     * @return the energy cost to play this card
     */
    public int getCost() {
        return def.getCost();
    }

    /**
     * @return the current description, switching to upgraded text if applicable
     */
    public String getDescription() {
        if (upgraded && def.getUpgrade() != null) {
            return def.getUpgrade().getDescription();
        }
        return def.getDescription();
    }

    /**
     * @return the list of effects to apply when the card is played,
     *         choosing upgraded effects if this instance is upgraded
     */
    public List<EffectDef> getActiveEffects() {
        if (upgraded && def.getUpgrade() != null) {
            return def.getUpgrade().getEffects();
        }
        return def.getEffects();
    }

    /**
     * @return true if this card has already been played/exhausted
     */
    public boolean isExhausted() {
        return exhausted;
    }

    /**
     * Plays the card: applies each effect in sequence to the specified entities,
     * then marks the card as exhausted.
     * @param self   the entity playing the card (for block and buffs)
     * @param target the entity receiving effects (for damage and debuffs)
     */
    public void play(Entity self, Entity target) {
        if (exhausted) {
            throw new IllegalStateException("Cannot play an exhausted card: " + def.getId());
        }
        for (EffectDef effect : getActiveEffects()) {
            applyEffect(effect, self, target);
        }
        this.exhausted = true;
    }


    private void applyEffect(EffectDef effect, Entity self, Entity target) {
        String type = effect.getType();
        int value = effect.getValue();
        switch (type) {
            case "damage":
                target.takeDamage(value);
                break;
            case "block":
                self.addBlock(value);
                break;
            case "conditionalDamage":
                if (effect.getCondition() != null) {
                    String status = effect.getCondition().getStatus();
                    if (target.hasStatus(status)) {
                        target.takeDamage(value);
                    }
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown effect type: " + type);
        }
    }
}
