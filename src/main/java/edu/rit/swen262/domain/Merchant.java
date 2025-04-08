package edu.rit.swen262.domain;

import java.util.Collection;
import java.util.List;
import java.util.Random;

import edu.rit.swen262.domain.DungeonPiece.Room;

public class Merchant implements Occupant{
    private List<Item> list;
    private MerchantForm currentForm;
    private Random r = new Random();
    private String description;
    private boolean safe;

    /**
     * Base constructor for Merchant that sets default values.
     * 
     * @param description   A description needed for the Occupant implementation
     */
    public Merchant(String description) {
        this.currentForm = new MerchantClosed(this);
        generateStoreItems();
        this.description = description;
        this.safe = false;
    }

    /**
     * Constructor for if you wish to create a Merchant with set variables
     * 
     * @param list          The list of items for sale.
     * @param currentForm   The state of the Merchant, either {@link MerchantOpened} or {@link MerchantClosed}
     * @param description   A description needed for the Occupant implementation
     * @param safe          Used for if the Merchant is safe to open.
     */
    public Merchant(List<Item> list, MerchantForm currentForm, String description, boolean safe) {
        this.list = list;
        this.currentForm = currentForm;
        this.description = description;
        this.safe = safe;
    }

    /**
     * Generates 3 random items for the Merchant Shop.
     */
    private void generateStoreItems() {
        CreateItemFactory factory = new CreateItemFactory();
        int capacity = 3;

        while (list.size() < capacity) {
            int rand = r.nextInt(4);
            if (rand == 0) {
                Armor armor = factory.createArmor();
                list.add(armor);
            }
            else if (rand == 1) {
                Weapon weapon = factory.createWeapon();
                list.add(weapon);
            }
            else if (rand == 2) {
                Food food = factory.createFood();
                list.add(food);
            }
            else {
                Buff buff = factory.createBuff();
                list.add(buff);
            }
        }
    }

    /**
     * Searches the current room for an enemy and if found sets it as unsafe. Otherwise its safe.
     * 
     * @param currentRoom   Passed in for somewhere to represent the current room.
     * @return              boolean of if the Merchant is safe.
     */
    public boolean isSafe(Room currentRoom) {
        Collection<Occupant> occupants = currentRoom.getOccupants();

        for (Occupant each : occupants) {
            if (each.render() == RenderRepresentation.ENEMY) {
                if (this.safe) {
                    this.safe = false;
                    this.currentForm = new MerchantClosed(this);
                }
                return this.safe;
            }
        }

        this.safe = true;
        this.currentForm = new MerchantOpened(this);
        return this.safe;
    }

    /**
     * Gets the items sold in the shop.
     * 
     * @return the list of items in the shop
     */
    public List<Item> getShopItems() {
        return this.list;
    }

    /**
     * Handles the purchase of a item and returns the result of what happened.
     * 
     * @param index         Index of the item wanting to buy from the list of items being sold.
     * @param player        The current player trying to buy from the shop.
     * @param currentRoom   Passed in for somewhere to represent the current room.
     * @return              The String result stating what happened
     */
    public String purchaseItem(int index, PlayerCharacter player, Room currentRoom) {
        isSafe(currentRoom);
        String result = currentForm.handlePurchaseItem(index, player);
        if (result == null) {
            return "Shop is closed.";
        }

        return result;
    }

    /**
     * Handles the sale of a item and returns the result of what happened.
     * 
     * @param item          The item that player wants to sell.
     * @param player        The current player trying to buy from the shop.
     * @param currentRoom   Passed in for somewhere to represent the current room.
     * @return              The String result stating what happened
     */
    public String sellItem(Item item, PlayerCharacter player, Room currentRoom) {
        isSafe(currentRoom);
        String result = currentForm.handleItemSale(item, player);
        if (result == null) {
            return "Shop is closed.";
        }

        return result;
    }

    @Override
    public RenderRepresentation render() {
        return RenderRepresentation.MERCHANT;
    }

    @Override
    public String description() {
        return this.description;
    }
}
