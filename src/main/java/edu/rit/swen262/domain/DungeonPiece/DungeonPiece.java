package edu.rit.swen262.domain.DungeonPiece;

import java.lang.reflect.Constructor;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import edu.rit.swen262.domain.Occupant;
import edu.rit.swen262.domain.RenderRepresentation;

/**
 * Defines the interface for a physical piece of a dungeon<p>
 * 
 * Try to edit this as little as possible any change could mean loads more work
 * 
 * @author Danny Catorcini
 */
public interface DungeonPiece<T> {
    //Try to edit this as little as possible

    /**
     * Give a list of {@link RenderRepresentation characters} to be displayed
     * 
     * @return list of {@link RenderRepresentation} to be displayed 
     */
    public List<RenderRepresentation> render();

    /**
     * Description of a {@link DungeonPiece}
     * 
     * @return String to be displayed
     */
    public String description();

    /**
    * Return a list of all {@link Occupant Occupants}
    * 
    * @return list of {@link Occupant Occupants} to be displayed 
    */
    public Collection<Occupant> getOccupants();

    /**
     * Creates a new Collection of the same type as col. If it cannot, then a new {@link HashSet} is returned
     * 
     * @param col a Collection<{@link Occupant}> that will have its type cloned
     * @return a new EMPTY Collection<{@link Occupant}> of the same type as col
     */
    @SuppressWarnings("unchecked") // thrown at newCol = (Collection<Occupant>) colConstructor.newInstance(transientOccupant); 
    // ^Java cannot guarantee a cast to a Collection<Occupant> but, by definition it has to be one for this entire class to be usable so it's just a nonsense error.
    public static Collection<Occupant> getNewCollectionOfType(Collection<Occupant> col) {
        Collection<Occupant> newCol = new HashSet<Occupant>(); // default implementation so at least something is there
        try { // in case any number of errors are thrown for some reason
            Constructor<?> colConstructor = col.getClass().getDeclaredConstructor(); // Get empty constructor
            if (colConstructor != null) {
                newCol = (Collection<Occupant>) colConstructor.newInstance(); // create a new instance of the class
            }
        } catch (Exception e) { // print error and stack trace without stopping execution
            System.err.println("Could not create a new collection of the same type. Defaulting to HashSet");
            e.printStackTrace();
        }
        return newCol;
    }
}
