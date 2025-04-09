package edu.rit.swen262.service.Action;

import java.util.HashMap;

import static edu.rit.swen262.ui.UIUtilities.buildMenuString;
import edu.rit.swen262.domain.Lootable;
import edu.rit.swen262.domain.Occupant;
import edu.rit.swen262.domain.PlayerCharacter;
import edu.rit.swen262.service.GameState;

public class LootableActionFactory implements InteractionActionFactory {

    @Override
    public InteractionResult createInteractionCommands(GameState gameState, PlayerCharacter player, 
            Occupant occupant) {
        HashMap<Character, Action> keystrokes = new HashMap<Character, Action>(); 
        keystrokes.put('1', new LootObjectAction(gameState, player, (Lootable) occupant));
        String menuText = buildMenuString(keystrokes);

        DisplayMenuAction menuAction = new DisplayMenuAction(gameState, DisplayMenuType.CHEST, menuText);

        return new InteractionResult('o', menuAction, keystrokes);
    }
}
