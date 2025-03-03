package edu.rit.swen262.domain;

public class Buff extends Item {

    private int duration;
    private int attack;
    private int defense;
    private int hp;

    public Buff(String name, String description, int duration, int attack, int defense, int hp, Gold value) {
        super(name, description, value);
        this.attack = attack;
        this.defense = defense;
        this.hp = hp;
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    public void decreaseDuration() {
        this.duration--;
        if (this.duration < 0) {
            this.duration = 0;
        }
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getHp() {
        return hp;
    }

    @Override
    public String toString() {
        Gold gold = getValue();
        return String.format("%s: %s%nDuration: %s%nAttack: %s%nDefense: %s%nHp: %s%nValue: %s", getName(), getDescription(), getDuration(), getAttack(), getDefense(), getHp(), gold.getCount());
    }
}
