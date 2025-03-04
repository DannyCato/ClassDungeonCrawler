package edu.rit.swen262.domain;

import java.util.ArrayList;
import java.util.List;

public class Bag {

    private List<Item> contents;
    private int capacity;

    public Bag(int capacity) {
        this.contents = new ArrayList<Item>();
        this.capacity = capacity;
    }

    public Bag(List<Item> contents, int capacity) {
        this.contents = contents;
        this.capacity = capacity;
    }

    public boolean addItem(Item item) {
        if (contents.size() < capacity) {
            return contents.add(item);
        }
        return false;

    }

    public boolean removeItem(Item item) {
        return contents.remove(item);

    }

    public List<Item> getItems() {
        return contents;

    }

    public int getCapacity() {
        return capacity;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Bag) {
            Bag other = (Bag) obj;
            return (contents.equals(other.contents)) && capacity == other.capacity;
        } else {
            return false;
        }
    }

}
