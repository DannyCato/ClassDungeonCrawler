package edu.rit.swen262.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

class BuffTest {

    @Test
    public void test_BuffConstructor() {
        Gold gold = new Gold(10);
        Buff buff = new Buff("Buff", "Buff Description", 0, 0, 0, 0, gold);

        assertEquals(gold, gold.getValue());
        assertEquals("Buff", buff.getName());
        assertEquals("Buff Description", buff.getDescription());
        assertEquals(0, buff.getDuration());
        assertEquals(0, buff.getAttack());
        assertEquals(0, buff.getDefense());
        assertEquals(0, buff.getHp());

    }

    @Test
    public void test_BuffEquals() {
        Gold gold = new Gold(10);
        Buff buff = new Buff("Buff", "Buff Description", 0, 0, 0, 0, gold);
        Buff buff2 = new Buff("Buff", "Buff Description", 0, 0, 0, 0, gold);

        assertEquals(buff, buff2);

    }

    @Test
    public void test_BuffNotEquals() {
        Gold gold = new Gold(10);
        Buff Buff = new Buff("Buff", "Buff Description", 0, 0, 0, 0, gold);
        Buff Buff2 = new Buff("Cool Buff", "Buff Description", 0, 0, 0, 0, gold);

        assertNotEquals(Buff, Buff2);

    }

    @Test
    public void test_BuffIsItem() {
        Gold gold = new Gold(10);
        Buff Buff = new Buff("Buff", "Buff Description", 0, 0, 0, 0, gold);

        assertEquals(Buff instanceof Item, true);

    }

}