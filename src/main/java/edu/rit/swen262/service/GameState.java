package edu.rit.swen262.service;

import java.util.List;

import edu.rit.swen262.domain.Armor;
import edu.rit.swen262.domain.Attackable;
import edu.rit.swen262.domain.Bag;
import edu.rit.swen262.domain.DayTime;
import edu.rit.swen262.domain.DirectionalVector;
import edu.rit.swen262.domain.GameCharacter;
import edu.rit.swen262.domain.Inventory;
import edu.rit.swen262.domain.Item;
import edu.rit.swen262.domain.Lootable;
import edu.rit.swen262.domain.Occupant;
import edu.rit.swen262.domain.PlayerCharacter;
import edu.rit.swen262.domain.DungeonPiece.Map;
import edu.rit.swen262.domain.DungeonPiece.Room;
import edu.rit.swen262.domain.DungeonPiece.Tile;
import edu.rit.swen262.domain.TimePeriod;
import edu.rit.swen262.domain.Weapon;
import edu.rit.swen262.service.Action.DisplayMenuType;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * The class which represents the current state of the game being played.
 * Both receives and manages commands as well as updates all interested observers
 * whenever a change in the game's state has occurred.
 * 
 * @author Victor Bovat, Philip Rubbo
 */
public class GameState implements IObservable, GameMediator {
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
     * updates the map panel within the {@link MUDGameUI UI component} to display
     * the current version of the room the player is currently inside
     */
    public void updateMap() {
        String currentRoomRender = this.map.structuredRender();

        GameEvent event = new GameEvent(GameEventType.UPDATE_MAP);
        event.addData("currentRoom", currentRoomRender);

        this.notifyObservers(event);
    }

    /**
     * updates the data of the player character currently playing the game
     * 
     * @param p the new player character to update to
     */
    public void updatePlayer(PlayerCharacter p) {
        this.player = p;
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
        
        //check if valid target found in attack direction on map, awaiting integration :(

        // test enemy
        PlayerCharacter recipient = new PlayerCharacter("target dummy", "a sword pincushion");

        String attackMessage = recipient.getName() + " launched an attack on " + player.getName() + "!";
        String dmgMessage = attackCharacter(player, recipient);

        event.addData("attackMessage", attackMessage);
        event.addData("dmgMessage", dmgMessage);

        this.notifyObservers(event);
        this.playerTurnFinished();
    }
    
    /** 
     * handles combat between an initator attacking and a receiver "defending" the attack
     * 
     * @param initiator the character initiating combat and attacking
     * @param receiver the character receiving the attack
     */
    public String attackCharacter(GameCharacter initiator, Attackable receiver) {
        return receiver.takeDamage(initiator.getAttack());
    }

    /** 
     * handles a player interacting with a lootable object
     *
     * @param player the Player Character attempting to loot the object
     * @param lootObject object that is being looted by the Player Character
     */
    public void lootObject(PlayerCharacter player, Lootable lootObject) {
        List<Item> loot = lootObject.takeLoot();

        player.takeLoot(loot);
    }
    
    /**
     * uses the item found in player character {@link Inventory}
     * 
     * @param item the item the player character is using
     */
    public void useItem(Item item) {
        String itemMsg = player.useItem(item);
        GameEvent event = new GameEvent(GameEventType.USE_ITEM);
        event.addData("item", item);
        event.addData("message", itemMsg);

        if (!(item instanceof Weapon) && !(item instanceof Armor)) {
            player.dropItem(item);
        }
        
        this.notifyObservers(event); 
    }

    /**
     * drops the item found in player character {@link Inventory}
     * 
     * @param item the item the player character is dropping
     */
    public void dropItem(Item item) {
        GameEvent event = new GameEvent(GameEventType.DROP_ITEM);
        event.addData("item", item);

        player.dropItem(item);

        this.notifyObservers(event); 
    }

    /**
     * adds a single item to the {@link PlayerCharacter player's} {@link Inventory}
     * @param item the item to be added to the inventory
     */
    public void pickUpItem(Item item) {
        player.addItemToBag(item);
    }

    /**
     * disarms the trap found in a cardinal direction away from the 
     * {@link PlayerCharacter player character} in a {@link Room room}
     * 
     * @param trap the trap being deactivated adjacent to the player
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
     * @param menuPath optional
     */
    public void displayMenu(DisplayMenuType menuType, String menuText, List<Character> menuPath) {
        // build event with additional data of type + display text
        GameEvent event = new GameEvent(GameEventType.DISPLAY_SUBMENU);
        event.addData("menuType", menuType);
        event.addData("menuText", menuText);

        event.addData("goldValue", 0);

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

     /**
     * fetches the {@link Inventory Inventory} of the current player
     * 
     * @return the a list of {@link Bag Bags} of the current player's inventory
     */
    public Inventory getInventory() {
        return this.player.getInventory();
    }
}
