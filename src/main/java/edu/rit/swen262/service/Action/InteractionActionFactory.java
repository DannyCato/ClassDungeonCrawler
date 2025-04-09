package edu.rit.swen262.service.Action;

import java.util.HashMap;

import edu.rit.swen262.domain.Occupant;
import edu.rit.swen262.domain.PlayerCharacter;
import edu.rit.swen262.service.GameState;

public interface InteractionActionFactory {
    public InteractionResult createInteractionCommands(GameState gameState, PlayerCharacter player, Occupant occupant);
}
