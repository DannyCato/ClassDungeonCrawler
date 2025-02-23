package edu.rit.swen262.domain;

import java.util.List;

public class Bag {

    private List<Item> contents;
    private int capacity;

    public Bag(List<Item> contents, int capacity){
        this.contents = contents;
        this.capacity = capacity;
    }

    public boolean removeItem(Item item){

        return contents.remove(item);
    
    }

    public boolean addItem(Item item){

        return contents.add(item);

    }

    public List<Item> getItems() {

        return contents;
        
    }
    
}
