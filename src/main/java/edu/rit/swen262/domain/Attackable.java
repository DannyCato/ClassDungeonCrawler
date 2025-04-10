package edu.rit.swen262.domain;

import edu.rit.swen262.service.GameMediator;

/**
 * interface which defines methods for any {@link Occupant} which may take damage
 * in the MUDGame
 * 
 * @author Victor Bovat
 */
public interface Attackable {
    /**
     * sets the mediator for interactions between lootable objects and entities with
     * the capacity to loot {@link Item items}
     * 
     * @param mediator object which mediates communication between looter and lootee
     */
    public void setMediator(GameMediator mediator);

    /**
     * deals a set amount of damage to the attackable entity based upon
     * its current defence stat
     * 
     * @param damage raw damage to deal to the attackee
     * @return String description of how much damage was taken
     */
    public String takeDamage(int damage);

    public String getName();
}
