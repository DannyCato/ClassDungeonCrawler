package edu.rit.swen262.domain;

/**
 * represents the Player Character traveling through the dungeon, extends the
 * {@link Character}
 */
public class PlayerCharacter extends Character {
    Armor armor;
    Weapon weapon;
    Inventory inventory;
    Gold gold;

    /**
     * initializes a PlayerCharacter with the given name and description. Their
     * maxHP, attack, and defense stats are set to 100, 10, and 0 respectively by
     * default.
     * 
     * @param name        the given name of the player
     * @param description the given brief description of the player
     * @param armor       the equipped armor
     * @param weapon      the equipped weapon
     * @param gold        the gold
     */

    public PlayerCharacter(String name, String description) {
        super(name, description, 100, 10, 0);
        this.armor = null;
        this.weapon = null;
        this.inventory = new Inventory(6);
        this.gold = new Gold(0);
    }

    public PlayerCharacter(String name, String description, Armor armor, Weapon weapon, Inventory inventory,
            Gold gold) {
        super(name, description, 100, 10, 0);
        this.armor = armor;
        this.weapon = weapon;
        this.inventory = inventory;
        this.gold = gold;
    }

    public String eatFood(Food food) {
        this.health += food.getHp();
        if (this.health > this.maxHP) {
            this.health = this.maxHP;
        }

        return "Ate food: " + food.getName();
    }

    public String equipArmor(Armor armor) {
        if (this.armor == null) {
            this.armor = armor;
            this.defense += armor.getDefense();
        }

        else {
            Armor temp = this.armor;
            this.defense -= temp.getDefense();
            this.armor = armor;
            this.defense += armor.getDefense();
            for (Bag bag : inventory.getBags()) {
                boolean result = bag.addItem(temp);
                if (result) {
                    break;
                }
            }
        }

        return "Now wearing: " + armor.getName();
    }

    public String equipWeapon(Weapon weapon) {
        if (this.weapon == null) {
            this.weapon = weapon;
            this.attack += weapon.getAttack();
        }

        else {
            Weapon temp = this.weapon;
            this.attack -= temp.getAttack();
            this.weapon = weapon;
            this.attack += weapon.getAttack();
            for (Bag bag : inventory.getBags()) {
                boolean result = bag.addItem(temp);
                if (result) {
                    break;
                }
            }
        }

        return "Now wielding: " + weapon.getName();
    }

    public Item findItem(Item item) {
        for (Bag bag : inventory.getBags()) {
            for (Item each : bag.getItems()) {
                if (each.equals(item)) {
                    bag.removeItem(each);
                    return each;
                }
            }
        }
        return null;
    }

    public void pickUpItem(Item item) {
        inventory.addItem(item);
    }

    /**
     * {@inheritdoc}
     */
    public String description() {
        return "the player, " + this.name;
    }

    /**
     * {@inheritdoc}
     */
    public RenderRepresentation render() {
        return RenderRepresentation.CHARACTER;
    }

    public Inventory getInventory() {
        return this.inventory;
    }
}
