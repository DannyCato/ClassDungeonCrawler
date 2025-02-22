package edu.rit.swen262.domain.DungeonPiece;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import edu.rit.swen262.domain.Occupant;
import edu.rit.swen262.domain.RenderRepresentation;


/**
 * A representation of the Tile DungeonPiece
 * 
 * @author Danny Catorcini
 */
public class Tile implements DungeonPiece<Tile>{

    /**
     * The {@link Occupant Occupant}  that will always be in a Tile. Set upon construction.
     */
    private Occupant permanentOccupant;

    /**
     * A set of {@link Occupant Occupants} that can be dynamically changed. Modified after construction <p>
     * 
     * Difference between permanent and transient will be described SOMEWHERE <p>
     * TODO: WRITE UP OF DIFFERENCE BETWEEN WHAT IS CONSIDERED PERMANENT AND TRANSIENT
     */
    private Collection<Occupant> transientOccupant;

    /**
     * If a character can move to this Tile.
     */
    private boolean stackable;

    /**
     * If a player can exit to a different room from this tile.
     */
    private boolean isExit;


    /**
     * Create a Tile with given information
     * 
     * @param pOccupant {@link Occupant} that can never be removed from the Tile
     */
    public Tile(Occupant pOccupant, boolean stackable) {
        this.permanentOccupant = pOccupant;
        this.stackable = stackable;
        this.transientOccupant = new HashSet<Occupant>() ;
    }


    @Override
    public List<RenderRepresentation> render() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'render'");
    }

    @Override
    public String description() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'description'");
    }

    @Override
    public Collection<Occupant> getOccupants() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getOccupants'");
    }
    
    /**
     * 
     * @return boolean of what isExit is set to
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * Set if the Exit can be adjusted
     * 
     * @param isExit what to set this.isExit to
     */
    public void setExit(boolean isExit) {
        this.isExit = isExit ;
    }

    /**
     * get whether an {@link Occupant Occupant} can move to this {@link Tile Tile}
     * 
     * @return stackable
     */
    public boolean isStackable() {
        return stackable;
    }

    /**
     * set whether an {@link Occupant Occupant} can move to this {@link Tile Tile}
     * 
     * @param stackable
     */
    public void setStackable(boolean stackable) {
        this.stackable = stackable;
    }


    /**
     * Add an {@link Occupant Occupant} to the Occupant Set
     * 
     * @param tOccupant to add
     * 
     * @return boolean if the add was successful or not
     */
    public boolean addOccupant(Occupant tOccupant) 
    {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addOccupant'");
    }

    public void removeOccupant(Occupant tOccupant)
    {

    }
}
