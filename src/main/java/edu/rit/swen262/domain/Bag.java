package edu.rit.swen262.domain;

import java.util.List;

import edu.rit.swen262.service.InventoryVisitor;

public class Bag implements InventoryComponent {
    private List<Item> contents;
    private int capacity;

    public Bag(List<Item> contents, int capacity) {
        this.contents = contents;
        this.capacity = capacity;
    }

    public Item findItem(Item item) {
        for (Item each: contents) {
            if (each.equals(item)) {
                return each;
            }
        }
        return null;
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

    public boolean isFull() {
        return this.contents.size() == this.capacity;
    }

    public String getName() {
        return "Bag";
    }

    public String getDescription(){
        return "Just a little baggie waggie";
    }

    public Gold getValue(){
        int totalVal = 0;
        
        for (Item item : contents) {
            totalVal += item.getValue().getStack();
        }

        return new Gold(totalVal, totalVal);
    }

    public void accept(InventoryVisitor visitor) {
        visitor.visitBag(this);
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
