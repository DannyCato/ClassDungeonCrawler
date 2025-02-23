package edu.rit.swen262.service;

/**
 * defines the interface for any subject capable of being observed by an
 * {@link GameObserver}, manages its own list of observers and is capable of
 * adding, removing, and notifying each of them.
 */
public interface IObservable {
    /**
     * adds the specified {@link GameObserver} to the list of observers watching
     * the Observable
     * 
     * @param go the observer to be registered
     */
    public void register(GameObserver go);
    /**
     * removes the specified {@link GameObserver} from the list of observers watching
     * the Observable
     * 
     * @param go the observer to be registered
     */
    public void deregister(GameObserver go);

    /**
     * notifies all {@link GameObserver GameObservers} within the list of observers that
     * a change has occurred
     */
    public void notifyObservers();
}
