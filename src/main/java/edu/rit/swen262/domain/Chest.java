package edu.rit.swen262.domain;

import java.util.List;
import java.util.Random;

public class Chest {

    private List<Item> contents;
    private int capacity;
    private Random r = new Random();

    public Chest(List<Item> contents, int capacity) {
        this.contents = contents;
        this.capacity = capacity;
        generateChest(new CreateItemFactory());
    }

    public List<Item> getContents() {
        return this.contents;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public void generateChest(CreateItemFactory factory) {

        int maxItems = r.nextInt(5) + 1;
        int goldRandom = r.nextInt(10) + 10;
        Gold goldValue = new Gold(goldRandom);
        contents.add(goldValue);

        for (int i = 0; i < maxItems; i++) {
            if (contents.size() != capacity) {
                int toCreate = r.nextInt(3);

                if (toCreate == 0) {
                    Armor armor = factory.createArmor();
                    contents.add(armor);
                }
                else if (toCreate == 1) {
                    Weapon weapon = factory.createWeapon();
                    contents.add(weapon);
                }
                else if (toCreate == 2) {
                    Food food = factory.createFood();
                    contents.add(food);
                }

                else if (toCreate == 3) {
                    Buff buff = factory.createBuff();
                    contents.add(buff);

                    // will make this switch case in a minute
                }
            }
        }
    }
}
