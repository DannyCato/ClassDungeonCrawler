package edu.rit.swen262.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class BagTest {

    @Test
    public void test_ItemConstructor() {
        List<Item> contents = new ArrayList<>();
        int capacity = 10;
        Bag bag = new Bag(contents, capacity);

        assertEquals(contents, bag.getItems());
        assertEquals(capacity, bag.getCapacity());

    }

    @Test
    public void test_ItemEquals() {
        List<Item> contents = new ArrayList<>();
        int capacity = 10;

        Bag bag1 = new Bag(contents, capacity);
        Bag bag2 = new Bag(contents, capacity);

        assertEquals(bag1, bag2);

    }

    @Test
    public void test_BagNotEquals() {
        List<Item> contents_bag1 = new ArrayList<>();
        List<Item> contents_bag2 = new ArrayList<>();
        int capacity = 10;

        Gold gold = new Gold(10, 1);
        Weapon item = new Weapon("Name", "Description", 0, gold);

        contents_bag2.add(item);

        Bag bag1 = new Bag(contents_bag1, capacity);
        Bag bag2 = new Bag(contents_bag2, capacity);

        assertNotEquals(bag1, bag2);

    }

    @Test
    public void test_BagEqualsHasItem() {
        List<Item> contents_bag1 = new ArrayList<>();
        List<Item> contents_bag2 = new ArrayList<>();
        int capacity = 10;

        Gold gold = new Gold(10, 1);
        Weapon item = new Weapon("Name", "Description", 0, gold);

        contents_bag1.add(item);
        contents_bag2.add(item);

        Bag bag1 = new Bag(contents_bag1, capacity);
        Bag bag2 = new Bag(contents_bag2, capacity);

        assertEquals(bag1, bag2);

    }


}
