package edu.rit.swen262.domain;

import java.util.ArrayList;
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

    public Merchant(String description) {
        this.currentForm = new MerchantOpened(this);
        generateStoreItems();
        this.description = description;
        this.safe = false;
    }

    public Merchant(List<Item> list, MerchantForm currentForm, String description, boolean safe) {
        this.list = list;
        this.currentForm = currentForm;
        this.description = description;
        this.safe = safe;
    }

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

    public void isSafe(Room currentRoom) {
        Collection<Occupant> occupants = currentRoom.getOccupants();
        
        for (Occupant each : occupants) {
            if (each.render() == RenderRepresentation.ENEMY) {
                this.safe = false;
                break;
            }
        }

        this.safe = true;
    }

    public List<Item> getShopItems() {
        return this.list;
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
