package edu.rit.swen262.service.Action;

import java.util.HashMap;
import java.util.List;

import static edu.rit.swen262.ui.UIUtilities.buildMenuString;
import edu.rit.swen262.domain.Lootable;
import edu.rit.swen262.domain.Item;
import edu.rit.swen262.domain.Merchant;
import edu.rit.swen262.domain.Occupant;
import edu.rit.swen262.domain.PlayerCharacter;
import edu.rit.swen262.service.GameState;

/**
 * generates the {@link Action} mapping for interaction of a {@link PlayerCharacter} and a {@link Merchant} Tile {@link Occupant}
 * 
 * @author Victor Bovat
 */
public class MerchantActionFactory implements InteractionActionFactory {

    /**
     * {@inheritdoc}
     */
    @Override
    public InteractionResult createInteractionCommands(GameState gameState, PlayerCharacter player, 
            Occupant occupant) {
        HashMap<DisplayMenuType, HashMap<Character, Action>> keystrokesMap = new HashMap<DisplayMenuType, HashMap<Character, Action>>();
        HashMap<Character, Action> merchantKeystrokes = new HashMap<Character, Action>(); 
        HashMap<Character, Action> buyKeystrokes = new HashMap<Character, Action>(); 
        HashMap<Character, Action> sellKeystrokes = new HashMap<Character, Action>();
        
        Merchant merchant = (Merchant) occupant;
        List<Item> merchantInventory = merchant.getShopItems();
        
        // build actions for buying merchant items
        for (int i = 0; i < merchantInventory.size(); i++) {
            buyKeystrokes.put(Character.forDigit(i + 1, 10), new BuyItemAction(gameState, merchant, merchantInventory.get(i), i));
        }

        //build merchant sub-menu for all options taken when interacting with merchant
        String merchantInventoryText = buildMenuString(buyKeystrokes);

        merchantKeystrokes.put('1', new DisplayMenuAction(gameState, DisplayMenuType.MERCHANT_INVENTORY, merchantInventoryText));
        //merchantKeystrokes.put('2', new DisplayMenuAction(gameState, DisplayMenuType.INVENTORY, ""));
        
        // build entry action which is called when merchant is interacted with
        String merchantMenuText = buildMenuString(merchantKeystrokes);
        DisplayMenuAction menuAction = new DisplayMenuAction(gameState, DisplayMenuType.MERCHANT, merchantMenuText);

        keystrokesMap.put(DisplayMenuType.MERCHANT_INVENTORY, buyKeystrokes);
        keystrokesMap.put(DisplayMenuType.MERCHANT, merchantKeystrokes);

        return new InteractionResult('e', menuAction, keystrokesMap);
    }
}
