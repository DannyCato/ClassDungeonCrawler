package edu.rit.swen262.domain;

public abstract class Item implements InventoryComponent {
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
        return String.format("%s: %s%nValue: %s", name, description, value);
    }

    public static void main(String[] args) {
        Gold gold = new Gold(10);
        Armor armor = new Armor("NAME", "DESC", 0, gold);
        System.out.println(armor);
    }

}
