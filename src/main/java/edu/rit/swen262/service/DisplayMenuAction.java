package edu.rit.swen262.service;

/**
 * 
 * @author Victor Bovat
 */
public class DisplayMenuAction implements Action {
    private GameState gameState;
    private DisplayMenuType menuType;
    private String menuText;

    public DisplayMenuAction(GameState gameState, DisplayMenuType menuType, 
                            String menuText) {
        this.gameState = gameState;
        this.menuType = menuType;
        this.menuText = menuText;
    }

    /**
     * 
     */
    @Override
    public void performAction() {
        gameState.displayMenu(this.menuType, this.menuText);
    }
}
