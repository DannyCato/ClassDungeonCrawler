package edu.rit.swen262.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CharacterTest {

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

        assertEquals(player.getHealth(), 100);

        enemy.attack(player);
        
        // Base attack is 10
        assertEquals(player.getHealth(), 75);
        assertEquals(enemy.getHealth(), 100);

    }

    @Test
    public void test_PlayerAttackEnemy() {

        Inventory inventory = new Inventory(6);
        Gold gold = new Gold(10);

        PlayerCharacter player = new PlayerCharacter("player", "description", null, null, inventory, gold);
        PlayerCharacter enemy = new PlayerCharacter("enemy", "description", null, null, inventory, gold);

        Armor armor = new Armor("armor", "cloth armor", 0, gold);
        Weapon weapon = new Weapon("weapon", "wooden sword", 15, gold);

        player.equipArmor(armor);
        player.equipWeapon(weapon);
        enemy.equipWeapon(weapon);

        assertEquals(player.getHealth(), 100);

        player.attack(enemy);
        
        // Base attack is 10
        assertEquals(enemy.getHealth(), 75);
        assertEquals(player.getHealth(), 100);

    }
    
}
