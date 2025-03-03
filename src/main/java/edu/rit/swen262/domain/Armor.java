package edu.rit.swen262.domain;

public class Armor extends Item {

    private int defense;

    public Armor(String name, String description, int defense, Gold value) {
        super(name, description, value);
        this.defense = defense;
    }

    public int getDefense() {
        return defense;
    }

    @Override
    public String toString() {
        Gold gold = getValue();
        return String.format("%s: %s%nDefense: %s%nValue: %s", getName(), getDescription(), getDefense(), gold.getCount());
    }
}
