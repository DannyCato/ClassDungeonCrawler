package edu.rit.swen262.domain;

public interface ItemFactory {
    Armor createArmor();
    Weapon createWeapon();
    Food createFood();
    Buff createBuff();
}
