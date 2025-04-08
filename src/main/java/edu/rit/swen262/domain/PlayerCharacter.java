    package edu.rit.swen262.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * represents the Player Character traveling through the dungeon, extends the
 * {@link Character}
 */
public class PlayerCharacter extends Character {
    Armor armor;
    Weapon weapon;
    Inventory inventory;
    Gold gold;
    List<Buff> buffs;

    /**
     * initializes a PlayerCharacter with the given name and description. Their
     * maxHP, attack, and defense stats are set to 100, 10, and 0 respectively by
     * default.
     *
     * @param name        the given name of the player
     * @param description the given brief description of the player
     * @param armor       the equipped armor
     * @param weapon      the equipped weapon
     * @param gold        the gold held by the player
     */
    public PlayerCharacter(String name, String description) {
        super(name, description, 100, 10, 0);
        this.armor = null;
        this.weapon = null;
        this.inventory = new Inventory(6);
        this.gold = new Gold(0);
    }

    public PlayerCharacter(String name, String description, Armor armor, Weapon weapon, Inventory inventory,
            Gold gold, List<Buff> buffs) {
        super(name, description, 100, 10, 0);
        this.armor = armor;
        this.weapon = weapon;
        this.inventory = inventory;
        this.gold = gold;
        this.buffs = new ArrayList<>();
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

    public String useBuff(Buff buff) {

        if (buff.getDuration() > 0) {
            boolean add = buffs.add(buff);
            if (add) {
                this.attack += buff.getAttack();
                this.defense += buff.getDefense();
                this.maxHP += buff.getHp();
            }
        }

        return "Your Stats are boosted for " + buff.getDuration() + " turns." +
        "\nAttack: " + this.getAttack() + "(+" + buff.getAttack() + ")" +
        "\nDefense: " + this.getDefense() + "(+" + buff.getDefense() + ")" +
        "\nHealth: " + getMaxHP() + "(+" +buff.getHp() + ")";

    }

    public void updateBuffs() {

        for (int i = 0; i < buffs.size(); i++) {
            buffs.get(i).decreaseDuration();
            if (buffs.get(i).getDuration() <= 0) {
                this.attack -= buffs.get(i).getAttack();
                this.defense -= buffs.get(i).getDefense();
                this.maxHP -= buffs.get(i).getHp();
                buffs.remove(i);
                i--;
            }
        }
    }

    public String useItem(Item item) {
        if (item instanceof Food) {
            Food food = (Food) item;
            return this.eatFood(food);
        } else if (item instanceof Weapon) {
            Weapon weapon = (Weapon) item;
            return this.equipWeapon(weapon);
        } else if (item instanceof Armor) {
            Armor armor = (Armor) item;
            return this.equipArmor(armor);
        } else {
            return "The" + item.getName() + "cannot be used.";
        }
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

    public String addItemToBag(Item item) {
        boolean result = inventory.addItem(item);

        if (result) {
            return item.getName() + " added to Bag.";
        }

        return "Inventory full!";
    }

    public void lootChest(Chest chest) {
        for (Item item : chest.getContents()) {
            addItemToBag(item);
        }
        //chest.getContents().clear();
    }

    public List<Item> getCorpse() {
        if (getHealth() > 0) {
            return null;
        }

        List<Item> corpse = new ArrayList<Item>();

        if (armor != null) {corpse.add(armor);}
        if (weapon != null) {corpse.add(weapon);}
        if (gold != null) {corpse.add(gold);}

        for (Bag bag : inventory.getBags()) {
            corpse.addAll(bag.getItems());
        }

        return corpse;

    }

    public void lootCorpse(PlayerCharacter player) {
        List<Item> items = player.getCorpse();
        for (Item item : items) {
        addItemToBag(item);
        }
    }

    /**
     * removes a single {@link Item} from the player character's {@link Inventory}
     * 
     * @param item the item to be removed
     */
    public void dropItem(Item item) {
        inventory.removeItem(item);
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
