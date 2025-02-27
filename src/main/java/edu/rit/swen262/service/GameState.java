package edu.rit.swen262.service;

import java.util.List;

import edu.rit.swen262.domain.PlayerCharacter;

import java.util.ArrayList;

/**
 * The class which represents the current state of the game being played.
 * Both receives and manages commands as well as updates all interested observers
 * whenever a change in the game's state has occurred.
 * 
 * @author Victor Bovat, Philip Rubbo
 */
public class GameState implements IObservable {
    private List<GameObserver> observers;
    private PlayerCharacter player;
    private int turnNumber;

    /**
     * creates a new GameState object with the specific player character, initializing
     * the list of active observers to be empty by default
     * 
     * @param player the player starting the new game
     */
    public GameState(PlayerCharacter player) {
        this.observers = new ArrayList<>();
        this.player = player;
    }

    /**
     * {@inheritDoc}
     */
    public void register(GameObserver go) {
        observers.add(go);
    }

    /**
     * {@inheritDoc}
     */
    public void deregister(GameObserver go) {
        observers.remove(go);
    }

    /**
     * {@inheritDoc}
     */
    public void notifyObservers(GameEvent event) {
        for (GameObserver o : observers) {
            o.update(event);
        }
    }

    /**
     * move the player one tile on the map in the specified direction
     * 
     * @param direction the direction to move in on the map
     */
    public void movePlayer(String direction) {
        //

        this.notifyObservers(null);
    }

    /**
     * attacks the specified character on the map
     * 
     * @param c the character that is being attacked
     */
    public void attackCharacter(Character c) {
        this.notifyObservers(null);
    }

    /**
     *  opens the inventory of the player character
     */
    public void openInventory() {
        this.notifyObservers(null); 
    }
    
    /**
     * uses the item found in player character Inventory
     * 
     * needs an @param item the player character is using
     */
    public void useItem(String item) {
        this.notifyObservers(null); 
    }

    /**
     * disarms the trap found in a cardinal direction away from player character in room
     * 
     * needs an @param trap the player character is deactivating in a cardinal direction
     */
    public void disarmTrap(String trap) {
        this.notifyObservers(null); 
    }

    /**
     * stop operation of the game and return the terminal to its normal state
     */
    public void quit() {
        // shut down all relevent components
        /*TO-DO: make a way to save objects like the current map using 
        Serializable interface */

        this.notifyObservers(new GameEvent(GameEventType.QUIT_GAME));
    }

    /**
     * updates the display of the menu component in the UI to show
     * the current actions available to take
     * 
     * @param menuType Enum value that dictates the type of menu
     * @param menuText String representation of all the actions the user can take
     */
    public void displayMenu(DisplayMenuType menuType, String menuText) {
        // build event with additional data of type + display text
        GameEvent event = new GameEvent(GameEventType.DISPLAY_SUBMENU);
        event.addData("menuType", menuType);
        event.addData("menuText", menuText);

        //update UI w/ menu
        this.notifyObservers(event);
    }

    /**
     * fetches the list of {@link GameObserver GameObservers} currently watching
     * this GameState for any new changes
     * 
     * @return a list of all active {@link GameObserver GameObservers}
     */
    public List<GameObserver> getObservers() {
        return observers;
    }
}
