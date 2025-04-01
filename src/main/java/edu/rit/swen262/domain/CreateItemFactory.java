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
        return new Armor(names[getRandomName(names)], "This is armor.", defense, goldValue);
    }

    @Override
    public Weapon createWeapon() {
        int attack = r.nextInt(10);
        int goldRandom = r.nextInt(5) + 1;
        Gold goldValue = new Gold(goldRandom);
        String[] names = {"Wooden", "Iron", "Gold", "Diamond"};
        return new Weapon(names[getRandomName(names)], "This is a weapon.", attack, goldValue);
    }

    @Override
    public Food createFood() {
        int hpRandom = r.nextInt(10);
        int goldRandom = r.nextInt(5) + 1;
        Gold goldValue = new Gold(goldRandom);
        String[] names = {"Leather", "Iron", "Gold", "Diamond"};
        return new Food(names[getRandomName(names)], "This is food.", hpRandom, goldValue);
    }

    @Override
    public Buff createBuff() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createBuff'");
    }

    public int getRandomName(String[] names) {
        int options = names.length;
        return r.nextInt(options);
    }
    
}
