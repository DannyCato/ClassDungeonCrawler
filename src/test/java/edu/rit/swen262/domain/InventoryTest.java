package edu.rit.swen262.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class InventoryTest {

    @Test
    public void test_InventoryConstructor() {

        List<Bag> contents = new ArrayList<>();
        int capacity = 10;

        Inventory inventory = new Inventory(contents, capacity);

        assertEquals(contents, inventory.getBags());
        assertEquals(capacity, inventory.getCapacity());

    }

    @Test
    public void test_AddBag() {

        List<Bag> contents = new ArrayList<>();
        int capacity = 6;
        Inventory inventory = new Inventory(contents, capacity);
        Bag bag = new Bag(null, 0);

        inventory.addBag(bag);

        assertEquals(inventory.getTotalBags(), 1);

    }

    @Test
    public void test_RemoveBag() {

        List<Bag> contents = new ArrayList<>();
        List<Item> items = new ArrayList<>();
        int capacity = 6;
        Inventory inventory = new Inventory(contents, capacity);
        Bag bag = new Bag(items, 0);
        inventory.addBag(bag);
        inventory.addBag(bag);
        inventory.addBag(bag);

        // Bag cannot be null, else fail
        inventory.removeBag(bag);

        assertEquals(inventory.getTotalBags(), 2);

    }
    
}
