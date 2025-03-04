package edu.rit.swen262.domain;

public class Food extends Item {

    private int hp;

    public Food(String name, String description, int hp, Gold value) {
        super(name, description, value);
        this.hp = hp;
    }

    public int getHp() {
        return hp;
    }
}
