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
}
