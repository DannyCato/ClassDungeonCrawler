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
        Buff buff = new Buff("Buff", "Buff Description", 0, 0, 0, 0, gold);
        Buff buff2 = new Buff("Cool Buff", "Buff Description", 0, 0, 0, 0, gold);

        assertNotEquals(buff, buff2);

    }

    @Test
    public void test_BuffIsItem() {
        Gold gold = new Gold(10);
        Buff buff = new Buff("Buff", "Buff Description", 0, 0, 0, 0, gold);

        assertEquals(buff instanceof Item, true);

    }

    @Test
    public void test_ApplyBuff() {
        Gold gold = new Gold(10);
        Buff buff = new Buff("Buff", "Buff Description", 10, 5, 10, 2, gold);
        PlayerCharacter player = new PlayerCharacter("name", "desc", null, null, null, null, null);

        player.useBuff(buff);
        assertEquals(15, player.getAttack());
        assertEquals(10, player.getDefense());
        assertEquals(102, player.getMaxHP());
        assertEquals(10, buff.getDuration());

    }

    @Test
    public void test_DecreaseDuration() {

        Gold gold = new Gold(10);
        Buff buff = new Buff("Buff", "Buff Description", 10, 5, 10, 2, gold);
        PlayerCharacter player = new PlayerCharacter("name", "desc", null, null, null, null, null);

        player.useBuff(buff);
        assertEquals(15, player.getAttack());
        assertEquals(10, player.getDefense());
        assertEquals(102, player.getMaxHP());
        assertEquals(10, buff.getDuration());

        player.updateBuffs();
        assertEquals(15, player.getAttack());
        assertEquals(10, player.getDefense());
        assertEquals(102, player.getMaxHP());
        assertEquals(9, buff.getDuration());

    }

    @Test
    public void test_RemoveBuffs() {


        Gold gold = new Gold(10);
        Buff buff = new Buff("Buff", "Buff Description", 1, 5, 10, 2, gold);
        PlayerCharacter player = new PlayerCharacter("name", "desc", null, null, null, null, null);

        player.useBuff(buff);
        assertEquals(15, player.getAttack());
        assertEquals(10, player.getDefense());
        assertEquals(102, player.getMaxHP());
        assertEquals(1, buff.getDuration());

        player.updateBuffs();
        assertEquals(10, player.getAttack());
        assertEquals(0, player.getDefense());
        assertEquals(100, player.getMaxHP());
        assertEquals(0, buff.getDuration());

    }
}