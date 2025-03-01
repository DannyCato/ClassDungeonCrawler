package edu.rit.swen262.service;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.jline.utils.Display;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.boot.test.context.SpringBootTest;

import edu.rit.swen262.service.Action.*;
import edu.rit.swen262.domain.DirectionalVector;
import edu.rit.swen262.domain.PlayerCharacter;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ActionTest {
    private PlayerCharacter player;

    @BeforeEach
    void setupGameState() {
        this.player = mock(PlayerCharacter.class);
    }

    @Test
	@DisplayName("Validate that a concrete attack action is correctly initialized")
	void attackActionInit() {
        GameState gameState = mock(GameState.class);
        AttackAction attackAction = new AttackAction(gameState, DirectionalVector.NORTH);
        
        assertTrue(attackAction.getGameState().equals(gameState));
        assertTrue(attackAction.getDirection().equals("North"));
    }

    @Test
    @DisplayName("Validate that the correct method is called by the GameState when a MoveAction is executed")
    void moveActionPerform() {
        GameState gameState = new GameState(this.player);
        GameState spyGameState = spy(gameState);

        MoveAction moveAction = new MoveAction(spyGameState, DirectionalVector.SOUTH);

        moveAction.performAction();
        verify(spyGameState).movePlayer(DirectionalVector.SOUTH);
        
    }

    @Test
    @DisplayName("Validate that an attack action is correctly initialized")
    void movementActionInit() {
        GameState gameState = mock(GameState.class);
        MoveAction moveAction = new MoveAction(gameState, DirectionalVector.NORTH);

        assertTrue(moveAction.getGameState().equals(gameState));
        assertTrue(moveAction.getDirection().equals("North"));
    }

    @Test
    @DisplayName("Validate that the correct method is called by the GameState when an AttackAction is executed")
    void attackActionPerform() {
        GameState gameState = new GameState(this.player);
        GameState spyGameState = spy(gameState);

        AttackAction attackAction = new AttackAction(spyGameState, DirectionalVector.SOUTH);

        attackAction.performAction();
        verify(spyGameState).attackCharacter(DirectionalVector.SOUTH);
        
    }

    @Test
    @DisplayName("Validate that a display menu action is correctly initialized")
    void displayMenuActionInit() {
        GameState gameState = mock(GameState.class);

        DisplayMenuAction displayMenuAction = new DisplayMenuAction(gameState, 
                                    DisplayMenuType.INVENTORY, "dummy text");
        
        assertTrue(displayMenuAction.getGameState().equals(gameState));
        assertEquals(DisplayMenuType.INVENTORY, displayMenuAction.getMenuType());
        assertTrue(displayMenuAction.getMenuText().equals("dummy text"));
    }
    
    @Test
    @DisplayName("Validate that the correct method is called by the GameState when a MoveAction is executed")
    void displayMenuActionPerform() {
        GameState gameState = new GameState(this.player);
        GameState spyGameState = spy(gameState);

        DisplayMenuAction menuAction = new DisplayMenuAction(spyGameState, 
                                            DisplayMenuType.MOVE, "dummy text");
        
        menuAction.performAction();
        verify(spyGameState).displayMenu(DisplayMenuType.MOVE, "dummy text");
    }
}
