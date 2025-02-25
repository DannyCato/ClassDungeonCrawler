package edu.rit.swen262.domain;

import java.util.List;

public class Inventory {

    private List<Bag> bags;
    private int capacity;

    public Inventory(List<Bag> bags, int capacity) {
        this.bags = bags;
        this.capacity = capacity;
    }

    public boolean addBag(Bag bag) {

        if (bags.size() < capacity) {
            return bags.add(bag);
        }
        return false;

    }

    public boolean removeBag(Bag bag) {

        return bags.remove(bag);

    }

    public List<Bag> getBags() {

        return bags;

    }

}
