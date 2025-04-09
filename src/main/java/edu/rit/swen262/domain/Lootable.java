package edu.rit.swen262.domain;

import java.util.List;

import edu.rit.swen262.service.GameMediator;

/**
 * interface which defines methods for any {@link Occupant} which may take damage
 * in the MUDGame
 * 
 * @author Victor Bovat
 */
public interface Lootable {
    /**
     * sets the mediator for interactions between lootable objects and entities with
     * the capacity to loot {@link Item items}
     * 
     * @param mediator object which mediates communication between looter and lootee
     */
    public void setMediator(GameMediator mediator);

    /**
     * fetches the list of {@link Item items} contained within the 
     * lootable object
     * 
     * @return list of {@link Item items} to loot
     */
    public List<Item> takeLoot();
}
