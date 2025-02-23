package edu.rit.swen262.domain;

public class Food implements Item {

    private String name;
    private String description;
    private int hp;
    private Gold value;

    public Food(String name, String description, int hp, Gold value) {
        this.name = name;
        this.description = description;
        this.hp = hp;
        this.value = value;
    }
    
    public String getName(){return name;}
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
