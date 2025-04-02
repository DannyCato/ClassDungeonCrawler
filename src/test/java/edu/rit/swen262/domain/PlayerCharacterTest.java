package edu.rit.swen262.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    public void test_EnemyAttackPlayer() {

        Inventory inventory = new Inventory(6);
        Gold gold = new Gold(10);

        PlayerCharacter player = new PlayerCharacter("player", "description", null, null, inventory, gold);
        PlayerCharacter enemy = new PlayerCharacter("enemy", "description", null, null, inventory, gold);

        Armor armor = new Armor("armor", "cloth armor", 0, gold);
        Weapon weapon = new Weapon("weapon", "wooden sword", 15, gold);

        player.equipArmor(armor);
        player.equipWeapon(weapon);
        enemy.equipWeapon(weapon);

        assertEquals(100, player.getHealth());

        enemy.attack(player);

        // Base attack is 10
        assertEquals(75, player.getHealth());
        assertEquals(100, enemy.getHealth());

    }

    @Test
    public void test_PlayerAttackEnemy() {

        Inventory inventory = new Inventory(6);
        Gold gold = new Gold(10);

        PlayerCharacter player = new PlayerCharacter("player", "description", null, null, inventory, gold);
        PlayerCharacter enemy = new PlayerCharacter("enemy", "description", null, null, inventory, gold);

        Armor armor = new Armor("armor", "cloth armor", 0, gold);
        Weapon weapon = new Weapon("weapon", "wooden sword", 89, gold);

        player.equipArmor(armor);
        player.equipWeapon(weapon);
        enemy.equipWeapon(weapon);

        assertEquals(100, player.getHealth());

        player.attack(enemy);

        // Base attack is 10
        assertEquals(1, enemy.getHealth());
        assertEquals(100, player.getHealth());

    }

    @Test
    public void test_PlayerLootChest() {
      Inventory inventory = new Inventory(6);
      Gold gold = new Gold(10);

      PlayerCharacter player = new PlayerCharacter("player", "description", null, null, inventory, gold);
      Chest chest = new Chest(new ArrayList<>(), 6);

      // Player should have a random assortment of randomly generated items
      player.lootChest(chest);

      assertFalse(player.inventory.getBags().get(0).getItems().isEmpty());
      assertTrue(chest.getContents().isEmpty());

//      for (Bag bag : player.inventory.getBags()) {
//        for (Item item : bag.getItems()) {
//          System.out.println(item);
//        }
//      }

    }

    @Test
    public void test_PlayerLootCorpse() {
      Inventory inventory = new Inventory(999);
      Gold gold = new Gold(10);

      PlayerCharacter player = new PlayerCharacter("player", "description", null, null, inventory, gold);
      PlayerCharacter player2 = new PlayerCharacter("player2", "description2", null, null, inventory, gold);
      Weapon weapon = new Weapon("weapon", "wooden sword", 999, gold);

      player.equipWeapon(weapon);
      player2.equipWeapon(weapon);
      player2.attack(player);

      // Player 2 should have a sword and some gold.
      player2.lootCorpse(player);

      assertFalse(player2.inventory.getBags().get(0).getItems().isEmpty());

//      for (Bag bag : player2.inventory.getBags()) {
//        for (Item item : bag.getItems()) {
//          System.out.println(item);
//        }
//      }

    }

}
