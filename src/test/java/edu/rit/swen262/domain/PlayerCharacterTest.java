package edu.rit.swen262.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class PlayerCharacterTest {

    @Test
    public void test_EquipItem() {

        Inventory inventory = new Inventory(6);
        Gold gold = new Gold(10);
        PlayerCharacter character = new PlayerCharacter("name", "description", null, null, inventory, gold);
        Armor armor = new Armor("armor", "description armor", 999, gold);
        Weapon weapon = new Weapon("weapon", "description weapon", 999, gold);
        
        character.equipArmor(armor);
        character.equipWeapon(weapon);

        assertEquals(character.armor, armor);
        assertEquals(character.weapon, weapon);

    }

    @Test
    public void test_EquipItemSlotTaken() {

        Inventory inventory = new Inventory(6);
        Gold gold = new Gold(10);
        PlayerCharacter character = new PlayerCharacter("name", "description", null, null, inventory, gold);
        Armor armor = new Armor("armor", "description armor", 999, gold);
        Armor armor2 = new Armor("shouldnt equip this", "description armor", 1, gold);
        Weapon weapon = new Weapon("weapon", "description weapon", 999, gold);
        
        character.equipArmor(armor);
        character.equipWeapon(weapon);
        character.equipArmor(armor2);

        assertEquals(character.armor, armor2);
        assertEquals(character.weapon, weapon);
        assertTrue(inventory.getBags().get(0).getItems().contains(armor));

    }
    
}
