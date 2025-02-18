package edu.rit.swen262.service;

public interface IObservable {
    /**
     * adds the specified GameObserver to the list of observers watching
     * the Observable
     * 
     * @param go the observer to be registered
     */
    public void register(GameObserver go);
    /**
     * removes the specified GameObserver from the list of observers watching
     * the Observable
     * 
     * @param go the observer to be registered
     */
    public void deregister(GameObserver go);

    /**
     * notifies all GameObservers within the list of observers that
     * a change has occurred
     */
    public void notifyObservers();
}
