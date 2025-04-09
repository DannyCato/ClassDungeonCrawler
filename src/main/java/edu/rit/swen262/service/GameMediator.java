package edu.rit.swen262.service;

import edu.rit.swen262.domain.Attackable;
import edu.rit.swen262.domain.GameCharacter;
import edu.rit.swen262.domain.Lootable;
import edu.rit.swen262.domain.PlayerCharacter;

/**
 * interface which defines the operations for mediating interactions
 * between multiple {@link Attackable} entities and multiple 
 * {@link Lootable} entities and applying the correct effects to each 
 * participant
 * 
 * @author Victor Bovat
 */
public interface GameMediator {
    /**
     * initiates an attack from the {@link Attackable initiator} based upon 
     * their sttack stat to deal damage to the {@link Attackable receiver},
     * which has its health reduced based on its defence stat
     * 
     * @param initiator the entity initiating an attack to deal damage
     * @param receiver the entity being damaged
     * @return
     */
    public String attackCharacter(GameCharacter initiator, Attackable receiver);

    /**
     * initiates a looting action by the {@link PlayerCharacter player} and 
     * attempts to draw loot from the {@link Lootable lootable object}
     * 
     * @param player the player adding loot to their inventory
     * @param lootObject the entity to take items from
     */
    public void lootObject(PlayerCharacter player, Lootable lootObject);
}
