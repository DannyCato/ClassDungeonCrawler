package edu.rit.swen262.domain.DungeonPiece;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import edu.rit.swen262.domain.Occupant;
import edu.rit.swen262.domain.RenderRepresentation;


/**
 * A representation of the {@link Tile} {@link DungeonPiece} <p>
 * 
 * The difference between a transient and a permanent object is that a transient object can be interacted with in a way that
 * would reasonably remove it from the Tile. That is, a charater moving, enemy being defeated, or a Chest being used (<--- ALL TRANSIENT).
 * While a permanemt would realistically always be there, like a boulder or an exit.
 *                         (v LIST YOUR NAME HERE WHEN YOU WORK ON IT v)
 * @authors Danny Catorcini, Eric Manning
 */
public class Tile implements DungeonPiece<Tile> {

    /**
     * The {@link Occupant Occupant} that will always be in a {@link Tile}. Set upon construction and final.
     */
    private final Occupant permanentOccupant;

    /**
     * A collection of {@link Occupant Occupants} that can be dynamically changed. Modified after construction <p>
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


    // <-----------------------Constructor----------------------->

    /**
     * Create a {@link Tile} with given information
     * 
     * @param pOccupant {@link Occupant} that can never be removed from the {@link Tile}
     * @param stackable whether the {@link Tile} can have other {@link Occupant Occupants} stacked on it
     * @param tOccupantCol Collection<{@link Occupant}> (Recommend an unordered collection like {@link HashSet} for now) the collection to be used for {@link Occupant transientOccupant} so that it can be passed through as needed
     */
    public Tile(Occupant pOccupant, boolean stackable, Collection<Occupant> tOccupantCol) {
        this.permanentOccupant = pOccupant;
        this.stackable = stackable;
        this.transientOccupant = tOccupantCol ;
    }

    /**
     * Create a {@link Tile} with given information. tOccupantCol is set to be a {@link HashSet}
     * 
     * @param pOccupant {@link Occupant} that can never be removed from the {@link Tile}
     * @param stackable whether the {@link Tile} can have other {@link Occupant Occupants} stacked on it
     */
    public Tile(Occupant pOccupant, boolean stackable) {
        this(pOccupant, stackable, new HashSet<Occupant>());
    }

    /**
     * Create an empty {@link Tile}. tOccupantCol is set to be a {@link HashSet}
     */
    public Tile() {
        this(null, true);
    }

    /**
     * Create an empty {@link Tile}. tOccupantCol is set to be a {@link HashSet}
     * 
     * @param tOccupantCol Collection<{@link Occupant}> (Recommend an unordered collection like {@link HashSet} for now) the collection to be used for {@link Occupant transientOccupant} so that it can be passed through as needed
     */
    public Tile(Collection<Occupant> tOccupantCol) {
        this(null, true, tOccupantCol);
    }


    // <-----------------------Methods----------------------->

    /**
     * Return a List of only the highest priority {@link Occupant Occupants'} {@link RenderRepresentation} within the {@link Tile}
     * 
     * @return List<{@link RenderRepresentation}>
     */
    @Override
    public List<RenderRepresentation> render() {
        ArrayList<RenderRepresentation> atPriorityLevel = new ArrayList<>() ; // return an arrayList
        if ( hasPermanentOccupant() ) { // check if not null
            atPriorityLevel.add(permanentOccupant.render()); // then put the render representation
        } else {
            atPriorityLevel.add(RenderRepresentation.EMPTY); // Add as if it was empty
        }
        for (Occupant occupant : transientOccupant) { // go through all transientOccupants
            RenderRepresentation rr = occupant.render(); // get their render
            switch ( atPriorityLevel.get(0).compare(atPriorityLevel.get(0), rr)) { // compare first element of atPriorityLevel
                case -1: // if atPriorityLevel is less than rr
                    atPriorityLevel.clear();
                    atPriorityLevel.add(rr); // refresh atPriorityLevel with only add rr
                    break;
                case 0:
                    atPriorityLevel.add(rr); // append to atPriorityLevel
                    break;
                default:
                    break;
            }
        }
        return atPriorityLevel ;
    }

    /**
     * Return a formatted description of the {@link Tile Tile's} {@link Occupant Occupants}
     * 
     * @return a String of descriptions
     */
    @Override
    public String description() {
        String desc = "";
        boolean trigger = false;
        if (hasPermanentOccupant()){
            desc += permanentOccupant.description();
            trigger = true;
        }

        for (Occupant occupant : transientOccupant) {
            if (trigger) {
                desc += ", ";
            }
            String compStr = occupant.description(); 
            if (compStr.equals(""))
            {
                trigger = false;
                continue;
            }
            desc += compStr;
            trigger = true;
        }
        return desc;
    }

    /**
     * Gets the collection of {@link Occupant Occupants} within the {@link Tile}
     * 
     * @return Collection<{@link Occupant}>
     */
    @SuppressWarnings("unchecked") // thrown at newCol = (Collection<Occupant>) colConstructor.newInstance(transientOccupant); 
    // ^Java cannot guarantee a cast to a Collection<Occupant> but, by definition it has to be one for this entire class to be usable so it's just a nonsense error.
    @Override
    public Collection<Occupant> getOccupants() {
        Collection<Occupant> newCol = new HashSet<Occupant>();
        try { // in case transientOccupant is null for some reason. This would cause other errors upstream but just to be safe
            Constructor<?> colConstructor = transientOccupant.getClass().getDeclaredConstructor(); // Get empty constructor
            if (colConstructor != null) {
                newCol = (Collection<Occupant>) colConstructor.newInstance(); // create a new instance of the class
            }
        } catch (Exception e) { // print error and stack trace without stopping execution
            System.err.println("Could not create a new collection of the same type. Defaulting to HashSet");
            e.printStackTrace();
        }
        newCol.addAll(transientOccupant); // add all transisents
        newCol.add(permanentOccupant); // add permanent
        newCol.remove(null); // remove any null elements which might populate the data
        return newCol;
    }

    /**
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
     * @return boolean stackable
     */
    public boolean isStackable() {
        return stackable;
    }

    /**
     * set whether an {@link Occupant Occupant} can move to this {@link Tile Tile}
     * 
     * @param stackable boolean
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
        if (stackable && !transientOccupant.contains(tOccupant)) {
            transientOccupant.add(tOccupant);
            return true;
        }
        return false;
    }

    /**
     * remove an {@link Occupant Occupant} from the {@link Occupant Occupants} Collection
     * 
     * @param tOccupant to remove
     * 
     * @return {@link Occupant Occupant} that was removed. Null if none was removed
     */
    public Occupant removeOccupant(Occupant tOccupant) {
        if (transientOccupant.contains(tOccupant)) {
            transientOccupant.remove(tOccupant);
            return tOccupant;
        }
        return null;
    }

    /**
     * True if has a permanentOccupant. See class for description on permanent occupant
     * 
     * @return boolean
     */
    public boolean hasPermanentOccupant() {
        return permanentOccupant != null;
    }
}
