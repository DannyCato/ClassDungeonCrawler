package edu.rit.swen262.domain;

public class Buff {

    private String name;
    private float duration;
    private int attack;
    private int defense;
    private int hp;

    public Buff(String name, float duration, int attack, int defense, int hp) {
        this.name = name;
        this.duration = duration;
        this.attack = attack;
        this.defense = defense;
        this.hp = hp;
    }

    public String getName(){return name;}
    public float getDuration(){return duration;}
    public int getAttack(){return attack;}
    public int getDefense(){return defense;}
    public int getHp(){return hp;}

}
