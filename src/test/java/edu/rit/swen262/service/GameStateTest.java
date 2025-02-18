package edu.rit.swen262.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import edu.rit.swen262.domain.PlayerCharacter;
import edu.rit.swen262.ui.MUDGame;

@SpringBootTest(properties = "spring.profiles.active:test")
class GameStateTest {
    private GameState gameState;

    @BeforeEach
    public void setupGameState() {
        PlayerCharacter p = new PlayerCharacter("Bobert", "can benchlift exactly 5 worms");
        this.gameState = new GameState(p);
    }

	@Test
	@DisplayName("Validate that the list of observers is correctly initialized as empty")
	void isObserversEmpty() {
        assertTrue(gameState.getObservers().isEmpty());
	}

    @Test
	@DisplayName("Validate that a new observer is correctly added to the list of observers")
	void registerObserver() {
        GameObserver mockObserver = mock(GameObserver.class);

        gameState.register(mockObserver);

        assertTrue(gameState.getObservers().contains(mockObserver));
	}

    @Test
	@DisplayName("Validate that a new observer is correctly removed from the list of observers")
	void deregisterObserver() {
        GameObserver mockObserver = mock(GameObserver.class);

        gameState.register(mockObserver);
        gameState.deregister(mockObserver);

        assertTrue(!gameState.getObservers().contains(mockObserver));
	}

    @Test
	@DisplayName("Validate that all interested observers are correctly updated when the game state changes")
	void notifyObservers() {
        GameObserver mockObserverA = mock(GameObserver.class);
        GameObserver mockObserverB = mock(GameObserver.class);

        gameState.register(mockObserverA);
        gameState.register(mockObserverB);

        
	}
}