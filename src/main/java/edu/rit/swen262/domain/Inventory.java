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

        if (bags.size() > 0) {
            return bags.remove(bag);
        }
        return false;
    }

    public List<Bag> getBags() {

        return bags;

    }

    public int getTotalBags() {

        return bags.size();

    }

    public int getCapacity() {

        return capacity;

    }

    

}
