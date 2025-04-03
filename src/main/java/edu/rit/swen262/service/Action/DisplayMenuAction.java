package edu.rit.swen262.service.Action;

import edu.rit.swen262.service.GameState;
import edu.rit.swen262.ui.MUDGameUI;

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
     * fetches the {@link GameState} that the action is bound to
     * 
     * @return the {@link GameState} object executing the command
     */
    public GameState getGameState() {
        return this.gameState;
    }

    /**
     * fetches the {@link DisplayMenuType menu type} that
     * this {@link Action} displays
     * 
     * @return the menu type to be displayed
     */
    public DisplayMenuType getMenuType() {
        return this.menuType;
    }

    /**
     * fetches the literal content to be displayed by the UI
     * 
     * @return the contents of the menu as a String
     */
    public String getMenuText() {
        return this.menuText;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performAction() {
        //System.out.println(menuType + " - " + menuText);
        gameState.displayMenu(this.menuType, this.menuText);
    }
}
