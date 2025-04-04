package edu.rit.swen262.domain;

import edu.rit.swen262.service.GameEvent;
import edu.rit.swen262.service.GameEventType;
import edu.rit.swen262.service.GameObserver;

public class Trap implements Occupant, GameObserver {
    private int damage;
    private boolean armed;
    private boolean disarmAttempted;
    private boolean isHidden;
    private String description;

    public Trap()
    {

    }

    

    /**
     * 
     * 
     * @return if the disarm attempt was successful
     */
    public boolean characterDisarm(Character disarmer) {
        
    }

    public String attack(Character trapped) {

        int damage_dealt = damage - trapped.getDefense();
        trapped.health -= damage;

        if (trapped.health < 0 || trapped.health == 0) {
            trapped.health = 0;
            return trapped.getName() + " has been defeated by " + description();
        }

        else {
            return description() + " attacked " + trapped.getName() + " for " + damage;
        }

    }

    @Override
    public RenderRepresentation render() {
        if (isHidden) {
            return RenderRepresentation.HIDDENTRAP ;
        } else {
            return RenderRepresentation.TRAP ;
        }
    }

    @Override
    public String description() {
        if (isHidden) {
            return "" ;
        } else {
            return description ;
        }
    }

    @Override
    public void update(GameEvent event) {
        if (event.getType() == GameEventType.MOVE_PLAYER) {
            // TODO: update on player moving if they are in an adjacent tile
        }
    }
    
}
