package edu.rit.swen262.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class GoldTest {

    @Test
    public void test_GoldConstructor() {
        Gold gold = new Gold(1);

        assertEquals(gold, gold.getValue());
        assertEquals(1, gold.getCount());

    }

    @Test
    public void test_IncreaseCount() {

        Gold gold = new Gold(5);
        gold.increaseCount();
        gold.increaseCount();

        assertEquals(7, gold.getCount());

    }

    @Test
    public void test_DecreaseCount() {

        Gold gold = new Gold(5);
        gold.decreaseCount();
        gold.decreaseCount();

        assertEquals(3, gold.getCount());

    }

    @Test
    public void test_DecreaseCountNegative() {

        Gold gold = new Gold(1);
        gold.decreaseCount();
        gold.decreaseCount();
        gold.decreaseCount();
        gold.decreaseCount();

        // Should reset to 0 each decrease after 0
        assertEquals(0, gold.getCount());

    }


    
}
