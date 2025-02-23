package edu.rit.swen262.domain;

public class Armor implements Item {

    private String name;
    private String description;
    private int defense;
    private Gold value;

    public Armor(String name, String description, int defense, Gold value) {
        this.name = name;
        this.description = description;
        this.defense = defense;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getDefense() {
        return defense;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public Gold getValue() {
        return value;
    }

}
