package edu.rit.swen262.domain.DungeonPiece;

import java.lang.reflect.Constructor;
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
public class Tile implements DungeonPiece<Tile> {

    /**
     * The {@link Occupant Occupant} that will always be in a {@link Tile}. Set upon construction and final.
     */
    private final Occupant permanentOccupant;

    /**
     * A collection of {@link Occupant Occupants} that can be dynamically changed. Modified after construction <p>
     * 
     * Difference between permanent and transient will be described somewhere <p>
     * TODO: WRITE UP OF DIFFERENCE BETWEEN WHAT IS CONSIDERED PERMANENT AND TRANSIENT
     */
    private Collection<Occupant> transientOccupant;

    /**
     * If a {@link GameCharacter} can move to this {@link Tile}.
     */
    private boolean stackable;

    /**
     * If a {@link GameCharacter} can exit to a different {@link Room} from this {@link Tile}.
     */
    private boolean isExit;


    /**
     * Create a {@link Tile} with given information
     * 
     * @param pOccupant {@link Occupant} that can never be removed from the {@link Tile}
     * @param stackable whether the {@link Tile} can have other {@link Occupant Occupants} stacked on it
     * @param tOccupantCol Collection<{@link Occupant}> the collection to be used for {@link Occupant transientOccupant} so that it can be passed through as needed
     */
    public Tile(Occupant pOccupant, boolean stackable, Collection<Occupant> tOccupantCol) {
        this.permanentOccupant = pOccupant;
        this.stackable = stackable;
        this.transientOccupant = tOccupantCol ;
    }

    /**
     * Return a single element List of only the highest priority {@link Occupant Occupant's} {@link RenderRepresentation} within the {@link Tile}
     * 
     * @return List<{@link RenderRepresentation}>
     */
    @Override
    public List<RenderRepresentation> render() {
        // TODO: Do what the comment says
        throw new UnsupportedOperationException("Unimplemented method 'render'");
    }

    /**
     * Return the descriptions of the {@link Occupant Occupants}
     * 
     * @return a String of descriptions
     */
    @Override
    public String description() {
        // TODO: do what the comment says
        throw new UnsupportedOperationException("Unimplemented method 'description'");
    }

    /**
     * Gets the collection of {@link Occupant Occupants} within the {@link Tile}
     * 
     * @return Collection<{@link Occupant}>
     */
    @SuppressWarnings("unchecked") // thrown at newCol = (Collection<Occupant>) colConstructor.newInstance(transientOccupant); 
    // ^Java cannot guarantee a cast to a Collection<Occupant> but by definition, it has to be one for this entire class to be usable.
    @Override
    public Collection<Occupant> getOccupants() {
        Collection<Occupant> newCol = new HashSet<Occupant>();
        try { // in case transientOccupant is null for some reason. This would cause other errors upstream but just to be safe
            Constructor<?> colConstructor = transientOccupant.getClass().getDeclaredConstructor(); // Get empty constructor
            if (colConstructor != null) {
                newCol = (Collection<Occupant>) colConstructor.newInstance(); // create a new instance of the class
            }
        } catch (Exception e) {
            System.err.println("Could not create a new collection of the same type. Defaulting to HashSet");
            e.printStackTrace();
        }
        newCol.addAll(transientOccupant); // .addAll() and .add() are defined by Collection interface so they will be implemented
        newCol.add(permanentOccupant);
        return newCol;
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
     * Add an {@link Occupant Occupant} to the {@link Occupant Occupant} Collection
     * 
     * @param tOccupant to add
     * 
     * @return boolean if the add was successful or not
     */
    public boolean addOccupant(Occupant tOccupant) {
        // TODO: Decide on a collection type or make one and implement an add method here
        throw new UnsupportedOperationException("Unimplemented method 'addOccupant'");
    }

    /**
     * remove an {@link Occupant Occupant} from the {@link Occupant Occupants} Collection
     * 
     * @param tOccupant to remove
     * 
     * @return {@link Occupant Occupant} that was removed
     */
    public Occupant removeOccupant(Occupant tOccupant) {
        // TODO: Decide on a collection type or make one and implement a remove method here.
        throw new UnsupportedOperationException("Unimplemented method 'removeOccupant'");
    }
}
