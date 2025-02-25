package edu.rit.swen262.domain;

public class Buff extends Item {

    private float duration;
    private int attack;
    private int defense;
    private int hp;

    public Buff(String name, String description, float duration, int attack, int defense, int hp, Gold value) {
        super(name, description, value);
        this.attack = attack;
        this.defense = defense;
        this.hp = hp;
    }

    public float getDuration() {
        return duration;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getHp() {
        return hp;
    }
}
