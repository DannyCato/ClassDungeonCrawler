package edu.rit.swen262.domain;

public class Weapon extends Item {

    private int attack;

    public Weapon(String name, String description, int attack, Gold value) {
        super(name, description, value);
        this.attack = attack;
    }

    public int getAttack() {
        return attack;
    }

    @Override
    public String toString() {
        Gold gold = getValue();
        return String.format("%s: %s%nAttack: %s%nValue: %s", getName(), getDescription(), getAttack(), gold.getCount());
    }
}
