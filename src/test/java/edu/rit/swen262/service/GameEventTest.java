package edu.rit.swen262.service;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.boot.test.context.SpringBootTest;

import edu.rit.swen262.service.*;
import edu.rit.swen262.service.Action.DisplayMenuType;
import edu.rit.swen262.domain.PlayerCharacter;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameEventTest {
    @Test
	@DisplayName("Validate that a concrete attack action is correctly initialized")
	void gameEventInit() {
        GameEventType gameEventType = GameEventType.QUIT_GAME;
        GameEvent gameEvent = new GameEvent(gameEventType);
        
        assertTrue(gameEvent.getType().equals(GameEventType.QUIT_GAME));
        assertTrue(gameEvent.getAllData().size() == 0);
    }

    @Test
    @DisplayName("Validate that a single piece of data can be added to a GameEvent's data map")
    void gameEventAddData() {
        GameEventType gameEventType = GameEventType.QUIT_GAME;
        GameEvent gameEvent = new GameEvent(gameEventType);

        gameEvent.addData("foo", "bar");

        assertTrue(gameEvent.getAllData().size() == 1);
        assertTrue(gameEvent.getAllData().containsKey("foo"));
        assertTrue(gameEvent.getAllData().containsValue("bar"));
    }

    @Test
    @DisplayName("Validate that a multiple pieces of data can be added to a GameEvent's data map")
    void gameEventAddMutlipleData() {
        GameEventType gameEventType = GameEventType.DISPLAY_SUBMENU;
        GameEvent gameEvent = new GameEvent(gameEventType);

        gameEvent.addData("menuType", DisplayMenuType.MOVE);
        gameEvent.addData("menuText", "BEES");
        gameEvent.addData("goo", "gar");

        assertTrue(gameEvent.getAllData().size() == 3);
        assertTrue(gameEvent.getAllData().containsKey("goo"));
        assertTrue(gameEvent.getAllData().containsKey("menuText"));
        assertTrue(gameEvent.getAllData().containsKey("menuType"));
        assertTrue(gameEvent.getAllData().containsValue("BEES"));
        assertTrue(gameEvent.getAllData().containsValue("gar"));
        assertTrue(gameEvent.getAllData().containsValue(DisplayMenuType.MOVE));
    }
}