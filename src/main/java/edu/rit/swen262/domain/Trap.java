package edu.rit.swen262.domain;

import java.util.ArrayList;
import java.util.Collection;

import edu.rit.swen262.domain.DungeonPiece.DungeonPiece;
import edu.rit.swen262.domain.DungeonPiece.Map;
import edu.rit.swen262.domain.DungeonPiece.Room;
import edu.rit.swen262.domain.DungeonPiece.Tile;
import edu.rit.swen262.service.GameEvent;
import edu.rit.swen262.service.GameEventType;
import edu.rit.swen262.service.GameObserver;
import edu.rit.swen262.service.RandomInstance;

public class Trap implements Occupant, GameObserver {
    private int damage;
    private boolean armed;
    private boolean disarmAttempted;
    private boolean isHidden;
    private String description;

    public Trap(int damage, String description) {
        this.damage = damage;
        this.description = description; 
    }

    public Trap(int damage) {
        this(damage, "A bouncing trap");
    }

    public Trap(String description) {
        this(4, description);
    }

    public Trap() {
        this(4, "A bouncing trap");
    }

    /**
     * 
     * 
     * @return if the disarm attempt was successful
     */
    public boolean characterDisarm(GameCharacter disarmer) {
        if (disarmAttempted) {
            return false ;
        }

        disarmAttempted = true;
        if (RandomInstance.instance().nextInt(0, 100) < 25) {
            attack(disarmer);
            return false;
        } else {
            armed = false ;
            return true ;
        }
    }

    public String attack(GameCharacter trapped) {
        if (!armed) {
            return "" ;
        }
        int damage_dealt = damage - trapped.getDefense();
        trapped.health -= damage_dealt;

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

    /**
     * Checks if the recieved update is a Move event and checks if the player has
     * moved to be adjacent to the tile. Use this to roll for if the trap should
     * be revealed
     * 
     * @param event {@link GameEvent} sent by {@link GameState}
     */
    @Override
    public void update(GameEvent event) {
        if (event.getType() != GameEventType.MOVE_PLAYER) {
            return;
        }
        if (isHidden) {
            Room room = (Room)((Map) event.getData("mapReference")).getCurrentRoom();
            ArrayList<DungeonPiece<Tile>> ts = new ArrayList<>();
            ts.add(room.getTileOfOccupant(this));
            room.getAllAdjacentTiles(ts.get(0), ts);
            for (DungeonPiece<Tile> t : ts ) {
                Tile ti = (Tile) t;
                Collection<Occupant> co = ti.getOccupants() ;
                for (Occupant o : co) {
                    if (o instanceof PlayerCharacter) {
                        if (RandomInstance.instance().nextInt(0, 100) > 50) {
                            isHidden = false ;
                        }
                    }
                }
            }
        }
    }
}
