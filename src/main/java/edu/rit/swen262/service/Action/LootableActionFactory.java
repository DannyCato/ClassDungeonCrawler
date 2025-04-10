package edu.rit.swen262.service.Action;

import java.util.HashMap;

import static edu.rit.swen262.ui.UIUtilities.buildMenuString;
import edu.rit.swen262.domain.Lootable;
import edu.rit.swen262.domain.Occupant;
import edu.rit.swen262.domain.PlayerCharacter;
import edu.rit.swen262.service.GameState;

/**
 * generates the {@link Action} mapping for interaction of a {@link PlayerCharacter} and a {@link Lootable} Tile {@link Occupant}
 * 
 * @author Victor Bovat
 */
public class LootableActionFactory implements InteractionActionFactory {

    /*
     * {@inheritdoc}
     */
    public InteractionResult createInteractionCommands(GameState gameState, PlayerCharacter player, 
            Occupant occupant) {
        HashMap<DisplayMenuType, HashMap<Character, Action>> keystrokesMap = new HashMap<DisplayMenuType, HashMap<Character, Action>>();
        HashMap<Character, Action> chestKeystrokes = new HashMap<Character, Action>(); 
        chestKeystrokes.put('1', new LootObjectAction(gameState, player, (Lootable) occupant));
        String menuText = buildMenuString(chestKeystrokes);

        DisplayMenuAction menuAction = new DisplayMenuAction(gameState, DisplayMenuType.CHEST, menuText);

        keystrokesMap.put(DisplayMenuType.CHEST, chestKeystrokes);

        return new InteractionResult('o', menuAction, keystrokesMap);
    }
}
