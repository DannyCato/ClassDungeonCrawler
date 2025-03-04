package edu.rit.swen262.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

class WeaponTest {

    @Test
    public void test_WeaponConstructor() {
        Gold gold = new Gold(10, 1);
        Weapon weapon = new Weapon("Weapon", "Weapon Description", 0, gold);

        assertEquals(gold, gold.getValue());
        assertEquals("Weapon", weapon.getName());
        assertEquals("Weapon Description", weapon.getDescription());

    }

    @Test
    public void test_WeaponEquals() {
        Gold gold = new Gold(10, 1);
        Weapon weapon = new Weapon("Weapon", "Weapon Description", 0, gold);
        Weapon weapon2 = new Weapon("Weapon", "Weapon Description", 0, gold);

        assertEquals(weapon, weapon2);

    }

    @Test
    public void test_WeaponNotEquals() {
        Gold gold = new Gold(10, 1);
        Weapon Weapon = new Weapon("Weapon", "Weapon Description", 0, gold);
        Weapon Weapon2 = new Weapon("Cool Weapon", "Weapon Description", 0, gold);

        assertNotEquals(Weapon, Weapon2);

    }

    @Test
    public void test_WeaponIsItem() {
        Gold gold = new Gold(10, 1);
        Weapon Weapon = new Weapon("Weapon", "Weapon Description", 0, gold);

        assertEquals(Weapon instanceof Item, true);

    }

}