package edu.rit.swen262.service.Action;

import edu.rit.swen262.domain.PlayerCharacter;
import edu.rit.swen262.service.GameState;

/**
 * represents a single {@link Action} which is responsible for updating the
 * menu panel within the {@link MUDGameUI UI}
 * 
 * @author Victor Bovat, Philip Rubbo
 */
public class DisplayMenuAction implements Action {
    private GameState gameState;
    private DisplayMenuType menuType;
    private String menuText;

    /**
     * instantiates a {@link Action concrete command} with the given 
     * {@link GameState}, {@link DisplayMenuType menu display type},
     * and String representation
     * 
     * @param gameState the current state of the game
     * @param menuType the type of menu to be displayed
     * @param menuText the text of the menu
     */
    public DisplayMenuAction(GameState gameState, DisplayMenuType menuType, 
                            String menuText) {
        this.gameState = gameState;
        this.menuType = menuType;
        this.menuText = menuText;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performAction() {
        gameState.displayMenu(this.menuType, this.menuText);
    }
}
