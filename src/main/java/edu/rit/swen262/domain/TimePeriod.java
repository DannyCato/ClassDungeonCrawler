package edu.rit.swen262.domain;

/**
 * Defines the interface for a period of time the {@link Map} can exist in.
 * Kept track of by the {@link GameState} and affects NPCs dpeending upon what
 * TimePeriod is currently active at the turn change.
 * 
 * @author Victor Bovat
 */
public interface TimePeriod {
    /**
     * checks the current turn number, and if ten turns have passed since the last
     * time change, transitions to the next time of day.
     */
    public void handlePlayerTurn();

    /**
     * applies the correct buffs or debuffs to NPCs based upon whether they
     * are nocturnal or diurnal
     */
    public void handleApplyEnemyBuffs();
}
