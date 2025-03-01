package edu.rit.swen262.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.standard.commands.Quit;

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
import edu.rit.swen262.service.Action.*;
import edu.rit.swen262.ui.MUDGameUI;
import java.util.HashMap;

@SpringBootTest(properties = "spring.profiles.active:test")
class InputParserTest {
    private HashMap<MenuState, HashMap<Character, Action>> keystrokes;
    private InputParser inputParser;
    private GameState gameState;

    @BeforeEach
    public void setUpInputParser() {
        this.gameState = mock(GameState.class);
        this.keystrokes = new HashMap<MenuState, HashMap<Character, Action>>();
        
        //init spies of commands in map
        MoveAction moveAction = new MoveAction(gameState, DirectionalVector.NORTH);
        MoveAction spyMoveAction = spy(moveAction);
        AttackAction attackAction = new AttackAction(gameState, DirectionalVector.NORTH);
        AttackAction spyAttackAction = spy(attackAction);
        DisplayMenuAction displayMenuAction = new DisplayMenuAction(gameState, DisplayMenuType.ATTACK, "dummy text");
        DisplayMenuAction spyDisplayMenuAction = spy(displayMenuAction);
        QuitGameAction quitAction = new QuitGameAction(gameState);
        QuitGameAction spyQuitGameAction = spy(quitAction);

        HashMap<Character, Action> defaultKeystrokes = new HashMap<Character, Action>() {{
            put('m', spyDisplayMenuAction);
            put('a', spyDisplayMenuAction);
            put('q', spyQuitGameAction);
            put('i', spyDisplayMenuAction);
        }};

        HashMap<Character, Action> moveKeystrokes = new HashMap<Character, Action>() {{
            put('1', spyMoveAction);
            put('2', spyAttackAction);
        }};

        HashMap<Character, Action> attackKeystrokes = new HashMap<Character, Action>() {{
            put('1', spyAttackAction);
            put('2', spyAttackAction);
        }};

        keystrokes.put(MenuState.MOVE, moveKeystrokes);
        keystrokes.put(MenuState.ATTACK, attackKeystrokes);
        keystrokes.put(MenuState.DEFAULT, defaultKeystrokes);

        this.inputParser = new InputParser(keystrokes);
    }

    @Test
    @DisplayName("Validates that the input parser correctly interprets a 'm' keystroke and transitions to a sub-menu")
    void parseMoveKeystroke() {
        this.inputParser.receivedInput("m");

        verify(keystrokes.get(MenuState.DEFAULT).get('m')).performAction();

        assertTrue(inputParser.currentMenu.equals(MenuState.MOVE));
    }

    @Test
    @DisplayName("Validates that the input parser correctly interprets a numeric keystroke after the entry of an 'm' keystroke")
    void parseMoveSelectionKeystroke() {
        this.inputParser.receivedInput("m");
        verify(keystrokes.get(MenuState.DEFAULT).get('m')).performAction();
        assertTrue(inputParser.currentMenu.equals(MenuState.MOVE));

        this.inputParser.receivedInput("1");
        verify(keystrokes.get(MenuState.MOVE).get('1')).performAction();

        assertTrue(inputParser.currentMenu.equals(MenuState.DEFAULT));
    }

    @Test
    @DisplayName("Validates that the input parser correctly interprets an 'a' keystroke and transitions to a sub-menu")
    void parseAttackKeystroke() {
        this.inputParser.receivedInput("a");

        verify(keystrokes.get(MenuState.DEFAULT).get('a')).performAction();

        assertTrue(inputParser.currentMenu.equals(MenuState.ATTACK));
    }

    @Test
    @DisplayName("Validates that the input parser correctly interprets a numeric keystroke after the entry of an 'a' keystroke")
    void parseAttackSelectionKeystroke() {
        this.inputParser.receivedInput("a");
        verify(keystrokes.get(MenuState.DEFAULT).get('a')).performAction();
        
        assertTrue(inputParser.currentMenu.equals(MenuState.ATTACK));

        this.inputParser.receivedInput("1");
        verify(keystrokes.get(MenuState.ATTACK).get('1')).performAction();

        assertTrue(inputParser.currentMenu.equals(MenuState.DEFAULT));
    }
    
    @Test
    @DisplayName("Validates that the input parser correctly interprets a 'q' keystroke")
    void parseQuitKeystroke() {
        this.inputParser.receivedInput("q");

        verify(keystrokes.get(MenuState.DEFAULT).get('q')).performAction();
    }
    
    @Test
    @DisplayName("Validates that the input parser correctly interprets an 'i' keystroke and transitions to a sub-menu")
    void parseInventoryKeystroke() {
        this.inputParser.receivedInput("i");

        verify(keystrokes.get(MenuState.DEFAULT).get('i')).performAction();;

        assertTrue(inputParser.currentMenu.equals(MenuState.INVENTORY));
    }

    //TODO: add additional tests for navigating inventory -> bag -> useItem()
}