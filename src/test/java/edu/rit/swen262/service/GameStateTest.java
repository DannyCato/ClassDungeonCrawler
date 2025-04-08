package edu.rit.swen262.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import edu.rit.swen262.domain.DirectionalVector;
import edu.rit.swen262.domain.PlayerCharacter;
import edu.rit.swen262.service.Action.DisplayMenuType;
import edu.rit.swen262.ui.MUDGameUI;

@SpringBootTest(properties = "spring.profiles.active:test")
class GameStateTest {
    private GameState gameState;

    @BeforeEach
    public void setupGameState() {
        PlayerCharacter p = new PlayerCharacter("Bobert", "can benchlift exactly 5 worms");
        this.gameState = new GameState(p);
    }

	@Test
	@DisplayName("Validate that the list of observers is correctly initialized as empty upon subject creation")
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
        GameObserver spyObserverA = spy(GameObserver.class);
        GameObserver spyObserverB = spy(GameObserver.class);
        GameEvent event = new GameEvent(GameEventType.QUIT_GAME);

        gameState.register(spyObserverA);
        gameState.register(spyObserverB);

        gameState.notifyObservers(event);

        assertTrue(gameState.getObservers().contains(spyObserverA));
        assertTrue(gameState.getObservers().contains(spyObserverB));

        verify(spyObserverA).update(event);
	}

    @Test
	@DisplayName("Validate that the correct event for moving a character is constructed + sent")
	void notifyMove() {
        GameObserver spyObserver = spy(GameObserver.class);
        ArgumentCaptor<GameEvent> actualEvent = ArgumentCaptor.forClass(GameEvent.class);

        GameEvent expectedEvent = new GameEvent(GameEventType.MOVE_PLAYER);
        expectedEvent.addData("direction", DirectionalVector.NORTH);

        gameState.register(spyObserver);
        gameState.movePlayer(DirectionalVector.NORTH);

        assertTrue(gameState.getObservers().contains(spyObserver));

        verify(spyObserver).update(actualEvent.capture());
        assertEquals(expectedEvent.getType(), actualEvent.getValue().getType());
        assertEquals(expectedEvent.getAllData(), actualEvent.getValue().getAllData());
	}

    @Test
	@DisplayName("Validate that the correct event for attacking a character is constructed + sent")
	void notifyAttack() {
        GameObserver spyObserver = spy(GameObserver.class);
        ArgumentCaptor<GameEvent> actualEvent = ArgumentCaptor.forClass(GameEvent.class);

        GameEvent expectedEvent = new GameEvent(GameEventType.TAKE_DAMAGE);
        expectedEvent.addData("direction", "East");

        gameState.register(spyObserver);
        gameState.attackCharacter(DirectionalVector.EAST);

        assertTrue(gameState.getObservers().contains(spyObserver));

        verify(spyObserver).update(actualEvent.capture());
        assertEquals(expectedEvent.getType(), actualEvent.getValue().getType());
        assertEquals(expectedEvent.getAllData(), actualEvent.getValue().getAllData());
	}

    @Test
	@DisplayName("Validate that the correct event for displaying a sub-menu is constructed + sent")
	void notifyDisplayMenu() {
        GameObserver spyObserver = spy(GameObserver.class);
        ArgumentCaptor<GameEvent> actualEvent = ArgumentCaptor.forClass(GameEvent.class);

        GameEvent expectedEvent = new GameEvent(GameEventType.DISPLAY_SUBMENU);
        expectedEvent.addData("menuType", DisplayMenuType.MOVE);
        expectedEvent.addData("menuText", "dummy text");

        gameState.register(spyObserver);
        gameState.displayMenu(DisplayMenuType.MOVE, "dummy text", null);

        assertTrue(gameState.getObservers().contains(spyObserver));

        verify(spyObserver).update(actualEvent.capture());
        assertEquals(expectedEvent.getType(), actualEvent.getValue().getType());
        assertEquals(expectedEvent.getAllData(), actualEvent.getValue().getAllData());
	}

    @Test
	@DisplayName("Validate that the correct event for quiting the game is constructed + sent")
	void notifyQuit() {
        GameObserver spyObserver = spy(GameObserver.class);
        ArgumentCaptor<GameEvent> actualEvent = ArgumentCaptor.forClass(GameEvent.class);

        GameEvent expectedEvent = new GameEvent(GameEventType.QUIT_GAME);

        gameState.register(spyObserver);
        gameState.quit();

        assertTrue(gameState.getObservers().contains(spyObserver));

        verify(spyObserver).update(actualEvent.capture());
        assertEquals(expectedEvent.getType(), actualEvent.getValue().getType());
        assertEquals(expectedEvent.getAllData(), actualEvent.getValue().getAllData());
	}
}