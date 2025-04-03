package edu.rit.swen262.service;

import java.util.List;

import edu.rit.swen262.domain.DayTime;
import edu.rit.swen262.domain.DirectionalVector;
import edu.rit.swen262.domain.Inventory;
import edu.rit.swen262.domain.Item;
import edu.rit.swen262.domain.Occupant;
import edu.rit.swen262.domain.PlayerCharacter;
import edu.rit.swen262.domain.RenderRepresentation;
import edu.rit.swen262.domain.DungeonPiece.DungeonPiece;
import edu.rit.swen262.domain.DungeonPiece.Map;
import edu.rit.swen262.domain.DungeonPiece.Room;
import edu.rit.swen262.domain.DungeonPiece.Tile;
import edu.rit.swen262.domain.TimePeriod;
import edu.rit.swen262.service.Action.DisplayMenuType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

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
    private Map map;
    private int turnNumber;
    private TimePeriod currentTime;

    /**
     * creates a new GameState object with the specific player character, initializing
     * the list of active observers to be empty by default, with the turn 
     * counter starting at 1
     * 
     * @param player the player starting the new game
     */
    public GameState(PlayerCharacter player) {
        this.observers = new ArrayList<>();
        this.player = player;
        this.map = this.buildMap();
        this.turnNumber = 1;
        this.setTime(new DayTime(this));
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
     * helper/test method which builds the exact same instance of a map
     * upon initialization
     * 
     * @return the completed map
     */
    private Map buildMap() {
        HashSet<Occupant> initOccupants = new HashSet<>();
        initOccupants.add(this.player);

        Room root = new Room(8, 4, "test starting room");
        Room room2 = new Room(10, 5, "test second room");

        Map newMap = new Map(root);

        newMap.addRoom(root, room2, DirectionalVector.WEST, false);
        
        // root.getRoomNode().setConnection(room2.getRoomNode(), DirectionalVector.EAST);
        // room2.getRoomNode().setConnection(root.getRoomNode(), DirectionalVector.WEST);
        
        RoomFiller.fill(root, 0.1);
        RoomFiller.fill(room2, 0.1);
        
        Tile startTile = (Tile)newMap.startUp();
        
        startTile.addOccupant(this.player);

        return newMap;
    }

    /**
     * move the player one tile on the map in the specified direction
     * 
     * @param direction the direction to move in on the map
     */
    public void movePlayer(DirectionalVector direction) {
        //update map??
        this.map.move(player, direction);
        
        //convert current Room to String render, then pass along to UI
        String currentRoomRender = this.map.structuredRender();

        GameEvent event = new GameEvent(GameEventType.MOVE_PLAYER);
        event.addData("direction", direction);
        event.addData("currentRoom", currentRoomRender);

        this.notifyObservers(event);

        this.playerTurnFinished();
    }

    /**
     * attacks the specified character on the map
     * 
     * @param direction the direction in which to attempt an attack
     */
    public void attackCharacter(DirectionalVector direction) {
        GameEvent event = new GameEvent(GameEventType.TAKE_DAMAGE);
        event.addData("direction", direction);
        
        this.notifyObservers(event);
    }

    /**
     *  opens the inventory of the player character
     */
    public void openInventory() {
        //still waiting on integration w/ inventory subsystem
        this.notifyObservers(null); 
    }
    
    /**
     * uses the item found in player character Inventory
     * 
     * needs an @param item the player character is using
     */
    public void useItem(Item item) {
        String itemMsg = player.useItem(item);
        GameEvent event = new GameEvent(GameEventType.USE_ITEM);
        event.addData("item", item);
        event.addData("message", itemMsg);
        
        this.notifyObservers(event); 
    }

    /**
     * test method for adding single item to inventory
     * @param item
     */
    public void pickUpItem(Item item) {
        player.pickUpItem(item);
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
        /*TODO: make a way to save objects like the current map using 
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
     * sets the current time  to the specified {@link TimePeriod}
     * 
     * @param time the time to change to
     */
    public void setTime(TimePeriod time) {
        this.currentTime = time;

        GameEvent event = new GameEvent(GameEventType.CHANGE_TIME);
        event.addData("time", time.toString());
        this.notifyObservers(event);
    }

    /**
     * executed when the user executes an action that consumes a turn:
     * moving, attacking, opening a chest, disarming a trap
     */
    public void playerTurnFinished() {
        this.turnNumber++;
        this.currentTime.handlePlayerTurn();

        GameEvent event = new GameEvent(GameEventType.FINISH_TURN);
        event.addData("turnNumber", this.turnNumber);
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

    /**
     * fetches the turn number the game is currently on
     * 
     * @return the current turn number
     */
    public int getTurnNumber() {
        return this.turnNumber;
    }

    public Inventory getInventory() {
        return this.player.getInventory();
    }
}
