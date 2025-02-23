package edu.rit.swen262.domain;

import java.util.List;

public class Bag {

    private List<Item> contents;
    private int capacity;

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

}
