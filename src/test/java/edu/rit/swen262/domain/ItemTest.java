package edu.rit.swen262.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

class ItemTest {

    public class MockItem extends Item {
        public MockItem(String name, String description, Gold value) {
            super(name, description, value);
        }
    }

    @Test
    public void test_ItemConstructor() {
        Gold gold = new Gold(10, 1);
        Item item = new MockItem("Item", "Description", gold);

        assertEquals(gold, gold.getValue());
        assertEquals("Item", item.getName());
        assertEquals("Description", item.getDescription());

    }

    @Test
    public void test_ItemEquals() {
        Gold gold = new Gold(10, 1);
        Item item = new MockItem("Item", "Description", gold);
        Item item2 = new MockItem("Item", "Description", gold);

        assertEquals(item, item2);

    }

    @Test
    public void test_ItemNotEquals() {
        Gold gold = new Gold(10, 1);
        Item item = new MockItem("Item", "Description", gold);
        Item item2 = new MockItem("Cool Item", "Description", gold);

        assertNotEquals(item, item2);

    }
}