package edu.rit.swen262.domain;

public class Food {

    private String name;
    private int hp;

    public Food(String name, int hp) {
        this.name = name;
        this.hp = hp;
    }
    
    public String getName(){return name;}
    public int getHp(){return hp;}
    
}
