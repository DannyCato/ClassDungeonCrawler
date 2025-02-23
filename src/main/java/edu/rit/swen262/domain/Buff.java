package edu.rit.swen262.domain;

public class Buff implements Item {

    private String name;
    private String description;
    private float duration;
    private int attack;
    private int defense;
    private int hp;
    private Gold value;

    public Buff(String name, String description, float duration, int attack, int defense, int hp, Gold value) {
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.attack = attack;
        this.defense = defense;
        this.hp = hp;
        this.value = value;
    }

    public String getName(){return name;}
    public float getDuration(){return duration;}
    public int getAttack(){return attack;}
    public int getDefense(){return defense;}
    public int getHp(){return hp;}

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public Gold getValue() {
        return value;
    }

}
