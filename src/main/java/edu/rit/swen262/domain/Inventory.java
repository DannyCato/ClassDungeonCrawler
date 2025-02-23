package edu.rit.swen262.domain;

import java.util.List;

public class Inventory {

    private List<Item> contents;
    private List<Bag> bags;
    private int capacity;

    public Inventory(List<Bag> bags, int capacity) {
        this.bags = bags;
        this.capacity = capacity;
    }

    public boolean addItem(Item item){

        if (contents.size() < capacity) {
            return contents.add(item);
        }
        return false;

    }

    public boolean removeItem(Item item){
        
        return contents.remove(item);

    }

    public List<Item> getItems() {

        return contents;
        
    }

    public List<Bag> getBags() {

        return bags;

    }
    
}
