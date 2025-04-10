package edu.rit.swen262.service.Action;

import java.util.HashMap;

import edu.rit.swen262.domain.Occupant;
import edu.rit.swen262.domain.PlayerCharacter;
import edu.rit.swen262.service.GameState;


/**
 * Handles the creation of a interaction menu of a {@link PlayerCharacter} and an {@link Occupant} like {@link Merchant}
 * 
 * @author Victor Bovat
 */
public interface InteractionActionFactory {
    /**
     * Requires a {@link PlayerCharacter}, {@link GameState}, and a {@link Occupant} to create a interaction menu between them.
     * 
     * @param gameState     An object containing the current {@link GameState}
     * @param player        The current {@link PlayerCharacter} that is interacting with a chosen {@link Occupant}
     * @param occupant      The {@link Occupant} that is chosen to interact with the current {@link PlayerCharacter}
     * @return              Returns a new {@link InteractionResult} that requires a default keystroke, {@link Action}, and keystroke map
     */
    public InteractionResult createInteractionCommands(GameState gameState, PlayerCharacter player, Occupant occupant);
}
