package edu.rit.swen262.domain;

public class Weapon implements Item {

    private String name;
    private String description;
    private int attack;
    private Gold value;

    public Weapon(String name, String description, int attack, Gold value){
        this.name = name;
        this.description = description;
        this.attack = attack;
        this.value = value;
    }

    public String getName(){return name;}
    public int getAttack(){return attack;}

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public Gold getValue() {
        return value;
    }
    
}
