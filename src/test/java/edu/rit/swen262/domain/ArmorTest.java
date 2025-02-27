package edu.rit.swen262.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

class ArmorTest {

    @Test
    public void test_ArmorConstructor() {
        Gold gold = new Gold(10, 1);
        Armor armor = new Armor("Armor", "Armor Description", 0, gold);

        assertEquals(gold, gold.getValue());
        assertEquals("Armor", armor.getName());
        assertEquals("Armor Description", armor.getDescription());
        assertEquals(0, armor.getDefense());

    }

    @Test
    public void test_ArmorEquals() {
        Gold gold = new Gold(10, 1);
        Armor armor = new Armor("Armor", "Armor Description", 0, gold);
        Armor armor2 = new Armor("Armor", "Armor Description", 0, gold);

        assertEquals(armor, armor2);

    }

    @Test
    public void test_ArmorNotEquals() {
        Gold gold = new Gold(10, 1);
        Armor armor = new Armor("Armor", "Armor Description", 0, gold);
        Armor armor2 = new Armor("Cool Armor", "Armor Description", 0, gold);

        assertNotEquals(armor, armor2);

    }

    @Test
    public void test_ArmorIsItem() {
        Gold gold = new Gold(10, 1);
        Armor armor = new Armor("Armor", "Armor Description", 0, gold);

        assertEquals(armor instanceof Item, true);

    }

}
