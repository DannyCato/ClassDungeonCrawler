package edu.rit.swen262.domain;

import java.util.List;
import java.util.Random;

import edu.rit.swen262.service.GameMediator;

public class Chest implements Occupant, Lootable {
    private GameMediator mediator;

    private List<Item> contents;
    private int capacity;
    private String description;
    private Random r = new Random();


    /*
     * 
     * Chest Constructor
     */
    public Chest(List<Item> contents, int capacity) {
        this.contents = contents;
        this.capacity = capacity;
        this.description = description;
        generateChest(new CreateItemFactory());
    }

    /**
     * 
     * @return the contents of Chest object
     */
    public List<Item> getContents() {
        return this.contents;
    }

    // Needs docustring
    public void generateChest(CreateItemFactory factory) {

        int maxItems = r.nextInt(5) + 1;
        int goldRandom = r.nextInt(10) + 10;
        Gold goldValue = new Gold(goldRandom); 
        contents.add(goldValue);

        for (int i = 0; i < maxItems; i++) {
            if (contents.size() != capacity) {
                int toCreate = r.nextInt(4);

                switch(toCreate) {
                    case 0:
                        Armor armor = factory.createArmor();
                        contents.add(armor);
                        break;
                    case 1:
                        Weapon weapon = factory.createWeapon();
                        contents.add(weapon);
                        break;
                    case 2:
                        Food food = factory.createFood();
                        contents.add(food);
                        break;
                    case 3:
                        Buff buff = factory.createBuff();
                        contents.add(buff);
                        break;
                }
            }
        }
    }

    public List<Item> takeLoot() {
        return this.contents;
    }

    public void setMediator(GameMediator mediator) {
        this.mediator = mediator;
    }

    public int getCapacity() {
        return this.capacity;
    }

    @Override
    public RenderRepresentation render() {
        return RenderRepresentation.CHEST;
    }

    @Override
    public String description() {
        return this.description;
    }
}
