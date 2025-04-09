package edu.rit.swen262.domain;
import java.util.Random;

/**
 * concrete implementation of the {@link ItemFactory} which produces simple
 * versions of {@link Armor}, {@link Weapon Weapons}, {@link Food}, and {@link Buff Buffs}
 * with randomized names, effects, and gold value
 * 
 * @author Nick F.
 */
public class CreateItemFactory implements ItemFactory {

    private Random r = new Random();

    /**
     * generates a new {@link Armor} item with a random defence stat, 
     * name, and gold value
     * 
     * @return the new {@link Armor} item
     */
    @Override
    public Armor createArmor() {
        int defense = r.nextInt(30);
        int goldRandom = r.nextInt(50) + 1; // to at least be 1
        Gold goldValue = new Gold(goldRandom);
        String[] names = {"Leather", "Iron", "Gold", "Diamond"};
        return new Armor(names[r.nextInt(names.length)], "This is armor.", defense, goldValue);
    }

    /**
     * generates a new {@link Weapon} item with a random attack stat, 
     * name, and gold value
     * 
     * @return the new {@link Weapon} item
     */
    @Override
    public Weapon createWeapon() {
        int attack = r.nextInt(30);
        int goldRandom = r.nextInt(50) + 1;
        Gold goldValue = new Gold(goldRandom);
        String[] names = {"Wooden", "Iron", "Gold", "Diamond"};
        return new Weapon(names[r.nextInt(names.length)], "This is a weapon.", attack, goldValue);
    }
    
    /**
     * generates a new {@link Food} item with a random health restore stat,
     * name, and gold value
     * 
     * @return the new {@link Food} item
     */
    @Override
    public Food createFood() {
        int hpRandom = r.nextInt(30);
        int goldRandom = r.nextInt(50) + 1;
        Gold goldValue = new Gold(goldRandom);
        String[] names = {"Steak", "Burger", "Apple", "Rocks"};
        return new Food(names[r.nextInt(names.length)], "This is food.", hpRandom, goldValue);
    }


    /**
     * Generates a new {@link Buff} item with a random gold value, name, 
     * and stat increase
     * 
     * @return the new {@link Buff} item
     */
    @Override
    public Buff createBuff() {
 
        int goldRandom = r.nextInt(50) + 1;
        Gold goldValue = new Gold(goldRandom);
        String[] names = {"Attack Potion", "Defense Potion", "Health Potion"};
        String potion = names[r.nextInt(names.length)];
        int attack = 0;
        int defense = 0;
        int health = 0;

        switch(potion) {
            case "Attack Potion":
                attack = r.nextInt(30);
                break;
            case "Defense Potion":
                defense = r.nextInt(30);
                break;
            case "Health Potion":
                health = r.nextInt(30);
                break;
        }

        return new Buff(potion, "This is a" + potion, 10, attack, defense, health, goldValue);
    }
    
}
