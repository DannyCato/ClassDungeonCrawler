package edu.rit.swen262.domain;

public class Armor {

    private String name;
    private int defense;

    public Armor(String name, int defense) {
        this.name = name;
        this.defense = defense;
    }

    public String getName(){return name;}
    public int getDefense(){return defense;}
    
}
