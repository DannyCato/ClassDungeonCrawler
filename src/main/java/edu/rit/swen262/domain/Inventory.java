package edu.rit.swen262.domain;

import java.util.ArrayList;
import java.util.List;

import edu.rit.swen262.service.GameEvent;
import edu.rit.swen262.service.GameEventType;
import edu.rit.swen262.service.GameObserver;
import edu.rit.swen262.service.IObservable;

public class Inventory {
    private List<GameObserver> observers;
    private List<Bag> bags;
    private int capacity;

    public Inventory(List<Bag> bags, int capacity) {
        this.bags = bags;
        this.capacity = capacity;
    }

    public Inventory() {
        this.capacity = 6;
        this.bags = this.generateBags(6);
    }

    public boolean addBag(Bag bag) {
        if (bags.size() < capacity) {
            return bags.add(bag);
        }
        return false;

    }

    private List<Bag> generateBags(int numSlots) {
        ArrayList<Bag> contents = new ArrayList<>();
        for (int i = 0; i < this.capacity; i++) {
            contents.add(new Bag(new ArrayList<Item>(), numSlots));
        }

        return contents;
    }

    public boolean addItem(Item item) {
        for (Bag bag : this.bags) {
            if (!bag.isFull()) {
                bag.addItem(item);
                return true;
            }
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
