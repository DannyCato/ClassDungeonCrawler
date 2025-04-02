package edu.rit.swen262.domain;
import java.util.Random;

public class CreateItemFactory implements ItemFactory {

    private Random r = new Random();

    @Override
    public Armor createArmor() {
        int defense = r.nextInt(10);
        int goldRandom = r.nextInt(5) + 1; // to at least be 1
        Gold goldValue = new Gold(goldRandom);
        String[] names = {"Leather", "Iron", "Gold", "Diamond"};
        return new Armor(names[r.nextInt(names.length)], "This is armor.", defense, goldValue);
    }

    @Override
    public Weapon createWeapon() {
        int attack = r.nextInt(10);
        int goldRandom = r.nextInt(5) + 1;
        Gold goldValue = new Gold(goldRandom);
        String[] names = {"Wooden", "Iron", "Gold", "Diamond"};
        return new Weapon(names[r.nextInt(names.length)], "This is a weapon.", attack, goldValue);
    }

    @Override
    public Food createFood() {
        int hpRandom = r.nextInt(10);
        int goldRandom = r.nextInt(5) + 1;
        Gold goldValue = new Gold(goldRandom);
        String[] names = {"Leather", "Iron", "Gold", "Diamond"};
        return new Food(names[r.nextInt(names.length)], "This is food.", hpRandom, goldValue);
    }

    @Override
    public Buff createBuff() {
 
        int goldRandom = r.nextInt(5) + 1;
        Gold goldValue = new Gold(goldRandom);
        String[] names = {"Attack Potion", "Defense Potion", "Health Potion"};
        String potion = names[r.nextInt(names.length)];
        int attack = 0;
        int defense = 0;
        int health = 0;

        if (potion.equals("Attack Potion")) {
            attack = r.nextInt(5);
        }
        else if(potion.equals("Defense Potion")) {
            defense = r.nextInt(5);
        }
        else if(potion.equals("Health Potion")) {
            health = r.nextInt(10);
        }
        return new Buff(potion, "This is a" + potion, 10, attack, defense, health, goldValue);

    }
    
}
