package edu.rit.swen262.domain;

public abstract class Item implements ItemComponent {
    private String name;
    private String description;
    private Gold value;

    public Item(String name, String description, Gold value) {
        this.name = name;
        this.description = description;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Gold getValue() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Item) {
            Item other = (Item) obj;
            return (description.equals(other.description)) && name.equals(other.name);
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return String.format("%s: %s%nValue: %s", name, description, value.getCount());
    }

    public static void main(String[] args) {
        Gold gold = new Gold(10);
        System.out.println(gold);
        Armor armor = new Armor("Armor", "Protect", 1, gold);
        System.out.println(armor);
        Weapon weapon = new Weapon("Weapon", "Fight", 2, gold);
        System.out.println(weapon);
        Food food = new Food("Food", "Heal", 3, gold);
        System.out.println(food);
        Buff buff = new Buff("Buff", "Helps", 4, 4, 4, 4, gold);
        System.out.println(buff);
    }

}
