package edu.rit.swen262.domain;

import java.util.List;
import java.util.Random;

import edu.rit.swen262.service.GameMediator;

/**
 * a type of {@link Occupant} which contains a set number of items which may be
 * looted by the {@link PlayerCharacter player}
 * 
 * @author Nick F., Victor Bovat
 */
public class Chest implements Occupant, Lootable {
    private GameMediator mediator;

    private List<Item> contents;
    private int capacity;
    private String description;
    private Random r = new Random();


    /**
     * Constructs a new Chest {@link Occupant} with the given contents, 
     * capacity, and description
     * 
     * @param contents the list of items the chest holds
     * @param capacity the max number of items the chest may hold
     * @param description description of the chest
     */
    public Chest(List<Item> contents, int capacity, String description) {
        this.contents = contents;
        this.capacity = capacity;
        this.description = description;
        generateChest(new CreateItemFactory());
    }

    /**
     * Constructs a new Chest with the given contents, and capacity.
     * The decsription is initialized to the stringified version of its contents
     * by default.
     * 
     * @param contents the list of items the chest holds
     * @param capacity the max number of items the chest may hold
     */
    public Chest(List<Item> contents, int capacity) {
        this(contents, capacity, contents.toString());
        generateChest(new CreateItemFactory());
    }

    /**
     * fetches the list in items held in the chest
     * 
     * @return the contents of Chest object
     */
    public List<Item> getContents() {
        return this.contents;
    }

    /**
     * populates the chest's contents with {@link Item items} with a random number
     * and type of new items
     * 
     * @param factory the factory object which creates new {@link Item items}
     */
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

    /**
     * {@inheritdoc}
     */
    public List<Item> takeLoot() {
        return this.contents;
    }

    /**
     * {@inheritdoc}
     */
    public void setMediator(GameMediator mediator) {
        this.mediator = mediator;
    }

    /**
     * fetches the max capacity of the chest
     * 
     * @return capacity of the chest
     */
    public int getCapacity() {
        return this.capacity;
    }

    /**
     * fetches the Render Representation for rendering Chest Event
     * 
     * @return the render representation for Chest Object
     */
    @Override
    public RenderRepresentation render() {
        return RenderRepresentation.CHEST;
    }

    /**
     * fetches the description of the Chest object
     * 
     * @return the description of the Chest object
     */
    @Override
    public String description() {
        return this.description;
    }
}
