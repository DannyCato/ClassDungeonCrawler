package edu.rit.swen262.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

class FoodTest {

    @Test
    public void test_FoodConstructor() {
        Gold gold = new Gold(10, 1);
        Food food = new Food("Food", "Food Description", 0, gold);

        assertEquals(gold, gold.getValue());
        assertEquals("Food", food.getName());
        assertEquals("Food Description", food.getDescription());
        assertEquals(0, food.getHp());

    }

    @Test
    public void test_FoodEquals() {
        Gold gold = new Gold(10, 1);
        Food food = new Food("Food", "Food Description", 0, gold);
        Food food2 = new Food("Food", "Food Description", 0, gold);

        assertEquals(food, food2);

    }

    @Test
    public void test_FoodNotEquals() {
        Gold gold = new Gold(10, 1);
        Food Food = new Food("Food", "Food Description", 0, gold);
        Food Food2 = new Food("Cool Food", "Food Description", 0, gold);

        assertNotEquals(Food, Food2);

    }

    @Test
    public void test_FoodIsItem() {
        Gold gold = new Gold(10, 1);
        Food Food = new Food("Food", "Food Description", 0, gold);

        assertEquals(Food instanceof Item, true);

    }

}