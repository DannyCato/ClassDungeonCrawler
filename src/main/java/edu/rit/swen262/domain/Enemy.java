package edu.rit.swen262.domain;

import java.util.List;

import edu.rit.swen262.service.GameState;

public class Enemy extends GameCharacter {
    private GameState mediator;
    private List<Item> loot;
    // TO-DO: implement attacking


    public Enemy(String name, int maxHP, int attack, int defense) {
        super(name, name + " (HP: " + maxHP + ", Attack: " + attack + ", Defense: " + defense + ")", maxHP, attack, defense);
    }

    @Override
    public RenderRepresentation render() {
        return RenderRepresentation.ENEMY;
    }

    @Override
    public String description() {
        return super.description;
    }
}
